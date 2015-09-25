(defproject org.clojars.eqw/konami-cljs "0.1.0-SNAPSHOT"
  :description "A little Konami code library for ClojureScript."
  :url "https://github.com/ericqweinstein/konami-cljs"
  :license {:name "MIT"
            :url "https://opensource.org/licenses/MIT"}

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.122"]]

  :plugins [[lein-cljsbuild "1.1.0"]
            [lein-figwheel "0.4.0"]]

  :profiles {:uberjar {:aot :all}
             :dev {:source-paths ["src"]
                   :plugins [[codox "0.8.13"]
                             [lein-ancient "0.6.7"]
                             [lein-bikeshed "0.2.0"]
                             [lein-kibit "0.1.2"]]}}

  :aliases {"lint" ^{:doc "Lint all the things"}
            ["do" "ancient," "kibit," "bikeshed", "-m80", "-v"]
            "run-tests" ^{:doc "Lint and test all the things"}
            ["do" "lint," "clean," "cljsbuild" "once" "test"]}

  :cljsbuild {
   :builds [{:id "dev"
             :source-paths ["src"]
             :figwheel true
             :compiler {:main konami-cljs.core
                        :asset-path "js/compiled/out"
                        :output-to "resources/public/js/compiled/konami.js"
                        :output-dir "resources/public/js/compiled/out"
                        :source-map-timestamp true}}
            {:id "test"
             :source-paths ["src" "test"]
             :notify-command ["phantomjs" "resources/test.js" "resources/test.html"]
             :compiler {:output-to "target/js/test.js"
                        :optimizations :whitespace
                        :pretty-print true}}]})
