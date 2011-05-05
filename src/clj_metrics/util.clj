(ns clj-metrics.util)

(def count-if (comp count filter))

(defn merge-seq [& args]
  (let [keys (map first args)
        seqs (map second args)]
    (apply map (fn [& args] (zipmap keys args)) seqs)))

; Fix me: next func should be generalised
(defn merge-seq2
  "Merge 2 sequences into list of maps with given labels"
  [seq1 label1 seq2 label2]
  (map (fn [x y] {(keyword label1) x (keyword label2) y}) seq1 seq2))
  
  