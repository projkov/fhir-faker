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
  ([] (rand-nth hnd/last-names))
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
