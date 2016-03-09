(ns inventi.voting
  (:require [inventi.util :refer [string->int]]))

(defn roll [res]
  (let [value (string->int (second res.match))
       max-val (if (integer? value) value 100)]
    (.send res (str "Rolling a number [1-" max-val "]: " (+ 1 (rand-int max-val))))))

