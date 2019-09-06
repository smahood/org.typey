(ns typey.qmk.process-record-user
  (:require [clojure.string :as str]
            [typey.qmk.buffer :as buffer]
            [typey.qmk.layers :as layers]
            [typey.qmk.shortcut-keys :as shortcuts]))


(defn reset->str []
  (str/join "\n"
            ["    case RESET:  return true;"]))

(defn print-buffers->str []
  (str/join "\n"
            ["    case PRNB:"
             "      if (record->event.pressed) {"
             "        print_all_buffers(); "
             "      }"
             "      return true;"]))

(defn modifiers->str []
  (str/join "\n"
            ["    case BETTER_SFT:"
             "      if (record->event.pressed) {"
             "        shift_used = false;"
             "        global_shifted_on = true;"
             "        global_shifted = true;"
             "      }"
             "      else {"
             "          global_shifted_on = false;"
             "        if (shift_used == true) {"
             "          global_shifted = false;"
             "          shift_used = false;"
             "        }"
             "      }"
             "      return true;"]))



(defn better-layer-toggle->str []
  (str/join "\n"
            ["    case LAYER1: "
             "      better_layer_toggle(keycode, record, 1);"
             "      return true;"
             "    case LAYER2: "
             "      better_layer_toggle(keycode, record, 2);"
             "      return true;"
             "    case LAYER3: "
             "      better_layer_toggle(keycode, record, 3);"
             "      return true;"
             "    case LAYER4: "
             "      better_layer_toggle(keycode, record, 4);"
             "      return true;"
             "    case LAYER5: "
             "      better_layer_toggle(keycode, record, 5);"
             "      return true;"]))


(defn buffered-keys->str []
  (str/join "\n"
            ["        if ((keycode >= 4    && keycode <= 39)  ||"
             "            (keycode >= 45   && keycode <= 56)  ||"
             "            (keycode >= 58   && keycode <= 69)  ||"
             "            (keycode >= 84   && keycode <= 87)  ||"
             "            (keycode >= 89   && keycode <= 100) ||"
             "            (keycode >= 103  && keycode <= 115) ||"
             "            (keycode >= 182 && keycode <= 185))  {"

             "             layer_used = true;"
             "           if ((current_layer == 1) ) {"      ;|| (current_layer == 2) || (current_layer == 3) || (current_layer == 4)
             "             return true;"
             ;"             pop_buffer(buffer_pos_to_pop(record));"
             "           }"
             "           else {"
             "             push_modded_buffer(keycode, record, shifted, false, false, false);"
             "           return false;"
             "           }"
             "        }"]))


(defn set-modifier-state->str []
  (str/join "\n"
            ["        bool shifted = (keyboard_report->mods & MOD_BIT(KC_LSFT));"]))

(defn process-record-user []
  (doall (str/join "\n"
                   ["bool process_record_user(uint16_t keycode, keyrecord_t *record) {"
                    "  bool is_shifted = (keyboard_report->mods & MOD_BIT(KC_LSHIFT));"
                    "  uint8_t current_layer = get_current_layer();"
                    "  if (31 == current_layer) {"
                    "    return true;"
                    "  } "
                    "    else  {"


                    "  switch(keycode) {"
                    "    // RESET should always fire as expected"
                    (reset->str)

                    "    // PRNB is used for debugging buffer functionality"
                    (print-buffers->str)

                    "    // Custom modifer actions - not sure how useful this will be"
                    (modifiers->str)

                    "    // Dual function alphabet keys - when held down, they will "
                    "    // activate a layer with complex buffering capability"
                    (buffer/make-buffered-keys-with-layers (layers/buffered-layer-keys))

                    "    // Shifted symbols - might not be working right yet"
                    (buffer/make-symbols (shortcuts/shifted-symbols))

                    "    // Bigraphs - these are interesting but maybe not in a good way"
                    ;(bigraphs/make-bigraphs (bigraphs/bigraphs))

                    "    // Macros - I might be able to make this less cluttered"
                    (buffer/make-macros (shortcuts/macros))

                    "    // Custom layer toggle"
                    (better-layer-toggle->str)
                    "    default: "
                    "      if (record->event.pressed) {"
                    "        // Set modifier state for use with buffered keys"
                    (set-modifier-state->str)
                    "        // Store keycode in buffer when pressed, will fire later"
                    "        // Used for normal characters (alphabet, numbers, symbols)"

                    (buffered-keys->str)
                    "        // Fire all previously buffered keys, then fire keycode"
                    "        // Used for things like enter"
                    "        // Not working yet"
                    "        else if ((keycode >= 40  && keycode <= 44)   ||"
                    "                 (keycode == 88)                     ||"
                    "                 (keycode >= 116 && keycode <= 132))  {"
                    "          pop_buffer(0);"
                    "          return true;"
                    "        }"
                    "        // Fire when pressed, do not notify parent or change buffers"
                    "        // Used for modifier keys"
                    "        else if ((keycode == 57)                     ||"
                    "                 (keycode >= 224 && keycode <= 231))  {"
                    "          layer_used = true;"
                    "          return true;"
                    "        }"
                    "        else {"
                    "        // Fire when pressed, then signal parent key that something has been pressed"
                    "        // Used for arrow keys and other things that may need to repeat"
                    "          layer_used = true;"
                    "          push_buffer(keycode, record);"
                    "          pop_buffer_no_execute(buffer_pos_to_pop(record));"
                    "          return true;"
                    "        }"
                    "    }"
                    "    // When key is released always attempt to pop (if anything is there) and return true"
                    "    else {"
                    "      pop_buffer(buffer_pos_to_pop(record));"
                    "      return true;"
                    "    }"
                    "  }"
                    "}"
                    "};"

                    ])))