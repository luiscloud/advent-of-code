(require '[clojure.string :as str])

(defn tobin [dec] (loop [x (map #(Integer/parseInt %) (str/split dec #"")) b 0 exp 1] (if (empty? x) b (recur (butlast x) (+ b (* exp (last x))) (* 2 exp)))))

(apply max (map #(+ (* 8 (tobin (subs % 0 7))) (tobin (subs % 7))) (map #(str/replace % #"[FL]" "0") (map #(str/replace % #"[BR]" "1") (str/split (slurp "day-5.txt") #"\r\n")))))

(loop [seats (sort (map #(+ (* 8 (tobin (subs % 0 7))) (tobin (subs % 7))) (map #(str/replace % #"[FL]" "0") (map #(str/replace % #"[BR]" "1") (str/split (slurp "day-5.txt") #"\r\n")))))] (if (not (empty? seats)) (if (= 1 (- (first (rest seats)) (first seats))) (recur (rest seats)) (+ 1 (first seats)))))