(ns typey.qmk.macros
  (:require [clojure.string :as str]))


(defn keyword->keycode [x]
  (condp = x
    :c- "KC_LCTL"
    :a- "KC_LALT"
    :s- "KC_LSFT"
    (str "KC_" (str/upper-case (name x)))))

#_(defn convert-to-keycode [x]
    (cond
      (keyword)))

(defn register-code [x]
  (str "register_code(KC_" (str/upper-case x) ");"))

(defn unregister-code [x]
  (str "unregister_code(KC_" (str/upper-case x) ");"))




(defn make-shortcut [x]
  (cond (map? x)

        "map"
        :else "else"

        ))

(defn make-code [x & shifted?]
  (cond
    (char? x) (cond

                (= x \space) (str (register-code "SPACE")
                                  (unregister-code "SPACE"))
                (= x \u0028) (str (register-code "LSFT")
                                  (register-code "9")
                                  (unregister-code "9")
                                  (unregister-code "LSFT"))
                (= x \') (str (register-code "QUOT")
                              (unregister-code "QUOT"))
                (= x \/) (str (register-code "SLSH")
                              (unregister-code "SLSH"))
                (= x \-) (str (register-code "MINS")
                              (unregister-code "MINS"))
                :else (str (register-code x)
                           (unregister-code x)))
    (string? x)
    (str/join (transduce
                (map #(make-code %))
                conj
                x))
    (vector? x)
    (str/join (transduce
                (map #(make-code %))
                conj
                x))
    (keyword? x)
    (str "register_code(" (str/upper-case (name x)) ");"
         "unregister_code(" (str/upper-case (name x)) ");")))


(defn wrap-code [k s]
  (str (register-code (str/upper-case (name k)))
       (make-code s)
       (unregister-code (str/upper-case (name k)))))

(defn k-
  ([x] (str (register-code x)
            (unregister-code x)))
  ([x1 x2]
   (str (register-code x1)
        (unregister-code x1)
        (register-code x2)
        (unregister-code x2))))

(defn w- [x]
  (str "register_code(KC_LGUI);"
       x
       "unregister_code(KC_LGUI);"))


(defn c- [x]
  (str "register_code(KC_LCTL);"
       x
       "unregister_code(KC_LCTL);"))


(defn s- [x]
  (str "register_code(KC_LSFT);"
       x
       "unregister_code(KC_LSFT);"))


(defn a- [x]
  (str "register_code(KC_LALT);"
       x
       "unregister_code(KC_LALT);"))


(defn make-buffered-macro [k s]
  (str/join [(str "if (record->event.pressed) { "
                  "push_buffer("
                  k
                  ", record); "
                  "return true; } ")
             (str "else { "
                  "if (buffer_pos_to_pop(record) != -1) {"
                  "layer_used = true;"
                  s
                  "} "
                  "pop_buffer_no_execute(buffer_pos_to_pop(record)); "
                  "return true; "
                  "} ")]))


(defn make-immediate-macro
  ([s]
   (str/join
     ["if (record->event.pressed) { "
      "layer_used = true;"
      s
      "} "
      "return true; "]))
  ([k s]
   (str/join
     [(str "if (record->event.pressed) { "
           "push_buffer("
           k
           ", record); "
           "if (buffer_pos_to_pop(record) != -1) {"
           "layer_used = true;"
           s
           "} "
           "pop_buffer_no_execute(buffer_pos_to_pop(record)); "
           "return true; } ")
      (str "else { "
           "return true; "
           "} ")])))