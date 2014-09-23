(ns ninagraphy.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))



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


(defn NGRoot [app owner]
  (reify
    om/IDisplayName
    (display-name [this] "NGRoot")

    om/IRender
    (render [_]
            (dom/div nil
               (dom/h1 nil "NinaGraphy")
             )
            )))

(def zero (.getElementById js/document "zero"))

(om/root NGRoot {}
  {:target zero})
