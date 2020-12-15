(ns custom-customs.base
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

(defn count-unanimous-votes
  "count votes where everyone voted"
  [ballot]
  (let [people (->> ballot
                   (filter #{\newline})
                   (count))
        votes (->> ballot
                  (remove #{\newline})
                  (frequencies)
                  (seq))]
    (->> votes
        (filter #(= (second %) people))
        (map first)
        (count))))

(defn process
  "process input already structured"
  [input]
  (->> input
       (map count-unanimous-votes)
       (reduce +)
      (post-process)))


