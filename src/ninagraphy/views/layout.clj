(ns ninagraphy.views.layout
  (:require [hiccup.page :refer [html5 include-css]]))

(defn common [& body]
  (html5
    [:head
     [:title "Welcome to ninagraphy"]
     (include-css "/css/screen.css")]
    [:body body]))
