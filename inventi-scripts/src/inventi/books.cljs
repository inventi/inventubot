(ns inventi.books
  (:require [cljs.nodejs :as nodejs]
            [cljs.core.async :as async :refer [<!]]
            [clojure.string :as st]
            [inventi.util :refer [json->map map->json request]])
  (:require-macros  [cljs.core.async.macros :refer [go]]))

(nodejs/enable-util-print!)

(def ok-text "I've added book to the list. Peace!")
(def err-text "I've tried to add your book, but not sure whether it worked. Thats what i got from the server: ")
(def redmine
  {:protocol "https:"
   :hostname "redmine.inventi.lt"
   :path "/projects/inventi/wiki/Knygos.json"
   :headers {:Content-Type "application/json"}
   :auth process.env.BOOKS_AUTH})

(defn book-name [url]
  (go
    (let [[e content] (<!(request url))]
      (when (nil? e)
        (-> (subs content
                  (+ 9 (.indexOf content "<title>"))
                  (.indexOf content "</title>"))
            (st/split #":")
            (as-> s (st/join ":" (take 2 s))))))))

(defn insert-book [books book]
  (let [items (st/split books #"\*")]
    (->>
      (conj
        (rest items)
        (str " " book "\r\n")
        (first items))
      (st/join "*"))))

(defn add-book [text book]
  (let [[head books & other] (st/split text #"h3\.")]
    (->>
      (conj
        other (insert-book books book) head)
      (st/join "h3."))))

(defn books-text []
  (go
    (let [[e page] (<!(request redmine))]
      (if-not e
        (get-in (json->map page) ["wiki_page" "text"])
        e))))

(defn register-book [new-book]
  (go
    (let [text (<!(books-text))]
      (if (< -1 (.indexOf text new-book))
             ["this book already exist!" nil]
        (<!(->>
             (str (<!(book-name new-book)) " - " new-book)
             (add-book text)
             (hash-map "text")
             (hash-map "wiki_page")
             (map->json)
             (request :put redmine)))))))

(defn respond-add-book [res]
  (async/take!
    (register-book (second res.match))
    (fn [[e result]]
      (.send res
             (if (nil? e) ok-text (str err-text e))))))

