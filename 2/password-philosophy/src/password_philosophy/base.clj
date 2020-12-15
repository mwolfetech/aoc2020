(ns password-philosophy.base
    (:gen-class)
     (:require [clojure.core.async :as async]
               [clojure.core.matrix :as matrix]
               [clojure.math.numeric-tower :as numeric]
               [clojure.pprint :as pp]
               [clojure.string :as string]
               [com.rpl.specter :as specter]))

(defn post-process
  "often advent of code requires a final step before posting your answer, this function does that simplification"
  [ans]
  ans)

(defn valid?
  "check password has the correct number of letters"
  [m]
  (let [{:keys [:letter :start :end :password]} m
        check1 (nth password (dec start))
        check2 (nth password (dec end))]
    (= 1 (count (filter #(= true %) [(= (str check1) letter) (= (str check2) letter)])))))


(defn count-true
  "count number of of times predicate returns true"
  [f coll]
  (->> (map f coll)
       (filter identity)
       (count)))

(defn process
  "process input"
  [input]
  (->> input
      (count-true valid?)
      (post-process)))


