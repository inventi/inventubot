(defproject inventi-scripts "0.1.0-SNAPSHOT"
  :description "Set of inventi specific scripts for hubot"
  :url "https://github.com/inventiLT/hubot-inventi"

  :min-lein-version "2.5.3"

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.170"]
                 [com.cognitect/transit-cljs  "0.8.237"]
                 [org.clojure/core.async  "0.2.374"]]

  :plugins [[lein-cljsbuild "1.1.1"]
            [lein-figwheel "0.5.0-2"]]

  :source-paths ["src"]

  :clean-targets ["app/index.js"
                  "target"]

  :cljsbuild {
    :builds [{:id "dev"
              :source-paths ["src"]
              :figwheel true
              :compiler {
                :main inventi.hubot
                :output-to "target/server_dev/inventi_scripts.js"
                :output-dir "target/server_dev"
                :target :nodejs
                :optimizations :none
                :source-map true}}
             {:id "prod"
              :source-paths ["src"]
              :compiler {
                :output-to "app/index.js"
                :output-dir "target/server_prod"
                :target :nodejs
                :optimizations :simple
                :hashbang false
                :preamble  ["help.js"]}}]})
