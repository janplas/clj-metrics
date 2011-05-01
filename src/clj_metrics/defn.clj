(ns clj-metrics.defn)

(defn is-defn?
  "Return true if this decl defines a function, otherwise false"
  [decl]
  (= (symbol "defn") (first decl)))

(defn has-comment?
  "Returns true if func has a comment string, otherwise false"
  [func]
  ; Fixe me: this will fail if the defn has type hints for the return value
  (string? (nth func 2)))
