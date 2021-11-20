(require '[clojure.string :as str])

(reduce + (map (fn [x] (let [y (str/split (str/replace x #": " "") #" ") pw (get y 1) freq (- ((frequencies pw) (get pw 0)) 1) limits (get y 0) limit-v (map #(Integer/parseInt %) (str/split limits #"-"))] (if (>= freq (first limit-v)) (if (<= freq (last limit-v)) 1 0) 0))) (str/split-lines (slurp "day-2.txt"))))

(reduce + (map (fn [x] (let [y (str/split (str/replace x #": " "") #" ") pw (get y 1) limits (get y 0) limit-v (map #(Integer/parseInt %) (str/split limits #"-"))] (if (= (get pw 0) (get pw (first limit-v))) (if (not (= (get pw 0) (get pw (last limit-v)))) 1 0) (if (= (get pw 0) (get pw (last limit-v))) (if (not (= (get pw 0) (get pw (first limit-v)))) 1 0) 0)))) (str/split-lines (slurp "day-2.txt"))))