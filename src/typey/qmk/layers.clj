(ns typey.qmk.layers
  (:require [clojure.string :as str]))

(defn buffered-layer-keys []
  [["QUOT" 2]
   ["COMM" 2]
   ["DOT" 3]
   ["MINS" 4]
   ["A" 6]
   ["B" 7]
   ["C" 8]
   ["D" 9]
   ["E" 10]
   ["F" 11]
   ["G" 12]
   ["H" 13]
   ["I" 14]
   ["J" 15]
   ["K" 16]
   ["L" 17]
   ["M" 18]
   ["N" 19]
   ["O" 20]
   ["P" 21]
   ["Q" 22]
   ["R" 23]
   ["S" 24]
   ["T" 25]
   ["U" 26]
   ["V" 27]
   ["W" 28]
   ["X" 29]
   ["Y" 30]
   ["Z" 31]])


(defn write-key [k]
  (condp = (str/trim k)
    "_" "KC_TRNS"
    "__" "KC_TRNS"
    "___" "KC_TRNS"
    "____" "KC_TRNS"
    "_____" "KC_TRNS"
    "______" "KC_TRNS"
    "_______" "KC_TRNS"
    "________" "KC_TRNS"
    "_________" "KC_TRNS"
    "__________" "KC_TRNS"
    "___________" "KC_TRNS"
    "" "KC_NO"
    "*" "KC_TRNS"
    "***" "KC_TRNS"
    "f-sexp" "LCTL(LALT(KC_F))"
    "b-sexp" "LCTL(LALT(KC_B))"
    "f-sexp-out" "LCTL(LALT(KC_N))"
    "b-sexp-out" "LCTL(LALT(KC_U))"
    "f-sexp-in" "LCTL(LALT(KC_D))"
    "b-sexp-in" "LCTL(LALT(KC_P))"
    "barf-bw" "LALT(LCTL(S(KC_LBRC)))"
    "barf-fw" "LCTL(S(KC_RBRC))"
    "slurp-bw" "LCTL(S(KC_9))"
    "slurp-fw" "LCTL(S(KC_0))"
    "form-down" "LCTL(S(KC_DOWN))"
    "form-up" "LCTL(S(KC_UP))"
    "raise-form" "RALT(KC_R)"
    "load-repl" "LALT(KC_L)"
    "sync-repl" "LCTL(LALT(KC_S))"
    "send-top" "LALT(KC_F)"
    "send-before" "LALT(KC_B)"
    "repl-ns" "LALT(KC_N)"
    "split" "LCTL(S(KC_S))"
    "join" "LALT(KC_J)"
    "kill" "LCTL(KC_K)"
    "splice" "LALT(KC_S)"
    "splice-kill-fw" "LALT(KC_DOWN)"
    "splice-kill-bw" "LALT(KC_UP)"
    "extend-selection" "LCTL(LALT(KC_W))"
    "shrink-selection" "LCTL(S(KC_W))"
    k))






(defn get_current_layer []
  (str/join "\n"
            ["static uint8_t get_current_layer(void) {"
             "  return biton32(layer_state);"
             "};"]))




(defn better_layer_toggle []
  (str/join "\n"
            ["static void better_layer_toggle (uint16_t keycode, keyrecord_t *record, uint8_t layer) {"
             "  if (record->event.pressed) {"
             "    uint8_t current_layer = get_current_layer();"
             "    layer_used = false; "
             "    if (current_layer != layer) {"
             "      layer_on(layer);"
             "    }"
             "    else if (current_layer == layer) {"
             "      layer_off(layer);"
             "    }"
             "    if (layer == 1) {"
             "      if ((current_layer == 2) || (current_layer == 3)|| (current_layer == 4) || (current_layer == 5)) {"
             "        layer_used = true;"
             "      }"
             "      layer_off(2);"
             "      layer_off(3);"
             "      layer_off(4);"
             "      if (current_layer > 4) { "
             "        layer_off(current_layer);"
             "      }"
             "    }"
             "    if (layer == 2) {"
             "      layer_off(3);"
             "      layer_off(4);"
             "    }"
             "    if (layer == 3) {"
             "      layer_off(4);"
             "      layer_off(5);"
             "    }"
             "    if (layer == 4) {"
             "      layer_off(5);"
             "    }"
             "  }"
             "  else {"
             "    if ((layer == 1) || (layer == 5)) {"
             "      layer_off(layer);"
             "    }"
             "    if ((layer_used == true) && (get_current_layer() == layer))  { "
             "      layer_off(layer);"
             "    }"
             "  }"
             "};"
             ""]))

