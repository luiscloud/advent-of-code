(require '[clojure.string :as str])

(def sorted-input (sort (map #(Integer/parseInt %) (str/split (str/replace (slurp "day-1.txt") #"\r" "") #"\n"))))

(def sorted-cross (sort (for [x sorted-input y sorted-input] (+ x y))))

(defn find-product [c] (if (> (+ (first c) (last c)) 2020) (find-product (butlast c)) (if (< (+ (first c) (last c)) 2020) (find-product (rest c)) (* (first c) (last c)))))

(find-product sorted-input)

(defn find-product-two [c l] (if (> (+ (first c) (last c)) l) (find-product-two (butlast c) l) (if (< (+ (first c) (last c)) l) (find-product-two (rest c) l) (* (first c) (last c)))))

(defn find-product-cross [c cross l] (if (> (+ (first c) (last cross)) l) (find-product-cross c (butlast cross) l) (if (< (+ (first c) (last cross)) l) (find-product-cross (rest c) cross l) (* (first c) (find-product-two sorted-input (last cross))))))

(find-product-cross sorted-input (filter #(> 2020 %) sorted-cross) 2020)