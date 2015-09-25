(ns ^{:doc "A little Konami code library for ClojureScript."
      :author "Eric Weinstein <eric.q.weinstein@gmail.com>"}
  konami-cljs.core)

(def user-input
  "Manages state for the user's keyboard input."
  (atom []))

(def konami-code
  "Up, up, down, down, left, right, left, right, b, a"
  [38 38 40 40 37 39 37 39 66 65])

(defn- take-last-ten
  "Takes a vector and returns the last ten elements.
  (The Konami code happens to be ten characters long.)"
  [coll]
  (vec (take-last 10 coll)))

(defn- handle
  "Takes an easter-egg (either a string or a function). If a string,
  sets the window.location to the URL in the string; if a function,
  executes the function."
  [easter-egg]
  (cond
    (string? easter-egg) (set! (.-location js/window) easter-egg)
    (fn? easter-egg) (easter-egg)
    :else (throw
      (js/Error.
        "Your easter egg must either be a URL string or a callback function.")))
  (reset! user-input []))

(defn- on-keyup
  "Takes an HTML element and an easter-egg, registering a
  keyup event listener on the element. When the Konami code
  is entered, handles the associated easter-egg."
  [el easter-egg]
  (.addEventListener
    el "keyup"
    (fn [evt]
      (let [event (or evt (.-event js/window))
            keycode (or (.-which event) (.-keyCode event))]
        (.preventDefault evt)
        (swap! user-input conj keycode)
        (when (> (count @user-input) 10)
          (swap! user-input take-last-ten))
        (when (= konami-code @user-input)
          (handle easter-egg))))))

(defn konami
  "Registers a Konami code event. Takes either a string
  (URL) or a function for the easter-egg. If a string, redirects
  the user to the URL when the code is entered; if a function,
  executes the function as a callback."
  [easter-egg]
  (on-keyup js/document easter-egg))
