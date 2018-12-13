(ns inventi.contacts)

(defn respond-contacts [res]
  (.send res (str "UAB \"INVENTI\"\n"
                  "Lvovo g. 105A, 3 a., LT-08104 Vilnius\n"
                  "CODE: 302641851\n"
                  "VAT: LT100006288910\n")))

