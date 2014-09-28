(ns ninagraphy.routes.api
  (:require [compojure.core :refer :all]
            ))

(def VERSION "v1")
(def api (str  "/api/" VERSION))

(defn api-info []
  (str "NinaGraphy API " VERSION))


(defn list-albums [req]
  {:status 200
   :body {
          :albums [
                   {:id 1
                    :title "Якась назва"},
                   {:id 2
                    :title ""}]}})

(defn get-album [req]
  {:status 404
   :body {}})

(defroutes api-routes
  (GET (str api "/") [] (api-info))
  (GET (str api "/albums") [] list-albums )
  (GET (str api "/albums/:album") [] get-album))
