(ns report-repair.core
  (:gen-class)
  (:require [report-repair.base :as base]
            [clojure.pprint :as pp]
            [clojure.string :as str]))

(defn input
  "read problem input as string"
  []
  (slurp "data/input.in"))

(defn structured-input
  "convert input to data structures"
  []
  (vec (map #(Integer/parseInt %)
  (-> (input)
      (str/split-lines)))))

(defn solve
  "solve problem"
  []
  (-> (structured-input)
      (base/process)))

(defn view
  "pprint structured input"
  []
  (pp/pprint (structured-input)))

(defn -main [& _args]
  (println (solve)))


