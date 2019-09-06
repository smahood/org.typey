(ns typey.qmk.bigraphs
  (:require [clojure.string :as str]))

(defn bigraphs []
  [["A" "Q"] ["A" "O"] ["A" "A"] ["A" "DOT"]
   ["B" "J"] ["B" "K"] ["B" "F"] ["B" "V"] ["B" "H"] ["B" "B"] ["B" "M"]
   ["C" "SPC"]
   ["D" "P"] ["D" "D"] ["D" "T"] ["D" "G"]
   ["E" "Z"] ["E" "U"] ["E" "E"] ["E" "MINS"]
   ["F" "J"] ["F" "K"] ["F" "F"] ["F" "V"] ["F" "H"] ["F" "B"] ["F" "M"]
   ["G" "P"] ["G" "D"] ["G" "T"] ["G" "G"]
   ["H" "J"] ["H" "K"] ["H" "F"] ["H" "V"] ["H" "H"] ["H" "B"] ["H" "M"]
   ["I" "X"] ["I" "Y"] ["I" "I"] ["I" "COMM"]
   ["J" "J"] ["J" "K"] ["J" "F"] ["J" "V"] ["J" "H"] ["J" "B"] ["J" "M"]
   ["K" "J"] ["K" "K"] ["K" "F"] ["K" "V"] ["K" "H"] ["K" "B"] ["K" "M"]
   ["L" "W"] ["L" "R"] ["L" "N"] ["L" "L"]
   ["M" "J"] ["M" "K"] ["M" "F"] ["M" "V"] ["M" "H"] ["M" "B"] ["M" "M"]
   ["N" "W"] ["N" "R"] ["N" "N"] ["N" "L"]
   ["O" "Q"] ["O" "O"] ["O" "A"] ["O" "DOT"]
   ["P" "P"] ["P" "D"] ["P" "T"] ["P" "G"]
   ["Q" "Q"] ["Q" "O"] ["Q" "A"] ["Q" "DOT"]
   ["R" "W"] ["R" "R"] ["R" "N"] ["R" "L"]
   ; s
   ["T" "P"] ["T" "D"] ["T" "T"] ["T" "G"]
   ["U" "Z"] ["U" "U"] ["U" "E"] ["U" "MINS"]
   ["V" "J"] ["V" "K"] ["V" "F"] ["V" "V"] ["V" "H"] ["V" "B"] ["V" "M"]
   ["W" "W"] ["W" "R"] ["W" "N"] ["W" "L"]
   ["X" "X"] ["X" "Y"] ["X" "I"] ["X" "COMM"]
   ["Y" "X"] ["Y" "Y"] ["Y" "I"] ["Y" "COMM"]
   ["Z" "Z"] ["Z" "U"] ["Z" "E"] ["Z" "MINS"]])

(defn ->bigraph-key [k1 k2]
  (let [kc1 (str/upper-case (str "KC_" (name k1)))
        kc2 (str/upper-case (str "KC_" (name k2)))
        #_(cond
            (= "_" k2) "KC_SPC"
            (= "DOT" k2) "KC_DOT"
            :else (str/upper-case (str "KC_" (name k2))))
        macro-name (str (str/upper-case (name k1))
                        (str/upper-case (name k2)))]
    (str "    "
         "case "
         macro-name
         ": "
         "bigraph_key("
         macro-name
         ", "
         kc1
         ", "
         kc2
         ", record); "
         "return true;")))


(defn make-bigraphs [bigraphs]
  (str/join "\n"
            (transduce
              (map #(->bigraph-key (first %) (second %)))
              conj
              bigraphs)))

(defn bigraph_key []
  (str/join "\n"
            ["static void bigraph_key (uint16_t bigraph_keycode, uint16_t kc1, uint16_t kc2, keyrecord_t *record) {"
             "  if (record->event.pressed) { "
             "    if (keyboard_report->mods & MOD_BIT(KC_LSFT)) {"
             "      push_modded_buffer(bigraph_keycode, record, true, false, false, false);"
             "    }"
             "    else {"
             "      push_buffer(bigraph_keycode, record);"
             "    }"
             "  }"
             "  else {"
             "    if (buffer_pos_to_pop(record) == -1) {"
             "      // no record in buffer, do nothing"
             "      return; "
             "    }"
             "    else if (buffer[buffer_pos_to_pop(record)].keycode != bigraph_keycode) {"
             "      // pop buffer - fix for issue where bigraph keys hijack other layers"
             "      pop_buffer(buffer_pos_to_pop(record));"
             "    }"
             "    else if (buffer[buffer_pos_to_pop(record)].keycode == bigraph_keycode) {"
             "      // bigraph exists in buffer "
             "      if ((buffer_pos_to_pop(record) == 0) && (buffer[1].keycode == 0) && (buffer[2].keycode == 0)) {"
             "        // bigraph at lowest buffer position and following buffers are empty"
             "        register_code(kc2);"
             "        unregister_code(kc2);"
             "      }"
             "      else if (keyboard_report->mods & MOD_BIT(KC_LSFT) || buffer[buffer_pos_to_pop(record)].shifted == true)  {"
             "        register_code(KC_LSFT);"
             "        register_code(kc1);"
             "        unregister_code(kc1);"
             "        unregister_code(KC_LSFT);"
             "        register_code(kc2);"
             "        unregister_code(kc2);"
             "      }"
             "      else {"
             "        register_code(kc1);"
             "        unregister_code(kc1);"
             "        register_code(kc2);"
             "        unregister_code(kc2);"
             "      }"
             "      pop_buffer_no_execute(buffer_pos_to_pop(record));"
             "    }"
             "  }"
             "}; "
             ""]))
