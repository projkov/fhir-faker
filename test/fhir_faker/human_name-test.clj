(ns fhir-faker.human_name-test
  (:require [fhir-faker.human_name :refer :all]
            [fhir-faker.human_name_data :as hnd]
            [clojure.test :refer :all]))

(deftest test-use-code
  (testing "Generate human name code manually"
    (is (= (fhir-faker.human_name/human-name-use-code "nickname") "nickname")))
  (testing "Generate random human name code"
    (is (not= (fhir-faker.human_name/human-name-use-code) nil)))
  (testing "Exception with uncorrect code"
    (is (thrown? Exception (fhir-faker.human_name/human-name-use-code "Some"))))
  (testing "Exception with uncorrect data"
    (is (thrown? Exception (fhir-faker.human_name/human-name-use-code 1)))))

(deftest test-family-name
  (testing "Generate family name manually with correct data"
    (is (= (fhir-faker.human_name/family-name "Smith") "Smith")))
  (testing "Generate random family name (check result is present)"
    (is (not= (fhir-faker.human_name/family-name) nil)))
  (testing "Generate random family name (check result type)"
    (is (instance? String (fhir-faker.human_name/family-name))))
  (testing "Generate random family name (check present of result in source list)"
    (is (some #{(fhir-faker.human_name/family-name)} hnd/last-names))))
