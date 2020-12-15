(ns passport-processing.core
    (:gen-class)
     (:require [passport-processing.base :as base]
               [clojure.pprint :as pp]
               [clojure.string :as str]
               [com.rpl.specter :as specter]))

(defn input
  "read problem input as string"
  []
  (-> (slurp "data/input.in")
      (str/split #"\r?\n\r?\n")))

(defn parse
  "parse string input"
  [s]
  (as-> s $
      (str/trim-newline $)
      (str/replace $ #"\r?\n" " ")
      (re-seq #"(?:([a-z]{3}):([A-Za-z0-9#]+)\s?)" $)
      (specter/select [specter/ALL (specter/multi-path 1 2)] $)
      (partition 2 $)
      (map (fn [[k v]] (hash-map (keyword k) v)) $)
      (apply merge $)))

(defn structured-input
  "convert input to data structures"
  []
  (->> (input)
       (map parse)
       (vec)))

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
