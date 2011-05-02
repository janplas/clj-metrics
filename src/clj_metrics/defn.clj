(ns clj-metrics.defn
  (:require [clojure.contrib.string :as str-utils]))

(defn is-defn?
  "Return true if this decl defines a function, otherwise false"
  [decl]
  (= (symbol "defn") (first decl)))

; fix me: we probably don't want to call eval every time we need something from a
; function
(defn get-comment
  "Get documentation string"
  [func]
  (-> func eval meta :doc))

(defn has-comment?
  "Returns true if func has a comment string, otherwise false"
  [func]
  (not (str-utils/blank? (get-comment func))))
