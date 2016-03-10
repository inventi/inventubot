(ns inventi.voting
  (:require [inventi.util :refer [string->int]]))

(defn roll [res]
  (let [value (string->int (second res.match))
       max-val (if (and (integer? value)
                       (> value 0)) value 100)]
    (.send res (str "Rolling a number [1-" max-val "]: " (+ 1 (rand-int max-val))))))

