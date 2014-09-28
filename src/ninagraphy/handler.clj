(ns ninagraphy.handler
  (:require [compojure.core :refer [defroutes routes]]
            [ring.util.response :refer :all]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [hiccup.middleware :refer [wrap-base-url]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [clojure.java.io :as io]
            [selmer.parser :as tpl]
            [cheshire.core :as json]
            [ninagraphy.routes.home :refer [home-routes]]
            [ninagraphy.routes.api :refer [api-routes]]))

(defn init []
  (tpl/set-resource-path! (io/resource  "templates"))
  (println "ninagraphy is starting"))

(defn destroy []
  (println "ninagraphy is shutting down"))

(defroutes app-routes
  (route/resources "/")
  (route/not-found "Not Found"))

(defn wrap-json-response [handler]
  (fn [req]
    (let [resp (handler req)
          body (:body resp)]
      (if (map? body)
        (-> resp
            (content-type "application/json;charset=utf-8")
            (update-in [:body] json/encode))
        resp))))

(def app
  (-> (routes home-routes api-routes app-routes)
      (handler/site)
      (wrap-base-url)
      (wrap-json-response)))


