(ns passport-processing.base
    (:gen-class)
     (:require [clojure.core.async :as async]
               [clojure.core.matrix :as matrix]
               [clojure.math.numeric-tower :as numeric]
               [clojure.pprint :as pp]
               [clojure.string :as string]
               [com.rpl.specter :as specter]
               [clojure.spec.alpha :as spec]))

(defn in-range [min max v]
  (<= min (Integer/parseInt v) max))

(defn height-in-range [min max s]
   (->> s
        (re-find #"[0-9]+")
        (in-range min max)))

(spec/def ::byr (spec/and string? #(re-matches #"[0-9]+" %) #(in-range 1920 2002 %)))
(spec/def ::iyr (spec/and string? #(re-matches #"[0-9]+" %) #(in-range 2010 2020 %)))
(spec/def ::eyr (spec/and string? #(re-matches #"[0-9]+" %) #(in-range 2020 2030 %)))
(spec/def ::hgt (spec/and string? (spec/or :centimeters (spec/and #(re-matches #"[0-9]+cm" %) #(height-in-range 150 193 %))
                                           :inches (spec/and #(re-matches #"[0-9]+in" %) #(height-in-range 59   76 %)))))
(spec/def ::hcl (spec/and string? #(re-matches #"#[0-9a-f]{6}" %)))
(spec/def ::ecl (spec/and string? #(re-matches #"(amb|blu|brn|gry|grn|hzl|oth)" %)))
(spec/def ::pid (spec/and string? #(re-matches #"[0-9]{9}" %)))
(spec/def ::cid string?)

(spec/def ::passport (spec/keys :req-un [::byr ::iyr ::eyr ::hgt ::hcl ::ecl ::pid] ::opt-un [::cid]))

(defn post-process
  "often advent of code requires a final step before posting your answer, this function does that simplification"
  [ans]
  ans)

(defn process
  "process input already structured"
  [input]
  (->> input
       (filter (partial spec/valid? ::passport))
       (count)
      (post-process)))


