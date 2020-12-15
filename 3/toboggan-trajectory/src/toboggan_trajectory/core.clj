(ns toboggan-trajectory.core
  (:gen-class)
  (:require [toboggan-trajectory.base :as base]
            [clojure.pprint :as pp]
            [com.rpl.specter :as specter]
            [clojure.string :as str]))

(defn input
  "read problem input as string"
  []
  (-> (slurp "data/input.in")
      (str/split-lines)))

(defn parse
  "parse string into map key/values based on regex pattern"
  [s]
  (vec (map #(case %
               \. 0
               \# 1) s)))


(defn structured-input
  "convert input to data structure()"
  []
  (vec (map parse (input))))

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


