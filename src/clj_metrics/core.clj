(ns clj-metrics.core
  (:require [clojure.string :as str]
            [clojure.contrib.string :as str-utils])
  (:import (java.io File)))

(defn read-source
  "Read file"
  [file]
  (slurp (.getPath file)))

(defn nr-of-lines
  "Get number of lines for the given source"
  [source]
  (count (str/split-lines source)))

(defn nr-of-blank-lines
  "Get number of blank lines for the given source"
  [source]
  (nr-of-lines (filter str-utils/blank? (str/split-lines source))))

(def clj-pattern #"^[^.](.*)\.clj")

(defn clojure-file? [file]
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

(defn get-nr-of-lines [seq]
  (map #(nr-of-lines (% :src)) seq))

(defn get-paths [seq]
  "Returns all path names"
  (map :path seq))

(defn- create-ast
  "Create a kind of abstract syntax tree"
  [source]
  (read-string (str "(" source ")")))

(defn nr-of-toplevel-symbols
  [ast symbol-name]
  (count (filter #(= (symbol symbol-name) (first %)) ast)))

(defn nr-of-defns
  "Count functions in file" 
  [ast]
  (nr-of-toplevel-symbols ast "defn"))

(defn get-nr-of-defns [seq]
  (map #(nr-of-defns (create-ast (% :src))) seq))

(defn -main [& [args]]
  (let [dir (if args args ".")
        clj-files (read-all-clj-files dir)]
    (println (get-paths clj-files))
    (println (get-nr-of-lines clj-files))
    (println (get-nr-of-defns clj-files))))
