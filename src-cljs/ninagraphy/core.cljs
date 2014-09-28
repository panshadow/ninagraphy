(ns ninagraphy.core
  (:require [om.core :as om :include-macros true]
            [ninagraphy.ui :as ui]))


(def app-state (atom
                {:albums []
                 }))

(om/root ui/NGraphy app-state
  {:target (.getElementById js/document "zero")})
