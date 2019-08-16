(ns nashville.lisp-sync.webapp
  (:require
    [clojure.tools.logging :as log]
    [hiccup.core :as hiccup]
    [ring.adapter.jetty :as jetty]
    [ring.middleware.params :as params]))

(defn app [{m :request-method
            {input "mah-button"} :form-params}]
  {:status 200
   :body (if (= m :post)
           (str "Hello, " input "!")
           (hiccup/html
             [:body
              [:div
               [:form {:action "/"
                       :method :post}
                [:input {:name "mah-button"}]
                [:button {:type :submit}]]]]))})

(defn start-server []
  (jetty/run-jetty (fn [req]
                     (app (params/params-request req)))
                   {:port 8080
                    :join? false}))

(comment
  (def s (start-server))

  (.stop s))