(ns typey.qmk.defs
  (:require [clojure.string :as str]
            [clojure.java.shell :refer [sh with-sh-dir]]
            [typey.qmk.layers :as layers]
            [typey.qmk.buffer :as buffer]))

(def keyboard "ergodox_infinity")

(def layout "shaun")

(def qmk-firmware-path "../qmk_firmware")

(def keymap-output-path
  (str/join "/" [qmk-firmware-path "keyboards" keyboard "keymaps" layout]))

(def keyboard-layout (str keyboard ":" layout))

(def serial-number "mk20dx256vlh7")

(def bin-file (str/replace (str keyboard "_" layout ".bin") #"-" "_"))

(defn includes []
  (str/join "\n"
            ["#include QMK_KEYBOARD_H"
             "#include \"action_layer.h\""
             "#include \"version.h\""
             "#include \"print.h\""
             "#include \"keymap_steno.h\""
             ""]))


(defn defines []
  (str/join "\n"
            ["#define BUF_START 0"
             "#define BUF_STOP 5"
             ""]))


(defn make-enums [symbols macros]
  (str/join
    "\n"
    [(str "enum custom_keycodes {\n"
          "PLACEHOLDER = SAFE_RANGE, "
          "SPACE, A, B, C, D, E, F, G, H, I, J, K, L, M,"
          "N, O, P, Q, R, S, T, U, V, W, X, Y, Z, COMM, DOT, MINS, COLON, QUOT, "
          "PRNB, LAYER1, LAYER2, LAYER3, LAYER4, LAYER5, "
          "BETTER_SFT, "
          (str/join ", " (concat (map #(str (first %)) symbols)))
          ", "
          (str/join ", " (concat (map first macros))))
     "};" ""]))


(defn vars []
  (str/join "\n"
            ["bool layer_used;"
             "bool global_shifted_on;"
             "bool global_shifted;"
             "bool shift_used;"
             "int max_buffer_used = 0;"
             ""
             "struct single_buffer {"
             "  uint8_t row;"
             "  uint8_t col;"
             "  uint32_t active_layer;"
             "  uint16_t keycode;"
             "  bool child_released;"
             "  bool shifted;"
             "  bool ctrled;"
             "  bool alted;"
             "  bool supered;"
             "};"
             ""
             "struct single_buffer buffer[BUF_STOP];"
             ""]))


(defn matrix-init []
  (str/join "\n"
            ["// Runs just one time when the keyboard initializes."
             "void matrix_init_user(void) {"
             "  steno_set_mode(STENO_MODE_GEMINI);"
             "  init_all_buffers();"
             "}"
             ""]))


(defn matrix-scan []
  (str/join "\n"
            ["// Runs constantly in the background, in a loop."
             "void matrix_scan_user(void) {"
             "  uint8_t layer = biton32(layer_state);"
             "  ergodox_board_led_off();"
             "  ergodox_right_led_1_off();"
             "  ergodox_right_led_2_off();"
             "  ergodox_right_led_3_off();"
             "  switch (layer) {"
             "    case 1:"
             "      ergodox_right_led_1_on();"
             "      break;"
             "    case 2:"
             "      ergodox_right_led_2_on();"
             "      break;"
             "    case 3:"
             "      ergodox_right_led_3_on();"
             "      break;"
             "    default:"
             "      break;"
             "  }"
             "}"
             ""]))


(defn buffered-functions []
  (str/join "\n\n"
            [(buffer/init_buffer_position)
             (buffer/init_all_buffers)
             (buffer/find_next_buffer_pos)
             (buffer/print_bool)
             (buffer/print_buffer_pos)
             (buffer/print_all_buffers)
             (layers/get_current_layer)
             (buffer/execute_buffer)
             (buffer/pop_child_buffers)
             (buffer/release_parent)
             (buffer/pop_buffer)
             (buffer/buffer_pos_to_pop)
             (buffer/push_modded_buffer)
             (buffer/push_buffer)
             (buffer/pop_buffer_no_execute)
             (buffer/modded_buffer_key)
             (buffer/buffered_key_with_layer)
             (layers/better_layer_toggle)
             ]))