(require '[clojure.string :as str])

(let [pairs (map #(str/split %1 #" ") (str/split-lines (slurp "day-2.txt")))] (* (reduce + (map #(Integer/parseInt (second %1)) (filter #(= "forward" (first %1)) pairs))) (- (reduce + (map #(Integer/parseInt (second %1)) (filter #(= "down" (first %1)) pairs))) (reduce + (map #(Integer/parseInt (second %1)) (filter #(= "up" (first %1)) pairs))))))

(let [pairs (map #(str/split %1 #" ") (str/split-lines (slurp "day-2.txt")))]
  (loop [plan pairs aim 0 x 0 d 0]
    (if
     (not (empty? plan))
      (case
       (first (first plan))
        "forward" (recur (rest plan) aim (+ x (Integer/parseInt (second (first plan)))) (+ d (* aim (Integer/parseInt (second (first plan))))))
        "up" (recur (rest plan) (- aim (Integer/parseInt (second (first plan)))) x d)
        "down" (recur (rest plan) (+ aim (Integer/parseInt (second (first plan)))) x d))
      (* x d))))