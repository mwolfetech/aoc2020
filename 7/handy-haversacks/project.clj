(defproject handy-haversacks "0.1.0-SNAPSHOT"
  :description "Advent of Code Problem"
  :url "https://github.com/mwolfetech/aoc2020"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [com.rpl/specter "1.1.3"]
                 [net.mikera/core.matrix "0.62.0"]
                 [org.clojure/core.async "1.3.610"]
                 [org.clojure/math.numeric-tower "0.0.4"]
                 [quil "3.1.0"]
                 [org.clojure/core.logic "1.0.0"]]
  :main ^:skip-aot handy-haversacks.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
