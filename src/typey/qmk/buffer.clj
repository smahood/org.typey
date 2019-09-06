(ns typey.qmk.buffer
  (:require [clojure.string :as str]))


(defn init_buffer_position []
  (str/join "\n"
            ["static void init_buffer_position(int pos) {"
             "  if ((pos >= BUF_START) && (pos < BUF_STOP)) {"
             "    buffer[pos].row = 0;"
             "    buffer[pos].col = 0;"
             "    buffer[pos].active_layer = 0;"
             "    buffer[pos].keycode = 0;"
             "    buffer[pos].child_released = false;"
             "    buffer[pos].shifted = false;"
             "    buffer[pos].ctrled = false;"
             "    buffer[pos].alted = false;"
             "    buffer[pos].supered = false;"
             "  }"
             "};"]))


(defn init_all_buffers []
  (str/join "\n"
            ["static void init_all_buffers (void) {"
             "  for (int i = BUF_START; i < BUF_STOP; i++) {"
             "    init_buffer_position(i); "
             "  }"
             "};"]))



(defn find_next_buffer_pos []
  (str/join "\n"
            ["int find_next_buffer_pos (void) {"
             "  for (int i = BUF_START; i < BUF_STOP; i++) {"
             "    if (buffer[i].keycode != 0) {"
             "      return i;"
             "    }"
             "  }"
             "  return 0;"
             "};"]))

(defn print_bool []
  (str/join "\n"
            ["static void print_bool (bool x) {"
             "  if (x == true) { "
             "    SEND_STRING(\"Y\"); "
             "  }"
             "  else { "
             "    SEND_STRING(\"N\"); "
             "  }"
             "};"]))


(defn print_buffer_pos []
  (str/join "\n"
            ["static void print_buffer_pos (int pos) {"
             "  char str[12];"
             "  if ((pos >= 0) && (pos < 10)) {"
             "    if (buffer[pos].keycode != 0 ) {"
             "      SEND_STRING(\"pos: \");"
             "      SEND_STRING(itoa(pos, str, 10));"
             "      SEND_STRING(\" - row: \");"
             "      SEND_STRING(itoa(buffer[pos].row, str, 10));"
             "      SEND_STRING(\" - col: \");"
             "      SEND_STRING(itoa(buffer[pos].col, str, 10));"
             "      SEND_STRING(\" - active_layer: \");"
             "      SEND_STRING(itoa( buffer[pos].active_layer, str, 10));"
             "      SEND_STRING(\" - code: \");"
             "      SEND_STRING(itoa(buffer[pos].keycode, str, 10));"
             "      SEND_STRING(\" - shifted: \");"
             "      print_bool(buffer[pos].shifted);"
             "      SEND_STRING(\" - ctrled: \");"
             "      print_bool(buffer[pos].ctrled);"
             "      SEND_STRING(\" - alted: \");"
             "      print_bool(buffer[pos].alted);"
             "      SEND_STRING(\" - supered: \");"
             "      print_bool(buffer[pos].supered);"
             "      SEND_STRING(\" - child_released: \");"
             "      print_bool(buffer[pos].child_released);"
             "      register_code(KC_ENT);"
             "      unregister_code(KC_ENT);"
             "    }"
             "    else {"
             "      SEND_STRING(\"pos: \");"
             "      SEND_STRING(itoa(pos, str, 10));"
             "      register_code(KC_ENT);"
             "      unregister_code(KC_ENT);"
             "    }"
             "  }"
             "};"]))


