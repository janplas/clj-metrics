(ns clj-metrics.test.util
  (:use [clj-metrics.util] :reload)
  (:use [clojure.test]))

(deftest test-count-if
  (is (= (count-if #(< % 3) (range 7)) 3))
  (is (= (count-if #(>= % 3) (range 7)) 4)))

