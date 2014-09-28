(ns ninagraphy.ui
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [ajax.core :refer [GET POST]]))

(def app-state (atom {:title "Shared example"}))

(defn NGPhoto [app owner]
  (reify
    om/IRenderState
    (render-state [_ state]
       (let []
         (dom/div nil
                  (dom/h3 nil "title")
                  (dom/div nil "photo"))))
    ))


(defn NGAlbum [prop owner]
  (reify
    om/IDisplayName
    (display-name [this]
      (str "NGAlbum-" (prop "id")))
    
    om/IRenderState
    (render-state [_ state]
      (dom/li #js {}
              (let [title (prop "title")]
                (str title))))))


(defn NGraphy [app owner]
  (reify
    om/IDisplayName
    (display-name [this] "NGraphy")

  
    om/IWillMount
    (will-mount [_]
      (GET "/api/v1/albums" {
            :response-format :json
            :handler (fn [resp]
                       (let [albums (resp "albums")]
                         (.log js/console albums)
                         (om/update! app [:albums] albums)))}))
    
    om/IRender
    (render [_]
      (let [albums (:albums app)
            nums (count albums)]
        
        (dom/div nil
                 (dom/header #js {:className "top"}
                             (dom/h1 nil "NinaGraphy"))
                 (dom/span nil (str "found " nums " albums"))
                 (apply dom/ul nil
                        (om/build-all NGAlbum albums))
                 ))
            )))