(defn print_all_buffers []
  (str/join "\n"
            ["static void print_all_buffers (void) {"
             "  bool curr_shifted = (keyboard_report->mods & MOD_BIT(KC_LSHIFT));"
             "  bool curr_ctled = (keyboard_report->mods & MOD_BIT(KC_LCTL));"
             "  bool curr_alted = (keyboard_report->mods & MOD_BIT(KC_LALT));"
             "  bool curr_supered = (keyboard_report->mods & MOD_BIT(KC_LGUI));"
             "  if (curr_shifted) {"
             "    unregister_code(KC_LSFT);"
             "  }"
             "  if (curr_ctled) {"
             "    unregister_code(KC_LCTL);"
             "  }"
             "  if (curr_alted) {"
             "    unregister_code(KC_LALT);"
             "  }"
             "  if (curr_supered) {"
             "    unregister_code(KC_LGUI);"
             "  }"
             "  register_code(KC_ENT);"
             "  unregister_code(KC_ENT);"
             "  SEND_STRING(\"global_shifted: \");"
             "  print_bool(global_shifted);"
             "  SEND_STRING(\" global_shifted_on: \");"
             "  print_bool(global_shifted_on);"
             "  SEND_STRING(\" shift_used: \");"
             "  print_bool(shift_used);"
             "  register_code(KC_ENT);"
             "  unregister_code(KC_ENT);"
             "  for (int i = BUF_START; i < BUF_STOP; i++) {"
             "    print_buffer_pos(i);"
             "  }"
             "  if (curr_shifted) {"
             "    register_code(KC_LSFT);"
             "  }"
             "  if (curr_ctled) {"
             "    register_code(KC_LCTL);"
             "  }"
             "  if (curr_alted) {"
             "    register_code(KC_LALT);"
             "  }"
             "  if (curr_supered) {"
             "    register_code(KC_LGUI);"
             "  }"
             "};"]))


(defn push_buffer []
  (str/join "\n"
            ["static void push_buffer(uint16_t keycode, keyrecord_t *record) {"
             "  bool curr_shifted = (keyboard_report->mods & MOD_BIT(KC_LSHIFT)) | global_shifted | global_shifted_on;"
             "  bool curr_ctled = (keyboard_report->mods & MOD_BIT(KC_LCTL));"
             "  bool curr_alted = (keyboard_report->mods & MOD_BIT(KC_LALT));"
             "  bool curr_supered = (keyboard_report->mods & MOD_BIT(KC_LGUI));"
             "  push_modded_buffer(keycode, record, curr_shifted, curr_ctled, curr_alted, curr_supered);"
             "};"]))


(defn buffer_pos_to_pop []
  (str/join "\n"
            ["static int buffer_pos_to_pop(keyrecord_t *record) {"
             "  for (int i = BUF_START; i < BUF_STOP; i++)  {"
             "    if (buffer[i].row == record->event.key.row && buffer[i].col == record->event.key.col) {"
             "      return i;"
             "    }"
             "  }"
             "  return -1;"
             "};"]))

(defn release_parent []
  (str/join "\n"
            ["static void release_parent(int pos) {"
             "  if (pos > BUF_START) {"
             "    if (buffer[BUF_START].keycode != 0) {"
             "      buffer[BUF_START].child_released = true;"
             "    }"
             "  }"
             "};"]))


(defn push_modded_buffer []
  (str/join "\n"
            ["static void push_modded_buffer(uint16_t keycode, keyrecord_t *record, bool shifted, bool ctrled, bool alted, bool supered) {"
             "  for (int i = BUF_START; i < BUF_STOP; i++)  {"
             "    if (buffer[i].keycode == 0) {"
             "      buffer[i].row = record->event.key.row;"
             "      buffer[i].col = record->event.key.col;"
             "      buffer[i].active_layer = get_current_layer();"
             "      buffer[i].keycode = keycode;"
             "      buffer[i].child_released = false;"
             "      buffer[i].shifted = shifted;"
             "      buffer[i].ctrled = ctrled;"
             "      buffer[i].alted = alted;"
             "      buffer[i].supered = supered;"
             "      break;"
             "    }"
             "  }"
             "};"]))


