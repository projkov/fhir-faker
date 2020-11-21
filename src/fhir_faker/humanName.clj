(ns fhir-faker.humanName
  "A name of a human with text, parts and usage information."
  (:require [fhir-faker.humanNameData :as hnd]))

(defn use
  "The use of a human name"
  []
  (rand-nth hnd/use-codes))

(use)
