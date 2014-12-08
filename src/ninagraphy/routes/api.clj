(ns ninagraphy.routes.api
  (:require [compojure.core :refer :all]
            ))

(def VERSION "v1")
(def api (str  "/api/" VERSION))

(def photos [
             {:album 1 :title "one" :photo "/photos/IMG_0632.JPG"}
             {:album 2 :title "two" :photo "/photos/IMG_8108.JPG"}
             {:album 2 :title "three" :photo "/photos/IMG_8155.JPG"}  ])

(defn api-info []
  (str "NinaGraphy API " VERSION))



(defn get-album
  ([] (let [albums (group-by :album photos)
            ids (keys albums)]
        (map (fn [id]
               {:id id
                :photos (get albums id [])
                }) ids)))
  
  ([id] {:id id
         :photos (filter #(-> % :album (str) (= id)) photos)
         }))


(defn list-albums [req]
  (let [albums (get-album)]
    {:status 200
     :body {:albums albums}}))

(defn one-album [req id]
  (let [album (get-album id)]
    {:status 200
     :body {:albums [album]}}
    ))



(defroutes api-routes
  (GET (str api "/") [] (api-info))
  (GET (str api "/albums") [] list-albums )
  (GET (str api "/albums/:album") [] one-album))
