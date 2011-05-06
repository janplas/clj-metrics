(ns clj-metrics.util)

(def count-if (comp count filter))

(defn merge-seq [& args]
  "Merge number of labeled sequences into a list of maps"
  (let [keys (map first args)
        seqs (map second args)]
    (apply map (fn [& args] (zipmap keys args)) seqs)))
  
  