(defn execute_buffer []
  (str/join "\n"
            ["static void execute_buffer(int pos) {"
             "  bool curr_shifted = (keyboard_report->mods & MOD_BIT(KC_LSHIFT));"
             ;"  bool curr_ctled = (keyboard_report->mods & MOD_BIT(KC_LCTL));"
             ;"  bool curr_alted = (keyboard_report->mods & MOD_BIT(KC_LALT));"
             ;"  bool curr_supered = (keyboard_report->mods & MOD_BIT(KC_LGUI));"
             "  uint16_t keycode = buffer[pos].keycode;"
             "  shift_used = true;"
             "  if (global_shifted == true && global_shifted_on == false) {"
             "    global_shifted = false;"
             "    shift_used = false;"
             "  }"
             "  layer_used = true;"
             "  switch(keycode) {"
             "    case A: keycode = KC_A; break;"
             "    case B: keycode = KC_B; break;"
             "    case C: keycode = KC_C; break;"
             "    case D: keycode = KC_D; break;"
             "    case E: keycode = KC_E; break;"
             "    case F: keycode = KC_F; break;"
             "    case G: keycode = KC_G; break;"
             "    case H: keycode = KC_H; break;"
             "    case I: keycode = KC_I; break;"
             "    case J: keycode = KC_J; break;"
             "    case K: keycode = KC_K; break;"
             "    case L: keycode = KC_L; break;"
             "    case M: keycode = KC_M; break;"
             "    case N: keycode = KC_N; break;"
             "    case O: keycode = KC_O; break;"
             "    case P: keycode = KC_P; break;"
             "    case Q: keycode = KC_Q; break;"
             "    case R: keycode = KC_R; break;"
             "    case S: keycode = KC_S; break;"
             "    case T: keycode = KC_T; break;"
             "    case U: keycode = KC_U; break;"
             "    case V: keycode = KC_V; break;"
             "    case W: keycode = KC_W; break;"
             "    case X: keycode = KC_X; break;"
             "    case Y: keycode = KC_Y; break;"
             "    case Z: keycode = KC_Z; break;"
             "    case COMM: keycode = KC_COMM; break;"
             "    case DOT: keycode = KC_DOT; break;"
             "    case MINS: keycode = KC_MINS; break;"
             "    case COLON: keycode = S_COLON; break;"
             "    case QUOT: keycode = KC_QUOTE; break;"
             "  }"
             "  if (buffer[pos].shifted == false && curr_shifted == true) {"
             "    unregister_code(KC_LSFT);"
             "  }"
             "  if (buffer[pos].shifted == true && curr_shifted == false) {"
             "    register_code(KC_LSFT);"
             "  }"
             "  register_code(keycode);"
             "  unregister_code(keycode);"
             "  if (buffer[pos].shifted == true && curr_shifted == false) {"
             "    unregister_code(KC_LSFT);"
             "  }"
             "  if (buffer[pos].shifted == false && curr_shifted == true) {"
             "    register_code(KC_LSFT);"
             "  }"
             "  if (curr_shifted == true && buffer[pos].shifted == false) {"
             "    register_code(KC_LSFT);"
             "  }"
             "};"]))

(defn pop_child_buffers []
  (str/join "\n"
            ["static void pop_child_buffers(int parent_pos, uint32_t layer) {"
             "  for (int i = parent_pos + 1; i < BUF_STOP; i++)  {"
             "    if (buffer[i].keycode != 0) {"
             ;"      if ((buffer[i].row == 3 && buffer[i].col == 4) || (buffer[i].row == 2 && buffer[i].col == 4)) {"
             ;"        init_buffer_position(i);"
             ;"      }"
             ;"      else {"
             "        keypos_t keypos = (keypos_t){ .row = buffer[i].row, .col = buffer[i].col};"
             "        buffer[i].keycode = keymap_key_to_keycode(layer, keypos);"
             "        buffer[i].shifted = buffer[parent_pos].shifted;"
             "        execute_buffer(i);"
             "        init_buffer_position(i);"
             ;"      }"
             "    }"
             "  }"
             "};"]))


