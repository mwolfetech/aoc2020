(ns handy-haversacks.base
    (:gen-class)
     (:require [clojure.core.async :as async]
               [clojure.core.matrix :as matrix]
               [clojure.math.numeric-tower :as numeric]
               [clojure.pprint :as pp]
               [clojure.string :as string]
               [com.rpl.specter :as specter]
               [clojure.core.logic :as logic]
               [clojure.core.logic.pldb :as pldb]))

(defn post-process
  "often advent of code requires a final step before posting your answer, this function does that simplification"
  [ans]
  (count (distinct ans)))

(pldb/db-rel ino p1 p2)

(defn containo [x y]
    (logic/fresh [z]
    (ino x z)
    (logic/membero y z)))

(defn containo* [x y]
  (logic/fresh [z]
    (logic/conde [(containo x y)]
                 [(containo x z)(containo* z y)])))

(defn coll->db
  "convert collection into "
  [coll]
  (reduce (fn [db [x a]]
            (pldb/db-fact db ino x a))
            pldb/empty-db
            coll))

(defn apply-logic
  "calculate possibilities"
  [input]
  (let [facts (coll->db input)]
    (logic/run-db* facts [q]
        (containo* q :shiny_gold))))

(defn process
  "process input already structured"
  [input]
  (->> input
      (apply-logic)
      (post-process)))
