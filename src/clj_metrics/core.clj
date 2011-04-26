(ns clj-metrics.core
  (:require [clojure.string :as str]
            [clojure.contrib.string :as str-utils]
            [clojure.walk :as walk])
  (:import (java.io File)))

(defn read-source
  "Read file"
  [file]
  (str/split-lines (slurp (.getPath file))))

(defn nr-of-lines
  "Get number of lines for the given source"
  [source]
  (count source))

(defn nr-of-blank-lines
  "Get number of blank lines for the given source"
  [source]
  (nr-of-lines (filter str-utils/blank? source)))

(def clj-pattern #".*\.clj")

(defn clojure-file? [file]
  (re-matches clj-pattern (.getName file)))

(defn get-all-clj-files
  "Recursively find all clj files in given dir"
  [dir]
  (filter clojure-file? (-> dir File. file-seq)))

(defn read-all-clj-files [dir]
  (map (fn [file] {:src (read-source file)}) (get-all-clj-files dir)))

(defn dump-nr-of-lines [seq]
  (map #(nr-of-lines (% :src)) seq))
