(ns ninagraphy.handler
  (:require [compojure.core :refer [defroutes routes]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [hiccup.middleware :refer [wrap-base-url]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [clojure.java.io :as io]
            [selmer.parser :as tpl]
            [ninagraphy.routes.home :refer [home-routes]]))

(defn init []
  (tpl/set-resource-path! (io/resource  "templates"))
  (println "ninagraphy is starting"))

(defn destroy []
  (println "ninagraphy is shutting down"))

(defroutes app-routes
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> (routes home-routes app-routes)
      (handler/site)
      (wrap-base-url)))


