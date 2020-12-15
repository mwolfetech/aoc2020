(ns report-repair.base
  (:gen-class)
  (:require [clojure.core.async :as async]
            [clojure.core.matrix :as matrix]
            [clojure.math.numeric-tower :as numeric]
            [clojure.pprint :as pp]
            [clojure.string :as string]
            [com.rpl.specter :as specter]))

(defn sum2020? 
  "do the two integers sum to 2020"
  [x y z]
  (= 2020 (+ x y z)))


(defn find-match
  "search items of a collection for two values that meet predicate function f"
  [f coll]
  (let [idxcoll (zipmap coll (range))]
    (for [[x i] idxcoll
          [y j] idxcoll
          [z k] idxcoll
          :when (and (< i j k) (apply f [x y z]))]
      [x y z])))

(defn post-process
  "often advent of code requires a final step before posting your answer, this function does that simplification"
  [ans]
  (apply * ans))

(defn process
  "process input"
  [input]
  (let [f (partial find-match sum2020?)]
    (-> input
        (f)
        (first)
        (post-process))))
