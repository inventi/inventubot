(ns inventi.contacts)

(defn respond-contacts [res]
  (.send res (str "UAB \"INVENTI\"\n"
                  "Verkių g. 25C, 2 a., LT-08223 Vilnius\n"
                  "CODE: 302641851\n"
                  "VAT: LT100006288910\n")))

