(ns structured-data)

(defn do-a-thing [x]
  (let [xx (+ x x)]
      (Math/pow xx xx)))

(defn spiff [v]
  (+ (get v 0) (get v 2)))

(defn cutify [v]
  (conj v "<3"))

(defn spiff-destructuring [v]
  (let [[x, y, z] v]
       (+ x z)))

(defn point [x y]
  [x y])

(defn rectangle [bottom-left top-right]
  [bottom-left top-right])

(defn width [rectangle]
  (let [[[x1 y1] [x2 y2]] rectangle]
    (- x2 x1)))

(defn height [rectangle]
  (let [[[x1 y1] [x2, y2]] rectangle]
    (- y2 y1)))

(defn square? [rectangle]
  (== (width rectangle) (height rectangle)))

(defn area [rectangle]
  (* (width rectangle) (height rectangle)))

(defn contains-point? [rectangle point]
(let [[[x1 y1] [x2 y2]] rectangle
      [x y] point]
      (and (<= x1 x x2) (<= y1 y y2))))

(defn contains-rectangle? [outer inner]
  (let [[[x3 y3] [x4 y4]] inner]
        (and (contains-point? outer [x3 y3])
             (contains-point? outer [x4 y4]))))

(defn title-length [book]
  (count (:title book)))

(defn author-count [book]
  (count (:authors book)))

(defn multiple-authors? [book]
  (> (author-count book) 1))

(defn add-author [book new-author]
  (let [authors (:authors book)]
    (assoc book :authors (conj authors new-author))))

(defn alive? [author]
  (not (contains? author :death-year)))

(defn element-lengths [collection]
  (map count collection))

(defn pride [x]
  (get x 1))

(defn second-elements [collection]
  (map pride collection))

(defn titles [books]
  (map :title books))

(defn monotonic? [a-seq]
  (or (apply <= a-seq)
      (apply >= a-seq)))

(defn stars [n]
  (apply str (repeat n "*")))

(defn toggle [a-set elem]
  (if (contains? a-set elem)
      (disj a-set elem)
      (conj a-set elem)))

(defn contains-duplicates? [a-seq]
  (let [first (count (set a-seq))]
       (not= (count a-seq) first)))

(defn old-book->new-book [book]
  (assoc book :authors (set (:authors book))))

(defn has-author? [book author]
  (contains? (:authors book) author))

(defn authors [books]
  (apply clojure.set/union (map :authors books)))

(defn all-author-names [books]
  (set (map :name (authors books))))

(defn author->string [author]
 (let [name (:name author)
      year (cond (contains? author :death-year) (str " (" (:birth-year author) " - " (:death-year author) ")")
                  (contains? author :birth-year) (str " (" (:birth-year author) " - " ")")
                  :else (str ""))]
    (str name year)))

(defn authors->string [authors]
  (apply str (interpose ", " (map author->string authors))))

(defn book->string [book]
(let [title (:title book)
      author (:authors book)]
  (str title ", written by " (authors->string author))))

(defn books->string [books]
  (let [count (count books)
        y (cond (== 0 count) (str "No books")
                (== 1 count) (str "1 book. ")
                (== 3 count) (str "3 books. "))]
      (str y (apply str (map book->string books)) ".")))

(defn books-by-author [author books]
  (filter (fn [book] (has-author? book author)) books))

(defn author-by-name [name authors]
  (first (filter (fn [author] (= (:name author) name)) authors)))

(defn living-authors [authors]
  (filter (fn [author] (alive? author)) authors))

(defn has-a-living-author? [book]
  (if (empty? (living-authors (:authors book)))
       false
       true))

(defn books-by-living-authors [books]
 (filter (fn [book] (has-a-living-author? book)) books))

; %________%
