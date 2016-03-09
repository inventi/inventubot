(ns inventi.hubot
  (:require [inventi.books :as books]
            [inventi.contacts :as contacts]
            [inventi.voting :as voting]))

(defn init-scripts [bot]
  (.respond bot "/add book (.*)/i" books/respond-add-book)
  (.respond bot "/contacts/i" contacts/respond-contacts)
  (.respond bot "/roll ?(.*)/i" voting/roll))

(defn -main []
  (println "inventi-scripts initialized"))

(set! (.-exports js/module) (fn [bot] (init-scripts bot)))

(set! *main-cli-fn* -main)
