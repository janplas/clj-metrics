(defproject clj-metrics "1.0.0-SNAPSHOT"
  :description "Clojure static source code analysis tool"
  :main clj-metrics.core
  :run-aliases {:clj-metrics [clj-metrics.core -main]}
  :dependencies [[org.clojure/clojure "1.2.1"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [org.danlarkin/clojure-json "1.2-SNAPSHOT"]]
  :dev-dependencies [[swank-clojure "1.3.0"]
                     [lein-run "1.0.0"]]
)
