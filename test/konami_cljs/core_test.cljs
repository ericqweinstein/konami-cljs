(ns ^{:doc "Tests for konami-cljs.core."
      :author "Eric Weinstein <eric.q.weinstein@gmail.com>"}
  konami-cljs.core-test
  (:require [cljs.test :refer-macros [async deftest is testing]]
            [konami-cljs.core :refer [take-last-ten handle]]))

(def test-vec
  (vec (take 12 (map inc (range)))))

(def test-value
  (atom "foo"))

(deftest test-take-last-ten
  (testing "gets the last 10 elements in a vector"
    (is (= [3 4 5 6 7 8 9 10 11 12] (take-last-ten test-vec)))))

;; TODO: Test modifying document.location. (EQW 25 Sep 2015)
(deftest test-handle-fn
  (testing "executes callback function when passed a function"
    (handle #(reset! test-value "bar"))
    (is (= "bar" @test-value))))

;; TODO: End-to-end test the konami function call. (EQW 25 Sep 2015)
