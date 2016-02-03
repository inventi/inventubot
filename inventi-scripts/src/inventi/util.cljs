(ns inventi.util
  (:require [cognitect.transit :as t]
            [cljs.nodejs :as nodejs]
            [cljs.core.async :as async :refer [<!]])
  (:require-macros  [cljs.core.async.macros :refer  [go-loop]]))

(defonce https (nodejs/require  "https"))
(defonce http (nodejs/require  "http"))
(defonce url  (nodejs/require  "url"))

(defn- url->map [u]
  (if (map? u) u
    (let [o  (.parse url u)]
      {:hostname (.-hostname o)
       :protocol (.-protocol o)
       :path (.-path o)
       :port (.-port o)
       :auth (.-auth o)})))

(defn- read-data [ch]
  (go-loop [result ""]
           (if-let [r (<! ch)]
             (recur (str result r))
             (if (re-matches #"^ERROR.*" result)
               [result nil]
               [nil result]))))

(defn- data-callback-fn [ch]
  (fn [res]
    (when (< 200 res.statusCode)
      (async/put! ch (str "ERROR-" res.statusCode)))
    (.on res "data" #(async/put! ch %))
    (.on res "end"  #(async/close! ch))))

(defn- client [protocol]
  (if (= "https:" protocol)
    https
    http))

(defn request
  ([url]
   (request :get url nil))
  ([method url]
   (request method url nil))
  ([method url data]
   (let [ch (async/chan)
         opts (assoc (url->map url) :method method)
         rq (.request (client (:protocol opts))
                      (clj->js opts)
                      (data-callback-fn ch))]
     (when data
       (.write rq data))
     (.end rq)
     (read-data ch))))

(defn json->map [t]
  (t/read (t/reader :json) t))

(defn map->json [o]
  (t/write (t/writer :json-verbose) o))
