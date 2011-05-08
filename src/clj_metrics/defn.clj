(ns clj-metrics.defn
  (:require [clojure.contrib.string :as str-utils]))

(defn is-defn?
  "Return true if this decl defines a function, otherwise false"
  [decl]
  (= (symbol "defn") (first decl)))

(defn has-comment?
  "Returns true if func has a comment string, otherwise false"
  [func]
  (string? (nth func 2)))

(defn get-comment
  "Return a function's documentation string"
  [func]
  (if (has-comment? func) (nth func 2)))

