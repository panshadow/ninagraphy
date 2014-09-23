(defproject ninagraphy "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2311"]
                 [org.clojure/core.async "0.1.267.0-0d7780-alpha"]
                 [om "0.7.1"]
                 [compojure "1.1.6"]
                 [hiccup "1.0.5"]
                 [selmer "0.7.1"]
                 [ring-server "0.3.1"]
                 ]
  :plugins [[lein-ring "0.8.10"]
            [lein-cljsbuild "1.0.4-SNAPSHOT"]]
  :ring {:handler ninagraphy.handler/app
         :init ninagraphy.handler/init
         :destroy ninagraphy.handler/destroy}

  :cljsbuild {
    :builds [{
              :source-paths ["src-cljs"]
              :compiler {
                :output-to "resources/public/js/om.ninagraphy.js"
               }}]}

  :aot :all
  :profiles
  {
   :production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? true}}

   :dev
   {:dependencies [[ring-mock "0.1.5"] [ring/ring-devel "1.2.1"]]}})