(defn pop_buffer []
  (str/join "\n"
            ["static void pop_buffer(int pos) {"
             "  if (pos >= BUF_START && pos < BUF_STOP) {"
             "    uint32_t layer = buffer[pos].active_layer;"
             "    if (buffer[pos].child_released == false) {"
             "      execute_buffer(pos);"
             "      init_buffer_position(pos);"
             "      pop_child_buffers(pos, layer);"
             "    }"
             "    else {"
             "      init_buffer_position(pos);"
             "      pop_child_buffers(pos, layer);"
             "    }"
             "    if (pos > BUF_START ) {"
             "      release_parent(pos);"
             "    }"
             "    init_buffer_position(pos);"
             "  }"
             "};"]))


(defn pop_buffer_no_release []
  (str/join "\n"
            ["static void pop_buffer_no_release(int pos) {"
             "  if (pos >= BUF_START && pos < BUF_STOP) {"
             "    if (buffer[pos].child_released == false) {"
             "      execute_buffer(pos);"
             "      pop_child_buffers(pos);"
             "    }"
             "    init_buffer_position(pos);"
             "  }"
             "};"]))


(defn pop_buffer_no_execute []
  (str/join "\n"
            ["static void pop_buffer_no_execute(int pos) {"
             "  if (pos >= BUF_START && pos < BUF_STOP) {"
             "    if (pos > BUF_START ) {"
             "      release_parent(pos);"
             "    }"
             "    init_buffer_position(pos);"
             "  }"
             "};"]))


(defn modded_buffer_key []
  (str/join "\n"
            ["static void modded_buffer_key (uint16_t keycode, uint16_t kc_base, keyrecord_t *record, bool shifted, bool ctrled, bool alted, bool supered) {"
             "  if (record->event.pressed) {"
             "    push_modded_buffer(kc_base, record, shifted, ctrled, alted, supered);"
             "  }"
             "  else {"
             "    pop_buffer(buffer_pos_to_pop(record));"
             "  }"
             "};"]))


(defn buffer_key_no_release []
  (str/join "\n"
            ["static void buffer_key_no_release (uint16_t keycode, keyrecord_t *record) {"
             "  if (record->event.pressed) {"
             "     push_buffer(keycode, record);"
             "  }"
             "  else {"
             "    pop_buffer_no_release(buffer_pos_to_pop(record));"
             "  }"
             "};"]))

(defn buffered_key_with_layer []
  (str/join "\n"
            ["static void buffered_key_with_layer (uint16_t keycode, keyrecord_t *record, uint8_t layer) {"
             "  if (record->event.pressed) {"
             "     pop_buffer(0);"
             "     push_buffer(keycode, record);"
             "     layer_on(layer);"
             "  }"
             "  else {"
             "    layer_off(layer);"
             "    pop_buffer(buffer_pos_to_pop(record));"
             "  }"
             "};"]))


(defn ->buffered_key_with_layer [letter layer]
  (str/join ["    case " letter ": "
             "buffered_key_with_layer(KC_" letter ", record, " layer "); "
             "return true;"]))

(defn ->shifted_buffer_key [shifted_kc unshifted_kc]
  (str/join ["    case " shifted_kc ": "
             "if (is_shifted) {"
             "modded_buffer_key(keycode, "
             unshifted_kc
             ", record, false, false, false, false);"
             "}"
             "else {"
             "modded_buffer_key(keycode, "
             unshifted_kc
             ", record, true, false, false, false);"
             "}"
             "return true;"]))


(defn make-buffered-keys-with-layers [keys]
  (str/join "\n"
            (transduce
              (map #(->buffered_key_with_layer (first %) (second %)))
              conj
              keys)))

(defn make-symbols [symbols]
  (str/join "\n" (transduce
                   (map #(->shifted_buffer_key (first %) (second %)))
                   conj
                   symbols)))


(defn make-macros [macros]
  (str/join "\n"
            (transduce
              (map #(str "    case " (first %) ": " (second %)))
              conj
              macros)))




