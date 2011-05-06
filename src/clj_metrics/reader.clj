(ns clj-metrics.reader
  (:import (java.io File)))

(defn read-source
  "Read file"
  [file]
  (slurp (.getPath file)))

(def clj-pattern #"^[^.](.*)\.clj")

(defn clojure-file? [file]
  "Returns true if file is a Clojure file, otherwise false"
  (re-matches clj-pattern (.getName file)))

(defn get-all-clj-files
  "Recursively find all clj files in given dir"
  [dir]
  (filter clojure-file? (-> dir File. file-seq)))

(defn- create-source-info
  "Create map with source info"
  [file]
  (let [path (.getPath file)]
    {:src (read-source file) :path path}))

(defn read-all-clj-files [dir]
  (map create-source-info (get-all-clj-files dir)))
