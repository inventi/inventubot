(ns inventi.hubot
  (:require [inventi.books :as books]
            [inventi.contacts :as contacts]))

(defn init-scripts [bot]
  (.respond bot "/add book (.*)/i" books/respond-add-book)
  (.respond bot "/get contacts/i" contacts/respond-get-contacts))

(defn -main []
  (println "inventi-scripts initialized"))

(set! (.-exports js/module) (fn [bot] (init-scripts bot)))

(set! *main-cli-fn* -main)
