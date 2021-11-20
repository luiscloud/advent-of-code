(require '[clojure.string :as str])

(def bag-map (map #(concat [(subs (first %) 0 (- (count (first %)) 5))] (map (fn [x] (let [y (str/split x #" ")] (str (nth y 1) " " (nth y 2)))) (str/split (last %) #", "))) (map #(str/split % #" contain ") (filter #(not= (subs % (- (count %) 14)) "no other bags.") (str/split-lines (slurp "day-7.txt"))))))

(defn check-bag [bag-list color] (if (some #(= % color) (rest bag-list)) (first bag-list)))

(loop [bag-set bag-map cur-bags '("shiny gold") temp-bags '()] (let [wrapper (reduce #(concat %1 %2) (filter #(not (empty? %)) (map #(filter (fn [x] (not (empty? x))) %) (map #(map (fn [x] (check-bag x %)) bag-set) cur-bags))))] (if (empty? wrapper) temp-bags (recur bag-set wrapper (concat temp-bags wrapper)))))
