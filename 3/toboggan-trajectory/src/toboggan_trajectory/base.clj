(ns toboggan-trajectory.base
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
  (reduce *' (map bigint ans)))

(defn run
  "return indices for run part of slope"
  [m run-increment]
  (let [[height width] (matrix/shape m)]
    (take height (take-nth run-increment (map #(mod % width) (iterate inc 0))))))

(defn rise
  "return indices for rise part of slope"
  [m increment]
  (let [[height _] (matrix/shape m)]
  (take-nth increment (range 0 height))))

(defn ski
  "ski"
  [m [run-increment rise-increment]]
  (let [ys (rise m rise-increment)
        xs (run m run-increment)
        coords (partition 2 (interleave ys xs))]
    (->> (for [p coords] (specter/select p m))
        (flatten)
        (reduce +))))

(defn process
  "process input"
  [input]
  (->
   (for [slopes [[1 1] [3 1] [5 1] [7 1] [1 2]]]
     (-> input
          (ski slopes)))
   (post-process)))
