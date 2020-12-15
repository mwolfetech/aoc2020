(ns binary-boarding.core
    (:gen-class)
     (:require [binary-boarding.base :as base]
               [clojure.pprint :as pp]
               [clojure.string :as str]
               [com.rpl.specter :as specter]))

(defn input
  "read problem input as string"
  []
  (-> (slurp "data/input.in")
      (str/split-lines)))


(defn parse
  "parse string input"
  [s]
  (-> s
    (char-array)))

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

