(ns inventi.hubot
  (:require [inventi.books :as books]))

(defn init-scripts [bot]
  (.respond bot "/add book (.*)/i" books/respond-add-book))

(defn -main []
  (println "inventi-scripts initialized"))

(set! (.-exports js/module) (fn [bot] (init-scripts bot)))

(set! *main-cli-fn* -main)