(defn wrap-keymaps [contents]
  (str/join "\n"
            ["const uint16_t PROGMEM keymaps[][MATRIX_ROWS][MATRIX_COLS] = {"
             contents
             "};"
             ""]))


(defn wrap-layout [layer-id layer-keys]
  (str "[" layer-id "]" "= LAYOUT_ergodox(\n" layer-keys "),\n"))


(defn make-row [side n row]
  (cond
    (and (= side :l) (= n 0)) (str/join ", " (concat ["KC_ESC"] (map write-key row) ["RESET"]))
    (and (= side :l) (= n 1)) (str/join ", " (concat ["KC_TAB"] (map write-key row) ["KC_NO"]))
    (and (= side :l) (= n 2)) (str/join ", " (concat ["KC_LSFT"] (map write-key row)))
    (and (= side :l) (= n 3)) (str/join ", " (concat ["KC_LALT"] (map write-key row) ["KC_NO"]))
    (and (= side :l) (= n 4)) (str/join ", " (concat ["KC_LCTL" "KC_NO"] (map write-key row)))
    (and (= side :l) (= n 5)) (str/join ", " ["KC_NO" "KC_NO"])
    (and (= side :l) (= n 6)) (str/join ", " ["KC_NO"])
    (and (= side :l) (= n 7)) (str/join ", " (concat (map write-key row) ["KC_NO"]))

    (and (= side :r) (= n 0)) (str/join ", " (concat ["RESET"] (map write-key row) ["KC_DEL"]))
    (and (= side :r) (= n 1)) (str/join ", " (concat ["KC_NO"] (map write-key row) ["KC_D"]))
    (and (= side :r) (= n 2)) (str/join ", " (concat (map write-key row) ["KC_Z"]))
    (and (= side :r) (= n 3)) (str/join ", " (concat ["KC_NO"] (map write-key row) ["KC_LALT"]))
    (and (= side :r) (= n 4)) (str/join ", " (concat (map write-key row) ["KC_NO" "KC_LCTL"]))
    (and (= side :r) (= n 5)) (str/join ", " ["KC_NO" "KC_NO"])
    (and (= side :r) (= n 6)) (str/join ", " ["KC_NO"])
    (and (= side :r) (= n 7)) (str/join ", " (concat ["KC_NO"] (map write-key row)))))


(defn make-layer [left-rows right-rows]
  (str/join ",\n"
            [(make-row :l 0 (nth left-rows 0))
             (make-row :l 1 (nth left-rows 1))
             (make-row :l 2 (nth left-rows 2))
             (make-row :l 3 (nth left-rows 3))
             (make-row :l 4 (nth left-rows 4))
             (make-row :l 5 (nth left-rows 5))
             (make-row :l 6 (nth left-rows 6))
             (make-row :l 7 (nth left-rows 7))

             (make-row :r 0 (nth right-rows 0))
             (make-row :r 1 (nth right-rows 1))
             (make-row :r 2 (nth right-rows 2))
             (make-row :r 3 (nth right-rows 3))
             (make-row :r 4 (nth right-rows 4))
             (make-row :r 5 (nth right-rows 5))
             (make-row :r 6 (nth right-rows 6))
             (make-row :r 7 (nth right-rows 7))]))
