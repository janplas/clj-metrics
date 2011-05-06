(ns clj-metrics.core
  (:use [clj-metrics.util] [clj-metrics.defn] [clj-metrics.reader])
  (:require [clojure.string :as str]
            [clojure.contrib.string :as str-utils]
            [org.danlarkin.json :as json]))

(defn nr-of-lines
  "Get number of lines for the given source"
  [source]
  (count (str/split-lines source)))

(defn nr-of-blank-lines
  "Get number of blank lines for the given source"
  [source]
  (nr-of-lines (filter str-utils/blank? (str/split-lines source))))

(defn get-nr-of-lines [seq]
  (map #(nr-of-lines (% :src)) seq))

(defn get-paths [seq]
  "Returns all path names"
  (map :path seq))

(defn- create-ast
  "Create a kind of abstract syntax tree"
  [source]
  (read-string (str "(" source "\n)")))

(defn nr-of-toplevel-symbols
  [ast symbol-name]
  (count (filter #(= (symbol symbol-name) (first %)) ast)))

(defn get-all-defns
  "Get all functions defined in this ast"
  [ast]
  (filter is-defn? ast))

(defn nr-of-defns
  "Count functions in file" 
  [ast]
  (count (get-all-defns ast)))

(defn nr-of-commented-defns
  "Count functions with comment string in file" 
  [ast]
  (count-if has-comment? (get-all-defns ast)))
  
(defn get-nr-of-defns [seq]
  (map #(nr-of-defns (create-ast (% :src))) seq))

(defn get-nr-of-commented-defns [seq]
  (map #(nr-of-commented-defns (create-ast (% :src))) seq))

(defn dump-json [clj-files]
  (println (json/encode (merge-seq [:path (get-paths clj-files)]
                                   [:loc (get-nr-of-lines clj-files)]
                                   [:defns (get-nr-of-defns clj-files)]
                                   [:cdefns (get-nr-of-commented-defns clj-files)])
                        :indent 2)))

(defn -main [& [args]]
  (let [dir (if args args ".")
        clj-files (read-all-clj-files dir)]
    (dump-json clj-files)))
