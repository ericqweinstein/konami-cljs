(ns ^{:doc "Test runner for headless browser testing."
      :author "Eric Weinstein <eric.q.weinstein@gmail.com>"}
  konami-cljs.test.test-runner
  (:require
   [cljs.test :as test :refer-macros [run-tests] :refer [report]]
   [konami-cljs.core-test]))

(enable-console-print!)

(defmethod report [::test/default :summary] [m]
  (println "\nRan" (:test m) "tests containing"
           (+ (:pass m) (:fail m) (:error m)) "assertions.")
  (println (:fail m) "failures," (:error m) "errors.")
  (aset js/window "test-failures" (+ (:fail m) (:error m))))

(defn runner []
  (test/run-tests
   (test/empty-env ::test/default)
   'konami-cljs.core-test))
