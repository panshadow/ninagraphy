(ns ninagraphy.views.layout
  (:require [hiccup.page :refer [html5 include-css include-js]]))

(defn common [& body]
  (html5
    [:head
     [:title ".ninagraphy:"]
     (include-js "http://fb.me/react-0.11.2.js")

     (include-css "/css/screen.css")
     (include-css "/css/bootstrap.min.css") 
     ]
    [:body {:role "document"}
     
       body
       (include-js "/js/om.ninagraphy.js")
     ]))
