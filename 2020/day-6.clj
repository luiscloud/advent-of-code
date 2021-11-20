(require '[clojure.string :as str])

(require '[clojure.set])

(reduce #(+ %1 %2) (map #(count %) (map (fn [x] (reduce #(clojure.set/union %1 %2) x)) (map (fn [x] (map #(set %) x)) (map (fn [x] (map #(str/split % #"") x)) (map #(str/split-lines %) (str/split (slurp "day-6.txt") #"\r\n\r\n")))))))

(reduce #(+ %1 %2) (map #(count %) (map (fn [x] (reduce #(clojure.set/intersection %1 %2) x)) (map (fn [x] (map #(set %) x)) (map (fn [x] (map #(str/split % #"") x)) (map #(str/split-lines %) (str/split (slurp "day-6.txt") #"\r\n\r\n")))))))