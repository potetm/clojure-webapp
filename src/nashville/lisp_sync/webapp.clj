(ns nashville.lisp-sync.webapp
  (:require
    [hiccup.core :as hiccup]
    [ring.adapter.jetty :as jetty]))

(defn app [req]
  {:status 200
   :body (hiccup/html
           [:body
            [:div
             [:form
              [:input {:name "mah-button"}]
              [:button {:type :submit}]]]])})

(defn start-server []
  (jetty/run-jetty (fn [req]
                     (app req))
                   {:port 8080
                    :join? false}))

(comment
  (def s (start-server))

  (.stop s))