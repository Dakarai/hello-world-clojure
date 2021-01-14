(ns hello-world.core
  (:require [clojure.string :as str])
  (:gen-class))

(use 'clojure.java.io)

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
  (println "Increment x" @atomEx))

(defn agent-ex
  []
  (def tickets-sold (agent 0))
  (send tickets-sold + 15)
  (println "Tickets " @tickets-sold)
  (send tickets-sold + 10)
  (await-for 100 tickets-sold)
  (println "Tickets " @tickets-sold)
  (shutdown-agents))

(defn math-stuff
  []
  (println (+ 1 2 3))
  (println (- 5 3 2))
  (println (* 2 5))
  (println (/ 10 5))
  (println (mod 12 5))

  (println (inc 5))
  (println (dec 5))

  (println (Math/abs -10)) ;absolute value
  (println (Math/cbrt 8)) ;cube root
  (println (Math/sqrt 4))
  (println (Math/ceil 4.5)) ;round up
  (println (Math/floor 4.5)) ;round down
  (println (Math/exp 1)) ;e to the power of 1
  (println (Math/hypot 2 2)) ;sqrt(x^2 + y^2)
  (println (Math/log 2.71828)) ;natural log
  (println (Math/log10 100)) ;base 10 log
  (println (Math/max 1 5))
  (println (Math/min 1 5))
  (println (Math/pow 2 4))
  
  (println (rand-int 20))
  
  (println (reduce + [1 2 3]))
  (println (Math/PI)))

(defn say-hello
  "receives a name with 1 parameter and responds"
  [name]
  (println "Hello again" name))

(defn get-sum
  [x y]
  (+ x y))

(defn get-sum-more
  ([x y z]
   (+ x y z))
  
  ([x y]
   (+ x y)))

(defn hello-you
  [name]
  (str "Hello " name))

(defn hello-all
  [& names]
  (map hello-you names))

(defn equalities
  []
  (println (= 4 5))
  (println (not= 4 5))
  (println (and true false))
  (println (or true false))
  (println (not true)))

(defn can-vote
  [age]
  (if (>= age 18)
    (println "You can vote")
    (println "You can't vote")))

(defn can-do-more
  [age]
  (if (>= age 18)
    (do (println "You can drive")
        (println"You can vote"))
    (println "You can't vote")))

(defn when-ex
  [tof]
  (when tof
    (println "1st thing")
    (println "2nd thing")))

(defn what-grade
  [n]
  (cond
    (< n 5) (println "Preschool")
    (= n 5) (println "Kindergarten")
    (and (> n 5) (<= n 18)) (format "Go to grade %d" (- n 5))
    :else "Go to college"))

(defn one-to-x
  [x]
  (def i (atom 1))
  (while (<= @i x)
    (do
      (println @i)
      (swap! i inc))))

(defn double-to-x
  [x]
  (dotimes [i x]
    (println (* i 2))))

(defn triple-to-x
  [x y]
  (loop [i x]
    (when (< i y)
      (println (* i 3))
      (recur (+ i 1)))))

(defn print-list
  [& nums]
  (doseq [x nums]
    (println x)))

(defn write-to-file
  [file text]
  (with-open  [wrtr (writer file)]
    (.write wrtr text)))

(defn read-from-file
  [file]
  (try
    (println slurp file))
  (catch Exception e 
    (println "Error: " (.getMessage e))))

(defn append-to-file
  [file text]
  (with-open [wrtr (writer file :append true)]
    (.write wrtr text)))

(defn read-line-from-file
  [file]
  (with-open [rdr (reader file)]
    (doseq [line (line-seq rdr)]
      (println line))))

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
  (agent-ex)
  
  (say-hello "Brendan")
  (get-sum 4 5)
  (get-sum-more 1 2 3)
  (hello-all "Doug" "Mary" "Paul")
  
  (equalities)
  
  (can-vote 17)
  (can-do-more 21)
  (when-ex true)
  (what-grade 19)
  
  (one-to-x 5)
  (double-to-x 5)
  (triple-to-x 1 5)
  (print-list 7 8 9)
  
  (write-to-file "test.txt" "This is a sentence.\n")
  (read-from-file "test.txt")
  (append-to-file "test.txt" "Th is another sentence.\n")
  (read-line-from-file "test.txt")
  )