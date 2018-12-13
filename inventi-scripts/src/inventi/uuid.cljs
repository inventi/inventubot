(ns inventi.uuid)

(defn random [res]
  (.send res (str (random-uuid))))
