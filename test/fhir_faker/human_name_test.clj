(ns fhir-faker.human_name_test
  (:require [fhir-faker.human_name :as hn]
            [fhir-faker.human_name_data :as hnd]
            [clojure.test :refer :all]))

(defmacro catch-thrown-info [f]
  `(try
     ~f
     (catch
         clojure.lang.ExceptionInfo e#
       {:msg (ex-message e#) :data (ex-data e#)})))

(deftest test-use-code
  (testing "Generate human name code manually"
    (is (= (hn/human-name-use-code "nickname") "nickname")))
  (testing "Generate random human name code"
    (is (not= (fhir-faker.human_name/human-name-use-code) nil)))
  (testing "Exception with uncorrect code"
    (is (=
         (catch-thrown-info
          (throw
           (ex-info "You can not use this value as use code for the HumanName"
                    {:input "Some" :expected-one-of hnd/use-codes})))
         (catch-thrown-info (fhir-faker.human_name/human-name-use-code "Some")))))
  )

(deftest test-family-name
  (testing "Generate family name manually with correct data"
    (is (= (fhir-faker.human_name/family-name "Smith") "Smith")))
  (testing "Generate random family name (check result is present)"
    (is (not= (fhir-faker.human_name/family-name) nil)))
  (testing "Generate random family name (check result type)"
    (is (instance? String (fhir-faker.human_name/family-name))))
  (testing "Generate random family name (check present of result in source list)"
    (is (some #{(fhir-faker.human_name/family-name)} hnd/family-names)))
  (testing "Exception with incorrect data type"
    (is (=
         (catch-thrown-info
          (throw
           (ex-info "You can not use this type of the variable for the family name"
                    {:input 1 :expected java.lang.String})))
         (catch-thrown-info (fhir-faker.human_name/family-name 1)))))
  )

(deftest test-given-name
  (testing "Generate given name manually with correct data"
    (is (= (fhir-faker.human_name/given-name "Maria") ["Maria"])))
  (testing "Generate random given name (check result is present)"
    (is (not= (fhir-faker.human_name/given-name) nil)))
  (testing "Generate random given name (check result type)"
    (is (instance? java.util.Collection (fhir-faker.human_name/given-name))))
  (testing "Exception with incorrect data type"
    (is (=
         (catch-thrown-info
          (throw
           (ex-info "You can not use this type of the variable for the given name"
                    {:input 1 :expected java.lang.String})))
         (catch-thrown-info (fhir-faker.human_name/given-name 1)))))
  )

(deftest test-prefixes
  (testing "Generate random prefix (check result is present)"
    (is (not= (fhir-faker.human_name/prefix) nil)))
  (testing "Generate random prefix (check result type)"
    (is (instance? java.util.Collection (fhir-faker.human_name/prefix))))
  (testing "Generate prefix manually"
    (is (= (fhir-faker.human_name/prefix "Mr.") ["Mr."])))
  (testing "Exception with generate manually prefix with incorrect data"
    (is (=
         (catch-thrown-info
          (throw
           (ex-info "You can not use this value as prefix for the HumanName"
                    {:input "PhD." :expected-one-of hnd/prefixes})))
         (catch-thrown-info (fhir-faker.human_name/prefix "PhD."))))))

(deftest test-suffixes
  (testing "Generate random suffix (check result is present)"
    (is (not= (fhir-faker.human_name/suffix) nil)))
  (testing "Generate random suffix (check result type)"
    (is (instance? java.util.Collection (fhir-faker.human_name/suffix))))
  (testing "Generate suffix manually"
    (is (= (fhir-faker.human_name/suffix "PhD") ["PhD"])))
  (testing "Exception with generate manually suffix with incorrect data"
    (is (=
         (catch-thrown-info
          (throw
           (ex-info "You can not use this value as suffix for the HumanName"
                    {:input "Mr." :expected-one-of hnd/suffixes})))
         (catch-thrown-info (fhir-faker.human_name/suffix "Mr."))))))

