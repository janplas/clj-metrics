(ns clj-metrics.defn)

(defn has-comment?
  "Returns true if func has a comment string, otherwise false"
  [func]
  ; Fixe me: this will fail if the defn has type hints for the return value
  (string? (nth func 2)))
