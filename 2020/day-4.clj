(require '[clojure.string :as str])

(count 
 (filter 
  #(= % 7) 
  (map 
   (fn [x] 
     (count 
      (filter 
       #(not (= % "cid")) 
       (map 
        #(subs % (- (count %) 3)) 
        (butlast (str/split x #":")))))) 
   (str/split (slurp "day-4.txt") #"\r\n\r\n"))))

(defn reqs [row] (filter #(let [k (subs % 0 3) v (subs % 4)] 
           (case k 
             "byr" (let [x (Integer/parseInt v)] (and (>= x 1920) (<= x 2002)))
             "iyr" (let [x (Integer/parseInt v)] (and (>= x 2010) (<= x 2020)))
             "eyr" (let [x (Integer/parseInt v)] (and (>= x 2020) (<= x 2030)))
             "hgt" (let [x (Integer/parseInt (subs v 0 (- (count v) 2))) u (subs v (- (count v) 2))] (case u "in" (and (>= x 59) (<= x 76)) "cm" (and (>= x 150) (<= x 193)) false))
             "hcl" (and (= \# (get v 0)) (= 7 (count v)) (empty? (str/replace (subs v 1) #"[1234567890abcdef]" "")))
             "ecl" (case v "amb" true "blu" true "brn" true "gry" true "grn" true "hzl" true "oth" true false)
             "pid" (and (= (count v) 9) (empty? (str/replace v #"[1234567890]" ""))))) row))

(count (filter #(= 7 %) (map #(count %) (map #(reqs %) (filter #(= 7 (count %)) (map (fn [x] (filter #(not= "cid" (subs % 0 3)) x)) (map (fn [x] (reduce #(concat %1 %2) x)) (map (fn [x] (map #(str/split % #" ") x)) (map #(str/split-lines %) (str/split (slurp "day-4.txt") #"\r\n\r\n"))))))))))