(ns inventi.uuid)

(defn random [res]
  (.send res (random-uuid)))
