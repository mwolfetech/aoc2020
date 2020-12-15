(ns password-philosophy.core
  (:gen-class)
  (:require [password-philosophy.base :as base]
            [clojure.pprint :as pp]
            [clojure.string :as str]
            [com.rpl.specter :as specter]
            [clojure.string :as string]))

(defn input
  "read problem input into list of strings"
  []
  (-> (slurp "data/input.in")
      (str/split-lines)))


(defn parse
  "parse string into map key/values based on regex pattern"
  [s]
  (zipmap
   [:start
    :end
    :letter
    :password]
   (->> s
        (re-find #"([0-9]+)-([0-9]+) ([a-z]): ([a-z]+)")
        rest)))

(defn structured-input
  "convert input to data structures"
  []
  (specter/transform
    [specter/ALL (specter/multi-path :start :end)]
    #(Integer/parseInt %)
    (map parse (input))))

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


