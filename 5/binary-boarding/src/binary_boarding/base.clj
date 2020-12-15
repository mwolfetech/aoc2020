(ns binary-boarding.base
    (:gen-class)
     (:require [clojure.core.async :as async]
               [clojure.core.matrix :as matrix]
               [clojure.math.numeric-tower :as numeric]
               [clojure.pprint :as pp]
               [clojure.string :as string]
               [com.rpl.specter :as specter]))

(defn gap-analysis
  "determine if two numbers are gapped by 1"
  [[a b]]
  (if (= (+ a 2) b) (inc a) :invalid))

(defn post-process
  "often advent of code requires a final step before posting your answer, this function does that simplification"
  [ans]
  (->> ans 
    (sort)
    (partition 2 1)
    (map gap-analysis)
    (remove keyword?)
    (first)))

(defn locate [coll start end upper lower]
  (reduce (fn [res letter]
            (let [sz (quot (count res) 2)]
                 (condp = letter
                  upper (drop sz res)
                  lower (take sz res))))
          (range start end)
          coll))

(defn boarding-pass-to-seat-ID
  "read boarding pass and return row seat"
  [coll]
  (+ (* 8 (first (locate (take 7 coll) 0 128 \B \F)))
      (first (locate (drop 7 coll) 0 8 \R \L))))

(defn process
  "process input already structured"
  [input]
  (->> input
       (map boarding-pass-to-seat-ID)
      (post-process)))
