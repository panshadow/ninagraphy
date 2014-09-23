(ns ninagraphy.routes.home
  (:require [compojure.core :refer :all]
            [ninagraphy.views.layout :as layout]
            [selmer.parser :as tpl]))



(defn home []
  (layout/common [:div {:id "zero"}]))

(defn home-selmer []
  (tpl/render-file "index.html" {:title "NinaGraphy"}))

(defroutes home-routes
  (GET "/" [] (home)))
