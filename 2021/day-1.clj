(require '[clojure.string :as str])

(let [x (map #(Integer/parseInt %1) (str/split-lines (slurp "day-1.txt")))] (count (filter #(> %1 0) (map #(- %1 %2) (rest x) x))))

(let [x (map #(Integer/parseInt %1) (str/split-lines (slurp "day-1.txt"))) y (map #(+ %1 %2 %3) x (rest x) (rest (rest x)))] (count (filter #(> %1 0) (map #(- %1 %2) (rest y) y))))