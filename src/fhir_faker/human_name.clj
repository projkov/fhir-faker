(ns fhir-faker.human_name
  "A name of a human with text, parts and usage information."
  (:require [fhir-faker.human_name_data :as hnd]))

(defn human-name-use-code
  "The use of a human name"
  ([] (rand-nth hnd/use-codes))
  ([code]
   (if (= code (some #{code} hnd/use-codes))
     (str code)
     (throw (ex-info
             "You can not use this value as use code for the HumanName"
             {:input code :expected-one-of hnd/use-codes})))))

(comment
  (human-name-use-code)

  (human-name-use-code "official")

  (human-name-use-code "test")

  )

(defn family-name
  "Family name (often called 'Surname')"
  ([] (rand-nth hnd/family-names))
  ([family-name]
   (if (= true (instance? String family-name))
     family-name
     (throw (ex-info
             "You can not use this type of the variable for the family name"
             {:input family-name :expected java.lang.String})))))

(comment
  (family-name)

  (family-name "John")

  (family-name 1)

  )

;; TODO: Generate a list of first-names
(defn given-name
  "Given names (not always 'first'). Includes middle names.
  This repeating element order: Given Names appear in the correct order
  for presenting the name"
  ([] [(rand-nth hnd/given-names)])
  ([given-name]
   (if (= true (instance? String given-name))
     [given-name]
     (throw (ex-info
            "You can not use this type of the variable for the given name"
            {:input given-name :expected java.lang.String})))))

(comment
  (given-name)

  (given-name "Juwan")

  (given-name 1)

  )

(defn prefix
  ""
  ([] [(rand-nth hnd/prefixes)])
  ([preff]
   (if (= preff (some #{preff} hnd/prefixes))
     [(str preff)]
     (throw (ex-info
             "You can not use this value as prefix for the HumanName"
             {:input preff :expected-one-of hnd/prefixes})))))

(comment
  (prefix)

  (prefix "Mr.")

  (prefix "PhD")
  )

(defn suffix
  "Parts that come after the name.
  This repeating element order: Suffixes appear in the correct order
  for presenting the name"
  ([] [(rand-nth hnd/suffixes)])
  ([suff]
   (if (= suff (some #{suff} hnd/suffixes))
     [(str suff)]
     (throw (ex-info
             "You can not use this value as suffix for the HumanName"
             {:input suff :expected-one-of hnd/suffixes})))))

(comment
  (suffix)

  (suffix "PhD")

  (suffix "Doctor")

  )

(defn human-name
  "Name of a human - parts and usage"
  []
  (def use-var (human-name-use-code))
  (def family-name-var (family-name))
  (def given-name-var (given-name))
  (def prefix-var (prefix))
  (def suffix-var (suffix))
  (def text-var (clojure.string/join " " (vec (flatten [prefix-var given-name-var family-name-var suffix-var]))))
  {:use use-var
   :text text-var
   :family family-name-var
   :given given-name-var
   :prefix prefix-var
   :suffix suffix-var})

(comment
  (human-name)

  )












