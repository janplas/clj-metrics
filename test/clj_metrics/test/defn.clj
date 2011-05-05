(ns clj-metrics.test.defn
  (:use [clj-metrics.defn] :reload)
  (:use [clojure.test]))

(def *tree1* '(defn foo "This is foo!" [x] (* x x)))
(def *tree2* '(defmacro foo "This is foo!" [x] (* x x)))
(def *tree3* '(defn foo [x] (* x x)))

(deftest test-is-defn?
  (is (is-defn? *tree1*))
  (is (not (is-defn? *tree2*))))

(deftest test-get-comment
  (is (= (get-comment *tree1*) "This is foo!")))

(deftest test-has-comment
  (is (has-comment? *tree1*))
  (is (not (has-comment? *tree3*))))