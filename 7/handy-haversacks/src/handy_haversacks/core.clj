(ns handy-haversacks.core
    (:gen-class)
     (:require [handy-haversacks.base :as base]
               [clojure.pprint :as pp]
               [clojure.string :as str]
               [com.rpl.specter :as specter]))

(defn input
  "read problem input as string"
  []
  (-> (slurp "data/sample.in")
      (str/split-lines)))


(defn parse
  "parse string input"
  [s]
  (->> s
       (re-seq #"([0-9]*)(?:\s*)(\w+ \w+) bags?") 
       (map rest)
       (specter/transform [specter/ALL specter/FIRST] (fn [s] (if (str/blank? s) 1 (Integer/parseInt s))))
       (map (comp keyword #(str/replace % " " "_") second))
       (remove #{:no_other})
       (apply (fn [x & ys] [x (vec ys)]))

  ))

(defn structured-input
  "convert input to data structures"
  []
  (->> (input)
       (map parse)))

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


