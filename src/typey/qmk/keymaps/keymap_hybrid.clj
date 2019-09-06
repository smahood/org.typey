(ns typey.qmk.keymaps.keymap-hybrid
  (:require [clojure.string :as str]))

(defn write-key [k]
  (condp = (str/trim k)
    "" "KC_NO"
    k))


(defn make-row [side n row]
  (cond
    (and (= side :l) (= n 0)) (str/join ", " (concat ["KC_NO"] (map write-key row) ["RESET"]))
    (and (= side :l) (= n 1)) (str/join ", " (concat ["KC_NO"] (map write-key row) ["KC_NO"]))
    (and (= side :l) (= n 2)) (str/join ", " (concat ["KC_NO"] (map write-key row)))
    (and (= side :l) (= n 3)) (str/join ", " (concat ["KC_NO"] (map write-key row) ["KC_NO"]))
    (and (= side :l) (= n 4)) (str/join ", " (concat ["KC_LCTL" "KC_NO"] (map write-key row)))
    (and (= side :l) (= n 5)) (str/join ", " ["KC_NO" "KC_NO"])
    (and (= side :l) (= n 6)) (str/join ", " ["KC_NO"])
    (and (= side :l) (= n 7)) (str/join ", " (concat (map write-key row) ["TG(31)"]))

    (and (= side :r) (= n 0)) (str/join ", " (concat ["RESET"] (map write-key row) ["KC_NO"]))
    (and (= side :r) (= n 1)) (str/join ", " (concat ["KC_NO"] (map write-key row) ["KC_NO"]))
    (and (= side :r) (= n 2)) (str/join ", " (concat (map write-key row) ["KC_NO"]))
    (and (= side :r) (= n 3)) (str/join ", " (concat ["KC_NO"] (map write-key row) ["KC_NO"]))
    (and (= side :r) (= n 4)) (str/join ", " (concat (map write-key row) ["KC_LCTL" "KC_LCTL"]))
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


(defn steno-layer-gemini []
  (str/join ",\n"
            [(str/join ", " ["PRNB   " "KC_NO " "KC_NO " "KC_NO " "KC_NO " "KC_NO  " "RESET"])
             (str/join ", " ["KC_NO  " "KC_NO " "KC_NO " "KC_NO " "KC_NO " "KC_NO  " "KC_NO"])
             (str/join ", " ["KC_NO  " "KC_NO  " "STN_S1" "STN_TL" "STN_PL" "STN_HL"])
             (str/join ", " ["KC_NO  " "KC_NO  " "STN_S2" "STN_KL" "STN_WL" "STN_RL" "STN_ST1"])
             (str/join ", " ["KC_NO  " "KC_NO" "KC_NO" "KC_NO" "KC_NO"])
             (str/join ", " ["KC_NO  " "KC_NO "])
             (str/join ", " ["KC_NO  "])
             (str/join ", " ["STN_A  " "STN_O " "TG(31) "])

             (str/join ", " ["RESET " "KC_NO " "KC_NO " "KC_NO" "KC_NO  " "KC_NO " "PRNB  "])
             (str/join ", " ["KC_NO " "KC_NO " "KC_NO " "KC_NO " "KC_NO " "KC_NO " "KC_NO "])
             (str/join ", " ["          STN_FR" "STN_PR" "STN_LR" "STN_TR" "STN_DR" "KC_NO "])
             (str/join ", " ["STN_ST3" "STN_RR" "STN_BR" "STN_GR" "STN_SR" "STN_ZR" "KC_NO "])
             (str/join ", " ["KC_NO " "KC_NO " "KC_NO " "KC_NO " "KC_NO "])
             (str/join ", " ["KC_NO " "KC_NO "])
             (str/join ", " ["KC_NO "])
             (str/join ", " ["KC_NO " "STN_E  " "STN_U  "])]))


(def lay0-keys
  [[["KC_ESC    " "J         " "Q         " "KC_QUOT   " "KC_GRV    "]
    ["KC_TAB    " "Y         " "O         " "U         " "KC_BSLS   "]
    ["KC_LSFT   " "I         " "A         " "E         " "KC_SLSH   "]
    ["KC_LALT   " "COMM      " "DOT       " "MINS      " "S_COLON   "]
    ["          " "          " "S         "]
    ["          " "          "]
    ["          " "          "]
    ["LAYER1    " "LAYER5    "]]

   [["KC_Z      " "X         " "P         " "W         " "KC_DEL    "]
    ["K         " "F         " "D         " "R         " "KC_BSPC   "]
    ["V         " "H         " "T         " "N         " "KC_LSFT   "]
    ["B         " "M         " "G         " "L         " "KC_LCTL   "]
    ["C         " "KC_LALT   " "          "]
    ["          " "          "]
    ["          " "          "]
    ["KC_ENT    " "KC_SPACE  "]]])


(def base-keys
  [[["KC_ESC    " "KC_J      " "KC_Q      " "KC_QUOT   " "KC_GRV    "]
    ["KC_TAB    " "KC_Y      " "KC_O      " "KC_U      " "KC_BSLS   "]
    ["KC_LSFT   " "KC_I      " "KC_A      " "KC_E      " "KC_SLSH   "]
    ["TG(31)    " "KC_COMM   " "KC_DOT    " "KC_MINS   " "S_COLON   "]
    ["          " "KC_LALT   " "KC_S      "]
    ["          " "          "]
    ["          " "          "]
    ["LAYER1    " "LAYER5    "]]

   [["KC_Z      " "KC_X      " "KC_P      " "KC_W      " "KC_DEL    "]
    ["KC_K      " "KC_F      " "KC_D      " "KC_R      " "KC_BSPC   "]
    ["KC_V      " "KC_H      " "KC_T      " "KC_N      " "KC_LSFT   "]
    ["KC_B      " "KC_M      " "KC_G      " "KC_L      " "KC_LCTL   "]
    ["KC_C      " "KC_LALT   " "          "]
    ["          " "          "]
    ["          " "          "]
    ["KC_ENT    " "KC_SPACE  "]]])


(defn lay0 [hand row col]
  (-> lay0-keys
      (nth hand)
      (nth row)
      (nth col)))


(defn base [hand row col]
  (-> base-keys
      (nth hand)
      (nth row)
      (nth col)))


(defn key-defs []
  (sort-by
    key
    {0
     (make-layer
       [[(lay0 0 0 0) (lay0 0 0 1) (lay0 0 0 2) (lay0 0 0 3) (lay0 0 0 4)]
        [(lay0 0 1 0) (lay0 0 1 1) (lay0 0 1 2) (lay0 0 1 3) (lay0 0 1 4)]
        [(lay0 0 2 0) (lay0 0 2 1) (lay0 0 2 2) (lay0 0 2 3) (lay0 0 2 4)]
        [(lay0 0 3 0) (lay0 0 3 1) (lay0 0 3 2) (lay0 0 3 3) (lay0 0 3 4)]
        [(lay0 0 4 0) (lay0 0 4 1) (lay0 0 4 2)]
        [(lay0 0 5 0) (lay0 0 5 1)]
        [(lay0 0 6 0) (lay0 0 6 1)]
        [(lay0 0 7 0) (lay0 0 7 1)]]

       [[(lay0 1 0 0) (lay0 1 0 1) (lay0 1 0 2) (lay0 1 0 3) (lay0 1 0 4)]
        [(lay0 1 1 0) (lay0 1 1 1) (lay0 1 1 2) (lay0 1 1 3) (lay0 1 1 4)]
        [(lay0 1 2 0) (lay0 1 2 1) (lay0 1 2 2) (lay0 1 2 3) (lay0 1 2 4)]
        [(lay0 1 3 0) (lay0 1 3 1) (lay0 1 3 2) (lay0 1 3 3) (lay0 1 3 4)]
        [(lay0 1 4 0) (lay0 1 4 1) (lay0 1 4 2)]
        [(lay0 1 5 0) (lay0 1 5 1)]
        [(lay0 1 6 0) (lay0 1 6 1)]
        [(lay0 1 7 0) (lay0 1 7 1)]])

     ; Movement
     1
     (make-layer
       [[(base 0 0 0) (base 0 0 1) (base 0 0 2) (base 0 0 3) (base 0 0 4)]
        [(base 0 1 0) (base 0 1 1) "EXTEND_SEL" (base 0 1 3) (base 0 1 4)]
        [(base 0 2 0) "SEXP_BW   " "SHRINK_SEL" "SEXP_FW   " (base 0 2 4)]
        [(base 0 3 0) "UNDO      " "CUT       " "COPY      " "PASTE     "]
        [(base 0 4 0) (base 0 4 1) (base 0 4 2)]
        [(base 0 5 0)]
        [(base 0 6 0)]
        [(base 0 7 0) (base 0 7 1)]]

       [["          " "KC_PGUP   " "EXTEND_SEL   " "KC_PGDN   " (base 1 0 4)]
        ["          " "KC_HOME   " "KC_UP        " "KC_END    " (base 1 1 4)]
        ["          " "KC_LEFT   " "KC_DOWN      " "KC_RIGHT  " (base 1 2 4)]
        ["          " "SEXP_BW   " "SHRINK_SEL   " "SEXP_FW   " (base 1 3 4)]
        ["CMNT_FORM " (base 1 4 1) (base 1 4 2)]
        [(base 1 5 0) (base 1 5 1)]
        [(base 1 6 0) (base 1 6 1)]
        [(base 1 7 0) "KC_ENT    "]])

     ; Comma - F-Keys
     2
     (make-layer
       [[(base 0 0 0) (base 0 0 1) (base 0 0 2) (base 0 0 3) (base 0 0 4)]
        [(base 0 1 0) (base 0 1 1) (base 0 1 2) (base 0 1 3) (base 0 1 4)]
        [(base 0 2 0) (base 0 2 1) (base 0 2 2) (base 0 2 3) (base 0 2 4)]
        [(base 0 3 0) "COMM      " (base 0 3 2) (base 0 3 3) (base 0 3 4)]
        [(base 0 4 0) (base 0 4 1) (base 0 4 2)]
        [(base 0 5 0) (base 0 5 1)]
        [(base 0 6 0) (base 0 6 1)]
        [(base 0 7 0) (base 0 7 1)]]

       [["          " "KC_F10    " "KC_F11    " "KC_F12    " (base 1 0 4)]
        ["          " "KC_F7     " "KC_F8     " "KC_F9     " (base 1 1 4)]
        ["          " "KC_F4     " "KC_F5     " "KC_F6     " (base 1 2 4)]
        ["          " "KC_F1     " "KC_F2     " "KC_F3     " (base 1 3 4)]
        ["          " "          " (base 1 4 2)]
        [(base 1 5 0) (base 1 5 1)]
        [(base 1 6 0) (base 1 6 1)]
        [(base 1 7 0) (base 1 7 1)]])

     ; Dot - Symbols
     3
     (make-layer
       [[(base 0 0 0) (base 0 0 1) (base 0 0 2) (base 0 0 3) (base 0 0 4)]
        [(base 0 1 0) (base 0 1 1) (base 0 1 2) (base 0 1 3) (base 0 1 4)]
        [(base 0 2 0) (base 0 2 1) (base 0 2 2) (base 0 2 3) (base 0 2 4)]
        [(base 0 3 0) (base 0 3 1) "DOT       " (base 0 3 3) (base 0 3 4)]
        [(base 0 4 0) (base 0 4 1) (base 0 4 2)]
        [(base 0 5 0) (base 0 5 1)]
        [(base 0 6 0) (base 0 6 1)]
        [(base 0 7 0) (base 0 7 1)]]

       [[(base 1 0 0) "          " "          " "          " "          "]
        [(base 1 1 0) "S_AMPR    " "S_STAR    " "          " "          "]
        [(base 1 2 0) "S_DLR     " "S_PERC    " "S_HAT     " "          "]
        [(base 1 3 0) "S_BANG    " "S_AT      " "S_HASH    " "          "]
        ["CMNT_FORM " "          " (base 1 4 2)]
        [(base 1 5 0) (base 1 5 1)]
        [(base 1 6 0) (base 1 6 1)]
        [(base 1 7 0) (base 1 7 1)]])

     ; Dash - Numpad
     4
     (make-layer
       [[(base 0 0 0) (base 0 0 1) (base 0 0 2) (base 0 0 3) (base 0 0 4)]
        [(base 0 1 0) (base 0 1 1) (base 0 1 2) (base 0 1 3) (base 0 1 4)]
        [(base 0 2 0) (base 0 2 1) (base 0 2 2) (base 0 2 3) (base 0 2 4)]
        [(base 0 3 0) (base 0 3 1) (base 0 3 2) "MINS      " (base 0 3 4)]
        [(base 0 4 0) (base 0 4 1) (base 0 4 2)]
        [(base 0 5 0) (base 0 5 1)]
        [(base 0 6 0) (base 0 6 1)]
        [(base 0 7 0) (base 0 7 1)]]

       [["          " "          " "KC_SLSH   " "S_STAR    " (base 1 0 4)]
        ["          " "KC_7      " "KC_8      " "KC_9      " (base 1 1 4)]
        ["KC_PMNS   " "KC_4      " "KC_5      " "KC_6      " (base 1 2 4)]
        ["KC_PPLS   " "KC_1      " "KC_2      " "KC_3      " (base 1 3 4)]
        ["KC_0      " "KC_DOT    " (base 1 4 2)]
        [(base 1 5 0) (base 1 5 1)]
        [(base 1 6 0) (base 1 6 1)]
        [(base 1 7 0) (base 1 7 1)]])


     ; Clojure and S-Expressions
     5
     (make-layer
       [[(base 0 0 0) (base 0 0 1) (base 0 0 2) (base 0 0 3) (base 0 0 4)]
        [(base 0 1 0) (base 0 1 1) (base 0 1 2) (base 0 1 3) (base 0 1 4)]
        [(base 0 2 0) (base 0 2 1) (base 0 2 2) (base 0 2 3) (base 0 2 4)]
        [(base 0 3 0) (base 0 3 1) (base 0 3 2) (base 0 3 3) (base 0 3 4)]
        [(base 0 4 0) (base 0 4 1) (base 0 4 2)]
        [(base 0 5 0) (base 0 5 1)]
        [(base 0 6 0) (base 0 6 1)]
        [(base 0 7 0) (base 0 7 1)]]

       [[(base 1 0 0) (base 1 0 1) (base 1 0 2) "RAISE     " (base 1 0 4)]
        [(base 1 1 0) "BARF_BW   " "FORM_UP   " "BARF_FW   " (base 1 1 4)]
        [(base 1 2 0) "SLURP_FW  " "FORM_DOWN " "SLURP_BW  " (base 1 2 4)]
        [(base 1 3 0) (base 1 3 1) (base 1 3 2) (base 1 3 3) (base 1 3 4)]
        ["CMNT_FORM " (base 1 4 1) (base 1 4 2)]
        [(base 1 5 0) (base 1 5 1)]
        [(base 1 6 0) (base 1 6 1)]
        [(base 1 7 0) (base 1 7 1)]])

     ; A Layer
     6
     (make-layer
       [[(base 0 0 0) (base 0 0 1) (base 0 0 2) (base 0 0 3) (base 0 0 4)]
        [(base 0 1 0) "ANY       " (base 0 1 2) (base 0 1 3) (base 0 1 4)]
        [(base 0 2 0) "ASSOC_IN  " "A         " (base 0 2 3) (base 0 2 4)]
        [(base 0 3 0) (base 0 3 1) (base 0 3 2) (base 0 3 3) (base 0 3 4)]
        [(base 0 4 0) (base 0 4 1) "ASSOC     "]
        [(base 0 5 0) (base 0 5 1)]
        [(base 0 6 0) (base 0 6 1)]
        [(base 0 7 0) (base 0 7 1)]]

       [[(base 1 0 0) (base 1 0 1) "APPLY     " (base 1 0 3) (base 1 0 4)]
        [(base 1 1 0) (base 1 1 1) (base 1 1 2) (base 1 1 3) (base 1 1 4)]
        [(base 1 2 0) (base 1 2 1) "ATOM      " "AND       " (base 1 2 4)]
        [(base 1 3 0) (base 1 3 1) (base 1 3 2) (base 1 3 3) (base 1 3 4)]
        [(base 1 4 0) (base 1 4 1) (base 1 4 2)]
        [(base 1 5 0) (base 1 5 1)]
        [(base 1 6 0) (base 1 6 1)]
        [(base 1 7 0) (base 1 7 1)]])

     ; B Layer
     7
     (make-layer
       [[(base 0 0 0) (base 0 0 1) (base 0 0 2) (base 0 0 3) (base 0 0 4)]
        [(base 0 1 0) (base 0 1 2) (base 0 1 2) (base 0 1 3) (base 0 1 4)]
        [(base 0 2 0) (base 0 2 1) (base 0 2 2) (base 0 2 3) (base 0 2 4)]
        [(base 0 3 0) (base 0 3 1) (base 0 3 2) (base 0 3 3) (base 0 3 4)]
        [(base 0 4 0) (base 0 4 1) (base 0 4 2)]
        [(base 0 5 0) (base 0 5 1)]
        [(base 0 6 0) (base 0 6 1)]
        [(base 0 7 0) (base 0 7 1)]]

       [[(base 1 0 0) (base 1 0 1) (base 1 0 2) (base 1 0 3) (base 1 0 4)]
        ["BOOK      " (base 1 1 1) (base 1 1 2) (base 1 1 3) (base 1 1 4)]
        [(base 1 2 0) (base 1 2 1) (base 1 2 2) (base 1 2 3) (base 1 2 4)]
        ["B         " (base 1 3 1) (base 1 3 2) (base 1 3 3) (base 1 3 4)]
        [(base 1 4 0) (base 1 4 1) (base 1 4 2)]
        [(base 1 5 0) (base 1 5 1)]
        [(base 1 6 0) (base 1 6 1)]
        [(base 1 7 0) (base 1 7 1)]])

     ; C Layer
     8
     (make-layer
       [[(base 0 0 0) "CLOJURE   " (base 0 0 2) (base 0 0 3) (base 0 0 4)]
        [(base 0 1 0) (base 0 1 1) "COMMENT_STR" (base 0 1 3) (base 0 1 4)]
        [(base 0 2 0) (base 0 2 1) (base 0 2 2) (base 0 2 3) (base 0 2 4)]
        [(base 0 3 0) (base 0 3 1) (base 0 3 2) (base 0 3 3) (base 0 3 4)]
        [(base 0 4 0) (base 0 4 1) (base 0 4 2)]
        [(base 0 5 0) (base 0 5 1)]
        [(base 0 6 0) (base 0 6 1)]
        [(base 0 7 0) (base 0 7 1)]]

       [[(base 1 0 0) (base 1 0 1) (base 1 0 2) (base 1 0 3) (base 1 0 4)]
        [(base 1 1 0) "WRAP_ROUND" "WRAP_SQR  " "WRAP_CURLY" (base 1 1 4)]
        [(base 1 2 0) "S_LPRN    " "KC_LBRC   " "S_LCBR    " (base 1 2 4)]
        [(base 1 3 0) "S_RPRN    " "KC_RBRC   " "S_RCBR    " (base 1 3 4)]
        ["C         " (base 1 4 1) (base 1 4 2)]
        [(base 1 5 0) (base 1 5 1)]
        [(base 1 6 0) (base 1 6 1)]
        [(base 1 7 0) (base 1 7 1)]])

     ; D Layer
     9
     (make-layer
       [[(base 0 0 0) (base 0 0 1) (base 0 0 2) (base 0 0 3) (base 0 0 4)]
        [(base 0 1 0) (base 0 1 1) (base 0 1 2) (base 0 1 3) (base 0 1 4)]
        [(base 0 2 0) "DISSOC    " (base 0 2 2) (base 0 2 3) (base 0 2 4)]
        [(base 0 3 0) (base 0 3 1) (base 0 3 2) (base 0 3 3) (base 0 3 4)]
        [(base 0 4 0) (base 0 4 1) (base 0 4 2)]
        [(base 0 5 0) (base 0 5 1)]
        [(base 0 6 0) (base 0 6 1)]
        [(base 0 7 0) (base 0 7 1)]]

       [[(base 1 0 0) (base 1 0 1) (base 1 0 2) (base 1 0 3) (base 1 0 4)]
        [(base 1 1 0) "DEF       " "D         " (base 1 1 3) (base 1 1 4)]
        [(base 1 2 0) "DEFTEST   " (base 1 2 2) "DEFN      " (base 1 2 4)]
        [(base 1 3 0) "DEFMETHOD" (base 1 3 2) (base 1 3 3) (base 1 3 4)]
        [(base 1 4 0) (base 1 4 1) (base 1 4 2)]
        [(base 1 5 0) (base 1 5 1)]
        [(base 1 6 0) (base 1 6 1)]
        [(base 1 7 0) (base 1 7 1)]])

     ; E Layer
     10
     (make-layer
       [[(base 0 0 0) (base 0 0 1) (base 0 0 2) (base 0 0 3) (base 0 0 4)]
        [(base 0 1 0) (base 0 1 1) (base 0 1 2) (base 0 1 3) (base 0 1 4)]
        [(base 0 2 0) (base 0 2 1) (base 0 2 2) "E         " (base 0 2 4)]
        [(base 0 3 0) (base 0 3 1) (base 0 3 2) (base 0 3 3) (base 0 3 4)]
        [(base 0 4 0) (base 0 4 1) (base 0 4 2)]
        [(base 0 5 0) (base 0 5 1)]
        [(base 0 6 0) (base 0 6 1)]
        [(base 0 7 0) (base 0 7 1)]]

       [[(base 1 0 0) "EXCEL     " (base 1 0 2) (base 1 0 3) (base 1 0 4)]
        [(base 1 1 0) (base 1 1 1) (base 1 1 2) (base 1 1 3) (base 1 1 4)]
        [(base 1 2 0) (base 1 2 1) (base 1 2 2) (base 1 2 3) (base 1 2 4)]
        [(base 1 3 0) (base 1 3 1) (base 1 3 2) (base 1 3 3) (base 1 3 4)]
        [(base 1 4 0) (base 1 4 1) (base 1 4 2)]
        [(base 1 5 0) (base 1 5 1)]
        [(base 1 6 0) (base 1 6 1)]
        [(base 1 7 0) (base 1 7 1)]])

     ; F Layer
     11
     (make-layer
       [[(base 0 0 0) (base 0 0 1) (base 0 0 2) (base 0 0 3) (base 0 0 4)]
        [(base 0 1 0) (base 0 1 1) (base 0 1 2) "CLJS_QUIT " (base 0 1 4)]
        [(base 0 2 0) "FILTER    " (base 0 2 2) (base 0 2 3) (base 0 2 4)]
        [(base 0 3 0) (base 0 3 1) (base 0 3 2) (base 0 3 3) (base 0 3 4)]
        [(base 0 4 0) (base 0 4 1) (base 0 4 2)]
        [(base 0 5 0) (base 0 5 1)]
        [(base 0 6 0) (base 0 6 1)]
        [(base 0 7 0) (base 0 7 1)]]

       [[(base 1 0 0) (base 1 0 1) (base 1 0 2) "FIGWHEEL  " (base 1 0 4)]
        [(base 1 1 0) "F         " (base 1 1 2) "CLJS_REPL " (base 1 1 4)]
        [(base 1 2 0) (base 1 2 1) (base 1 2 2) (base 1 2 3) (base 1 2 4)]
        [(base 1 3 0) (base 1 3 1) (base 1 3 2) (base 1 3 3) (base 1 3 4)]
        [(base 1 4 0) (base 1 4 1) (base 1 4 2)]
        [(base 1 5 0) (base 1 5 1)]
        [(base 1 6 0) (base 1 6 1)]
        [(base 1 7 0) (base 1 7 1)]])

     ; G Layer
     12
     (make-layer
       [[(base 0 0 0) (base 0 0 1) (base 0 0 2) (base 0 0 3) (base 0 0 4)]
        [(base 0 1 0) (base 0 1 1) (base 0 1 2) (base 0 1 3) (base 0 1 4)]
        [(base 0 2 0) (base 0 2 1) (base 0 2 2) (base 0 2 3) (base 0 2 4)]
        [(base 0 3 0) (base 0 3 1) (base 0 3 2) (base 0 3 3) (base 0 3 4)]
        [(base 0 4 0) (base 0 4 1) (base 0 4 2)]
        [(base 0 5 0) (base 0 5 1)]
        [(base 0 6 0) (base 0 6 1)]
        [(base 0 7 0) (base 0 7 1)]]

       [[(base 1 0 0) (base 1 0 1) (base 1 0 2) (base 1 0 3) (base 1 0 4)]
        [(base 1 1 0) (base 1 1 1) (base 1 1 2) (base 1 1 3) (base 1 1 4)]
        [(base 1 2 0) (base 1 2 1) (base 1 2 2) (base 1 2 3) (base 1 2 4)]
        [(base 1 3 0) (base 1 3 1) "G         " (base 1 3 3) (base 1 3 4)]
        [(base 1 4 0) (base 1 4 1) (base 1 4 2)]
        [(base 1 5 0) (base 1 5 1)]
        [(base 1 6 0) (base 1 6 1)]
        [(base 1 7 0) (base 1 7 1)]])

     ; H Layer
     13
     (make-layer
       [[(base 0 0 0) (base 0 0 1) (base 0 0 2) (base 0 0 3) (base 0 0 4)]
        [(base 0 1 0) (base 0 1 1) (base 0 1 2) (base 0 1 3) (base 0 1 4)]
        [(base 0 2 0) (base 0 2 1) (base 0 2 2) (base 0 2 3) (base 0 2 4)]
        [(base 0 3 0) (base 0 3 1) (base 0 3 2) (base 0 3 3) (base 0 3 4)]
        [(base 0 4 0) (base 0 4 1) (base 0 4 2)]
        [(base 0 5 0) (base 0 5 1)]
        [(base 0 6 0) (base 0 6 1)]
        [(base 0 7 0) (base 0 7 1)]]

       [[(base 1 0 0) (base 1 0 1) (base 1 0 2) (base 1 0 3) (base 1 0 4)]
        [(base 1 1 0) (base 1 1 1) (base 1 1 2) (base 1 1 3) (base 1 1 4)]
        [(base 1 2 0) "H         " (base 1 2 2) (base 1 2 3) (base 1 2 4)]
        [(base 1 3 0) (base 1 3 1) (base 1 3 2) (base 1 3 3) (base 1 3 4)]
        [(base 1 4 0) (base 1 4 1) (base 1 4 2)]
        [(base 1 5 0) (base 1 5 1)]
        [(base 1 6 0) (base 1 6 1)]
        [(base 1 7 0) (base 1 7 1)]])

     ; I Layer
     14
     (make-layer
       [[(base 0 0 0) (base 0 0 1) (base 0 0 2) (base 0 0 3) (base 0 0 4)]
        [(base 0 1 0) (base 0 1 1) (base 0 1 2) (base 0 1 3) (base 0 1 4)]
        [(base 0 2 0) "I         " (base 0 2 2) (base 0 2 3) (base 0 2 4)]
        [(base 0 3 0) (base 0 3 1) (base 0 3 2) (base 0 3 3) (base 0 3 4)]
        [(base 0 4 0) (base 0 4 1) (base 0 4 2)]
        [(base 0 5 0) (base 0 5 1)]
        [(base 0 6 0) (base 0 6 1)]
        [(base 0 7 0) (base 0 7 1)]
        ]

       [[(base 1 0 0) (base 1 0 1) (base 1 0 2) (base 1 0 3) (base 1 0 4)]
        [(base 1 1 0) (base 1 1 1) (base 1 1 2) "IG_RESET  " (base 1 1 4)]
        [(base 1 2 0) "IG_HALT   " (base 1 2 2) (base 1 2 3) (base 1 2 4)]
        [(base 1 3 0) (base 1 3 1) "IG_GO     " (base 1 3 3) (base 1 3 4)]
        [(base 1 4 0) (base 1 4 1) (base 1 4 2)]
        [(base 1 5 0) (base 1 5 1)]
        [(base 1 6 0) (base 1 6 1)]
        [(base 1 7 0) (base 1 7 1)]])

     ; J Layer
     15
     (make-layer
       [[(base 0 0 0) "J         " (base 0 0 2) (base 0 0 3) (base 0 0 4)]
        [(base 0 1 0) (base 0 1 1) (base 0 1 2) (base 0 1 3) (base 0 1 4)]
        [(base 0 2 0) (base 0 2 1) (base 0 2 2) (base 0 2 3) (base 0 2 4)]
        [(base 0 3 0) (base 0 3 1) (base 0 3 2) (base 0 3 3) (base 0 3 4)]
        [(base 0 4 0) (base 0 4 1) (base 0 4 2)]
        [(base 0 5 0) (base 0 5 1)]
        [(base 0 6 0) (base 0 6 1)]
        [(base 0 7 0) (base 0 7 1)]]

       [[(base 1 0 0) (base 1 0 1) (base 1 0 2) (base 1 0 3) (base 1 0 4)]
        [(base 1 1 0) (base 1 1 1) (base 1 1 2) (base 1 1 3) (base 1 1 4)]
        [(base 1 2 0) (base 1 2 1) (base 1 2 2) (base 1 2 3) (base 1 2 4)]
        [(base 1 3 0) (base 1 3 1) (base 1 3 2) (base 1 3 3) (base 1 3 4)]
        [(base 1 4 0) (base 1 4 1) (base 1 4 2)]
        [(base 1 5 0) (base 1 5 1)]
        [(base 1 6 0) (base 1 6 1)]
        [(base 1 7 0) (base 1 7 1)]])

     ; K Layer
     16
     (make-layer
       [[(base 0 0 0) (base 0 0 1) (base 0 0 2) (base 0 0 3) (base 0 0 4)]
        [(base 0 1 0) (base 0 1 1) (base 0 1 2) (base 0 1 3) (base 0 1 4)]
        [(base 0 2 0) (base 0 2 1) (base 0 2 2) (base 0 2 3) (base 0 2 4)]
        [(base 0 3 0) (base 0 3 1) (base 0 3 2) (base 0 3 3) (base 0 3 4)]
        [(base 0 4 0) (base 0 4 1) (base 0 4 2)]
        [(base 0 5 0) (base 0 5 1)]
        [(base 0 6 0) (base 0 6 1)]
        [(base 0 7 0) (base 0 7 1)]]

       [[(base 1 0 0) (base 1 0 1) (base 1 0 2) (base 1 0 3) (base 1 0 4)]
        ["K         " (base 1 1 1) (base 1 1 2) (base 1 1 3) (base 1 1 4)]
        [(base 1 2 0) (base 1 2 1) (base 1 2 2) (base 1 2 3) (base 1 2 4)]
        [(base 1 3 0) (base 1 3 1) (base 1 3 2) (base 1 3 3) (base 1 3 4)]
        [(base 1 4 0) (base 1 4 1) (base 1 4 2)]
        [(base 1 5 0) (base 1 5 1)]
        [(base 1 6 0) (base 1 6 1)]
        [(base 1 7 0) (base 1 7 1)]])


     ; L Layer
     17
     (make-layer
       [[(base 0 0 0) (base 0 0 1) (base 0 0 2) (base 0 0 3) (base 0 0 4)]
        [(base 0 1 0) (base 0 1 1) (base 0 1 2) (base 0 1 3) (base 0 1 4)]
        [(base 0 2 0) (base 0 2 1) (base 0 2 2) (base 0 2 3) (base 0 2 4)]
        [(base 0 3 0) (base 0 3 1) (base 0 3 2) (base 0 3 3) (base 0 3 4)]
        [(base 0 4 0) (base 0 4 1) (base 0 4 2)]
        [(base 0 5 0) (base 0 5 1)]
        [(base 0 6 0) (base 0 6 1)]
        [(base 0 7 0) (base 0 7 1)]]

       [[(base 1 0 0) (base 1 0 1) (base 1 0 2) (base 1 0 3) (base 1 0 4)]
        [(base 1 1 0) (base 1 1 1) (base 1 1 2) (base 1 1 3) (base 1 1 4)]
        [(base 1 2 0) (base 1 2 1) (base 1 2 2) (base 1 2 3) (base 1 2 4)]
        [(base 1 3 0) (base 1 3 1) (base 1 3 2) "L         " (base 1 3 4)]
        [(base 1 4 0) (base 1 4 1) (base 1 4 2)]
        [(base 1 5 0) (base 1 5 1)]
        [(base 1 6 0) (base 1 6 1)]
        [(base 1 7 0) (base 1 7 1)]])

     ; M Layer
     18
     (make-layer
       [[(base 0 0 0) (base 0 0 1) (base 0 0 2) (base 0 0 3) (base 0 0 4)]
        [(base 0 1 0) (base 0 1 1) (base 0 1 2) (base 0 1 3) (base 0 1 4)]
        [(base 0 2 0) (base 0 2 1) (base 0 2 2) (base 0 2 3) (base 0 2 4)]
        [(base 0 3 0) (base 0 3 1) (base 0 3 2) (base 0 3 3) (base 0 3 4)]
        [(base 0 4 0) (base 0 4 1) (base 0 4 2)]
        [(base 0 5 0) (base 0 5 1)]
        [(base 0 6 0) (base 0 6 1)]
        [(base 0 7 0) (base 0 7 1)]]

       [[(base 1 0 0) (base 1 0 1) (base 1 0 2) (base 1 0 3) (base 1 0 4)]
        [(base 1 1 0) (base 1 1 1) (base 1 1 2) (base 1 1 3) (base 1 1 4)]
        [(base 1 2 0) (base 1 2 1) (base 1 2 2) (base 1 2 3) (base 1 2 4)]
        [(base 1 3 0) "M         " (base 1 3 2) (base 1 3 3) (base 1 3 4)]
        [(base 1 4 0) (base 1 4 1) (base 1 4 2)]
        [(base 1 5 0) (base 1 5 1)]
        [(base 1 6 0) (base 1 6 1)]
        [(base 1 7 0) (base 1 7 1)]])

     ; N Layer
     19
     (make-layer
       [[(base 0 0 0) (base 0 0 1) (base 0 0 2) (base 0 0 3) (base 0 0 4)]
        [(base 0 1 0) (base 0 1 1) (base 0 1 2) (base 0 1 3) (base 0 1 4)]
        [(base 0 2 0) (base 0 2 1) (base 0 2 2) (base 0 2 3) (base 0 2 4)]
        [(base 0 3 0) (base 0 3 1) (base 0 3 2) (base 0 3 3) (base 0 3 4)]
        [(base 0 4 0) (base 0 4 1) (base 0 4 2)]
        [(base 0 5 0) (base 0 5 1)]
        [(base 0 6 0) (base 0 6 1)]
        [(base 0 7 0) (base 0 7 1)]]

       [[(base 1 0 0) (base 1 0 1) (base 1 0 2) (base 1 0 3) (base 1 0 4)]
        [(base 1 1 0) (base 1 1 1) (base 1 1 2) (base 1 1 3) (base 1 1 4)]
        [(base 1 2 0) (base 1 2 1) (base 1 2 2) "N         " (base 1 2 4)]
        [(base 1 3 0) (base 1 3 1) (base 1 3 2) (base 1 3 3) (base 1 3 4)]
        [(base 1 4 0) (base 1 4 1) (base 1 4 2)]
        [(base 1 5 0) (base 1 5 1)]
        [(base 1 6 0) (base 1 6 1)]
        [(base 1 7 0) (base 1 7 1)]])

     ; O layer
     20
     (make-layer
       [[(base 0 0 0) (base 0 0 1) (base 0 0 2) (base 0 0 3) (base 0 0 4)]
        [(base 0 1 0) (base 0 1 1) "O         " (base 0 1 3) (base 0 1 4)]
        [(base 0 2 0) (base 0 2 1) (base 0 2 2) (base 0 2 3) (base 0 2 4)]
        [(base 0 3 0) (base 0 3 1) (base 0 3 2) (base 0 3 3) (base 0 3 4)]
        [(base 0 4 0) (base 0 4 1) (base 0 4 2)]
        [(base 0 5 0) (base 0 5 1)]
        [(base 0 6 0) (base 0 6 1)]
        [(base 0 7 0) (base 0 7 1)]]

       [[(base 1 0 0) (base 1 0 1) (base 1 0 2) (base 1 0 3) (base 1 0 4)]
        [(base 1 1 0) (base 1 1 1) (base 1 1 2) (base 1 1 3) (base 1 1 4)]
        [(base 1 2 0) (base 1 2 1) (base 1 2 2) (base 1 2 3) (base 1 2 4)]
        [(base 1 3 0) (base 1 3 1) (base 1 3 2) (base 1 3 3) (base 1 3 4)]
        [(base 1 4 0) (base 1 4 1) (base 1 4 2)]
        [(base 1 5 0) (base 1 5 1)]
        [(base 1 6 0) (base 1 6 1)]
        [(base 1 7 0) (base 1 7 1)]])

     ; P layer
     21
     (make-layer
       [[(base 0 0 0) (base 0 0 1) (base 0 0 2) (base 0 0 3) (base 0 0 4)]
        [(base 0 1 0) (base 0 1 1) (base 0 1 2) (base 0 1 3) (base 0 1 4)]
        [(base 0 2 0) (base 0 2 1) (base 0 2 2) (base 0 2 3) (base 0 2 4)]
        [(base 0 3 0) (base 0 3 1) (base 0 3 2) (base 0 3 3) (base 0 3 4)]
        [(base 0 4 0) (base 0 4 1) (base 0 4 2)]
        [(base 0 5 0) (base 0 5 1)]
        [(base 0 6 0) (base 0 6 1)]
        [(base 0 7 0) (base 0 7 1)]]

       [[(base 1 0 0) (base 1 0 1) "P         " (base 1 0 3) (base 1 0 4)]
        [(base 1 1 0) (base 1 1 1) (base 1 1 2) "PRINTLN   " (base 1 1 4)]
        [(base 1 2 0) (base 1 2 1) (base 1 2 2) (base 1 2 3) (base 1 2 4)]
        [(base 1 3 0) (base 1 3 1) (base 1 3 2) (base 1 3 3) (base 1 3 4)]
        [(base 1 4 0) (base 1 4 1) (base 1 4 2)]
        [(base 1 5 0) (base 1 5 1)]
        [(base 1 6 0) (base 1 6 1)]
        [(base 1 7 0) (base 1 7 1)]])

     ; Q layer
     22
     (make-layer
       [[(base 0 0 0) "JOIN      " "Q         " (base 0 0 4) (base 0 0 4)]
        [(base 0 1 0) (base 0 1 1) "OUTER     " "UNION     " (base 0 1 4)]
        [(base 0 2 0) "INNER     " (base 0 2 2) (base 0 2 3) (base 0 2 4)]
        [(base 0 3 0) (base 0 3 1) (base 0 3 2) (base 0 3 3) (base 0 3 4)]
        [(base 0 4 0) (base 0 4 1) "SELECT    "]
        [(base 0 5 0) (base 0 5 1)]
        [(base 0 6 0) (base 0 6 1)]
        [(base 0 7 0) (base 0 7 1)]]

       [[(base 1 0 0) (base 1 0 1) (base 1 0 2) "WHERE     " (base 1 0 4)]
        [(base 1 1 0) "FROM      " "DISTINCT  " "RIGHT     " (base 1 1 4)]
        [(base 1 2 0) "HAVING    " (base 1 2 2) "SQL_NULL  " "VIEW      "]
        [(base 1 3 0) (base 1 3 1) "GROUP_BY  " "LEFT      " "BETWEEN   "]
        ["CREATE    " (base 1 4 1) (base 1 4 2)]
        [(base 1 5 0) (base 1 5 1)]
        [(base 1 6 0) (base 1 6 1)]
        [(base 1 7 0) (base 1 7 1)]])

     ; R Layer
     23
     (make-layer
       [[(base 0 0 0) (base 0 0 1) "REQUIRE   " (base 0 0 3) (base 0 0 4)]
        [(base 0 1 0) (base 0 1 1) (base 0 1 2) (base 0 1 3) (base 0 1 4)]
        [(base 0 2 0) (base 0 2 1) (base 0 2 2) (base 0 2 3) (base 0 2 4)]
        [(base 0 3 0) (base 0 3 1) (base 0 3 2) (base 0 3 3) (base 0 3 4)]
        [(base 0 4 0) (base 0 4 1) (base 0 4 2)]
        [(base 0 5 0) (base 0 5 1)]
        [(base 0 6 0) (base 0 6 1)]
        [(base 0 7 0) (base 0 7 1)]]

       [[(base 1 0 0) "REPL_CLEAR" "REPL_TOP  " (base 1 0 3) (base 1 0 4)]
        [(base 1 1 0) "REPL_FILE " "KAOCHA_RUN" "R         " (base 1 1 4)]
        [(base 1 2 0) (base 1 2 1) (base 1 2 2) "REPL_NS   " (base 1 2 4)]
        [(base 1 3 0) "FORMAT_CODE" (base 1 3 2) (base 1 3 3) (base 1 3 4)]
        [(base 1 4 0) (base 1 4 1) (base 1 4 2)]
        [(base 1 5 0) (base 1 5 1)]
        [(base 1 6 0) (base 1 6 1)]
        [(base 1 7 0) (base 1 7 1)]])

     ; S layer
     24
     (make-layer
       [[(base 0 0 0) "WRAP_DQT  " "S_TILDE   " "KC_DQT    " "S_TILDE   "]
        [(base 0 1 0) "S_DQT     " "KC_QUOT   " "          " "S_PIPE    "]
        [(base 0 2 0) "          " "KC_EQL    " "S_PLUS    " "S_QUES    "]
        [(base 0 3 0) "S_LT      " "S_GT      " "S_UNDS    " "KC_SCLN   "]
        [(base 0 4 0) (base 0 4 1) "S         "]
        [(base 0 5 0) (base 0 5 1)]
        [(base 0 6 0) (base 0 6 1)]
        [(base 0 7 0) (base 0 7 1)]]

       [[(base 1 0 0) (base 1 0 1) (base 1 0 2) (base 1 0 3) (base 1 0 4)]
        [(base 1 1 0) "SPEC_FDEF " "SPEC_DEF  " (base 1 1 3) (base 1 1 4)]
        [(base 1 2 0) (base 1 2 1) (base 1 2 2) (base 1 2 3) (base 1 2 4)]
        [(base 1 3 0) (base 1 3 1) (base 1 3 2) (base 1 3 3) (base 1 3 4)]
        [(base 1 4 0) (base 1 4 1) (base 1 4 2)]
        [(base 1 5 0) (base 1 5 1)]
        [(base 1 6 0) (base 1 6 1)]
        [(base 1 7 0) (base 1 7 1)]])

     ; T layer
     25
     (make-layer
       [[(base 0 0 0) (base 0 0 1) (base 0 0 2) (base 0 0 3) (base 0 0 4)]
        [(base 0 1 0) (base 0 1 1) (base 0 1 2) (base 0 1 3) (base 0 1 4)]
        [(base 0 2 0) (base 0 2 1) (base 0 2 2) (base 0 2 3) (base 0 2 4)]
        [(base 0 3 0) (base 0 3 1) (base 0 3 2) (base 0 3 3) (base 0 3 4)]
        [(base 0 4 0) (base 0 4 1) (base 0 4 2)]
        [(base 0 5 0) (base 0 5 1)]
        [(base 0 6 0) (base 0 6 1)]
        [(base 0 7 0) (base 0 7 1)]]

       [[(base 1 0 0) (base 1 0 1) (base 1 0 2) (base 1 0 3) (base 1 0 4)]
        [(base 1 1 0) (base 1 1 1) (base 1 1 2) "TRUE_STR  " (base 1 1 4)]
        [(base 1 2 0) (base 1 2 1) "T         " (base 1 2 3) (base 1 2 4)]
        [(base 1 3 0) (base 1 3 1) (base 1 3 2) (base 1 3 3) (base 1 3 4)]
        [(base 1 4 0) (base 1 4 1) (base 1 4 2)]
        [(base 1 5 0) (base 1 5 1)]
        [(base 1 6 0) (base 1 6 1)]
        [(base 1 7 0) (base 1 7 1)]])

     ; U layer
     26
     (make-layer
       [[(base 0 0 0) (base 0 0 1) (base 0 0 2) (base 0 0 3) (base 0 0 4)]
        [(base 0 1 0) (base 0 1 1) (base 0 1 2) "U         " (base 0 1 4)]
        [(base 0 2 0) (base 0 2 1) (base 0 2 2) (base 0 2 3) (base 0 2 4)]
        [(base 0 3 0) (base 0 3 1) (base 0 3 2) (base 0 3 3) (base 0 3 4)]
        [(base 0 4 0) (base 0 4 1) (base 0 4 2)]
        [(base 0 5 0) (base 0 5 1)]
        [(base 0 6 0) (base 0 6 1)]
        [(base 0 7 0) (base 0 7 1)]]

       [[(base 1 0 0) (base 1 0 1) (base 1 0 2) (base 1 0 3) (base 1 0 4)]
        [(base 1 1 0) (base 1 1 1) (base 1 1 2) (base 1 1 3) (base 1 1 4)]
        [(base 1 2 0) (base 1 2 1) (base 1 2 2) (base 1 2 3) (base 1 2 4)]
        [(base 1 3 0) (base 1 3 1) (base 1 3 2) (base 1 3 3) (base 1 3 4)]
        [(base 1 4 0) (base 1 4 1) (base 1 4 2)]
        [(base 1 5 0) (base 1 5 1)]
        [(base 1 6 0) (base 1 6 1)]
        [(base 1 7 0) (base 1 7 1)]])

     ; V layer
     27
     (make-layer
       [[(base 0 0 0) (base 0 0 1) (base 0 0 2) (base 0 0 3) (base 0 0 4)]
        [(base 0 1 0) (base 0 1 1) (base 0 1 2) (base 0 1 3) (base 0 1 4)]
        [(base 0 2 0) (base 0 2 1) (base 0 2 2) (base 0 2 3) (base 0 2 4)]
        [(base 0 3 0) (base 0 3 1) (base 0 3 2) (base 0 3 3) (base 0 3 4)]
        [(base 0 4 0) (base 0 4 1) (base 0 4 2)]
        [(base 0 5 0) (base 0 5 1)]
        [(base 0 6 0) (base 0 6 1)]
        [(base 0 7 0) (base 0 7 1)]]

       [[(base 1 0 0) (base 1 0 1) (base 1 0 2) (base 1 0 2) (base 1 0 4)]
        [(base 1 1 0) (base 1 1 1) (base 1 1 2) (base 1 1 3) (base 1 1 4)]
        ["V         " (base 1 2 1) (base 1 2 2) (base 1 2 3) (base 1 2 4)]
        [(base 1 3 0) (base 1 3 1) (base 1 3 2) (base 1 3 3) (base 1 3 4)]
        [(base 1 4 0) (base 1 4 1) (base 1 4 2)]
        [(base 1 5 0) (base 1 5 1)]
        [(base 1 6 0) (base 1 6 1)]
        [(base 1 7 0) (base 1 7 1)]
        ])

     ; W Layer
     28
     (make-layer
       [[(base 0 0 0) (base 0 0 1) (base 0 0 2) (base 0 0 3) (base 0 0 4)]
        [(base 0 1 0) (base 0 1 1) (base 0 1 2) (base 0 1 3) (base 0 1 4)]
        [(base 0 2 0) (base 0 2 1) (base 0 2 2) (base 0 2 3) (base 0 2 4)]
        [(base 0 3 0) (base 0 3 1) (base 0 3 2) (base 0 3 3) (base 0 3 4)]
        [(base 0 4 0) (base 0 4 1) "WORKSHEET"]
        [(base 0 5 0) (base 0 5 1)]
        [(base 0 6 0) (base 0 6 1)]
        [(base 0 7 0) (base 0 7 1)]]

       [[(base 1 0 0) (base 1 0 1) (base 1 0 2) "W         " (base 1 0 4)]
        ["WORK      " (base 1 1 1) (base 1 1 2) (base 1 1 3) (base 1 1 4)]
        [(base 1 2 0) (base 1 2 1) (base 1 2 2) (base 1 2 3) (base 1 2 4)]
        ["WORKBOOK  " (base 1 3 1) (base 1 3 2) (base 1 3 3) (base 1 3 4)]
        [(base 1 4 0) (base 1 4 1) (base 1 4 2)]
        [(base 1 5 0) (base 1 5 1)]
        [(base 1 6 0) (base 1 6 1)]
        [(base 1 7 0) (base 1 7 1)]
        ])

     ; X layer
     29
     (make-layer
       [[(base 0 0 0) (base 0 0 1) (base 0 0 2) (base 0 0 3) (base 0 0 4)]
        [(base 0 1 0) (base 0 1 1) (base 0 1 2) (base 0 1 3) (base 0 1 4)]
        [(base 0 2 0) (base 0 2 1) (base 0 2 2) (base 0 2 3) (base 0 2 4)]
        [(base 0 3 0) (base 0 3 1) (base 0 3 2) (base 0 3 3) (base 0 3 4)]
        [(base 0 4 0) (base 0 4 1) (base 0 4 2)]
        [(base 0 5 0) (base 0 5 1)]
        [(base 0 6 0) (base 0 6 1)]
        [(base 0 7 0) (base 0 7 1)]]

       [[(base 1 0 0) "X         " (base 1 0 2) (base 1 0 3) (base 1 0 4)]
        [(base 1 1 0) (base 1 1 1) (base 1 1 2) (base 1 1 3) (base 1 1 4)]
        [(base 1 2 0) (base 1 2 1) (base 1 2 2) (base 1 2 3) (base 1 2 4)]
        [(base 1 3 0) (base 1 3 1) (base 1 3 2) (base 1 3 3) (base 1 3 4)]
        [(base 1 4 0) (base 1 4 1) (base 1 4 2)]
        [(base 1 5 0) (base 1 5 1)]
        [(base 1 6 0) (base 1 6 1)]
        [(base 1 7 0) (base 1 7 1)]])

     ; Y layer
     30
     (make-layer
       [[(base 0 0 0) (base 0 0 1) (base 0 0 2) (base 0 0 3) (base 0 0 4)]
        [(base 0 1 0) "Y         " (base 0 1 2) (base 0 1 3) (base 0 1 4)]
        [(base 0 2 0) (base 0 2 1) (base 0 2 2) (base 0 2 3) (base 0 2 4)]
        [(base 0 3 0) (base 0 3 1) (base 0 3 2) (base 0 3 3) (base 0 3 4)]
        [(base 0 4 0) (base 0 4 1) (base 0 4 2)]
        [(base 0 5 0) (base 0 5 1)]
        [(base 0 6 0) (base 0 6 1)]
        [(base 0 7 0) (base 0 7 1)]
        ]

       [[(base 1 0 0) (base 1 0 1) (base 1 0 2) (base 1 0 3) (base 1 0 4)]
        [(base 1 1 0) (base 1 1 1) (base 1 1 2) (base 1 1 3) (base 1 1 4)]
        [(base 1 2 0) (base 1 2 1) (base 1 2 2) (base 1 2 3) (base 1 2 4)]
        [(base 1 3 0) (base 1 3 1) (base 1 3 2) (base 1 3 3) (base 1 3 4)]
        [(base 1 4 0) (base 1 4 1) (base 1 4 2)]
        [(base 1 5 0) (base 1 5 1)]
        [(base 1 6 0) (base 1 6 1)]
        [(base 1 7 0) (base 1 7 1)]])

     ; Steno Layer
     31
     (steno-layer-gemini)
     }))