(ns hello-world.core
  (:require [clojure.string :as str])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]


  (def aLong 16)
  (nil? aLong)
  (pos? aLong)
  (even? aLong)

  (def aDouble 1.234)

  (def aString "Hello")
  (format "This is a string %s" aString)
  (format "5 spaces and %5d" aLong)
  (format "Leading zeros %04d" aLong)
  (format "%-4d left justify" aLong)
  (format "2 decimals %.2f" aDouble)

  (def str1 "This is my 2nd string")
  (str/blank? str1)
  (str/includes? str1 "my")
  (str/index-of str1 "my")
  (str/split str1 #" ")
  (str/split str1 #"\d")
  (str/join " " ["The" "Big" "Cheese"])
  (str/replace "I am 42" #"42" "26")
  (str/upper-case str1)
  (str/lower-case str1)

  (println (list "Dog" 1 3.4 false))
  (println (first (list 1 2 3)))
  (println (rest (list 1 2 3)))
  (println (nth (list 1 2 3) 1))
  (println (list* 1 2 [3 4]))
  (println (cons 3 (list 1 2)))

  (println (set '(1 1 2)))
  (println (get (set '(3 2)) 2))
  (println (conj (set '(3 2)) 1))
  (println (contains? (set '(3 2)) 2))
  (println (disj (set '(3 2)) 2))

  (println (vector 1 "Dog"))
  (println (get (vector 3 2) 1))
  (println (conj (vector 3 2) 1))
  (println (pop (vector 1 2 3 4)))
  (println (subvec (vector 1 2 3 4) 1 3))

  (println (hash-map "Name" "Brendan" "Age" 26))
  (println (sorted-map 3 42 2 "Bananas" 1 "Brendan"))
  (println (get (hash-map "Name" "Brendan" "Age" 26) "Age"))
  (println (contains? (hash-map "Name" "Brendan" "Age" 26) "Age"))
  (println (keys (hash-map "Name" "Brendan" "Age" 26)))
  (println (vals (hash-map "Name" "Brendan" "Age" 26)))
  (println (merge-with + (hash-map "Name" "Brendan") (hash-map "Age" 26)))
  
  (atom-ex 5)
  )

(defn atom-ex
  [x]
  (def atomEx (atom x))
  
  (add-watch atomEx :watcher
       (fn [key atom old-state new-state]
         (println "atomEx changed from " old-state " to " new-state)))
  (println "1st x" @atomEx)
  (reset! atomEx 10)
  (println "2nd x" @atomEx)
  (swap! atomEx inc)
  (println "Increment x" @atomEx)
  )