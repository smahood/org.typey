(ns typey.qmk.shortcut-keys
  (:require [typey.qmk.macros :as m
             :refer [w- c- a- s- k-]]
            [clojure.string :as str]))


(defn string-shortcut [s & space?]
  (if space?
    [s (m/make-buffered-macro s (m/make-code (str s " ")))]
    [s (m/make-buffered-macro s (m/make-code s))]))


(defn named-string-shortcut [s name & space?]
  (if space?
    [name (m/make-buffered-macro name (m/make-code (str s " ")))]
    [name (m/make-buffered-macro name (m/make-code s))]))


(defn immediate-string-shortcut [s & space?]
  (if space?
    [s (m/make-immediate-macro s (m/make-code (str s " ")))]
    [s (m/make-immediate-macro s (m/make-code s))]))


(defn shifted-symbols []
  [["S_A" "KC_A"]
   ["S_B" "KC_B"]
   ["S_C" "KC_C"]
   ["S_D" "KC_D"]
   ["S_E" "KC_E"]
   ["S_F" "KC_F"]
   ["S_G" "KC_G"]
   ["S_H" "KC_H"]
   ["S_I" "KC_I"]
   ["S_J" "KC_J"]
   ["S_K" "KC_K"]
   ["S_L" "KC_L"]
   ["S_M" "KC_M"]
   ["S_N" "KC_N"]
   ["S_O" "KC_O"]
   ["S_P" "KC_P"]
   ["S_Q" "KC_Q"]
   ["S_R" "KC_R"]
   ["S_S" "KC_S"]
   ["S_T" "KC_T"]
   ["S_U" "KC_U"]
   ["S_V" "KC_V"]
   ["S_W" "KC_W"]
   ["S_X" "KC_X"]
   ["S_Y" "KC_Y"]
   ["S_Z" "KC_Z"]

   ["S_TILDE" "KC_GRV"]
   ["S_BANG" "KC_1"]
   ["S_AT" "KC_2"]
   ["S_HASH" "KC_3"]
   ["S_DLR" "KC_4"]
   ["S_PERC" "KC_5"]
   ["S_HAT" "KC_6"]
   ["S_AMPR" "KC_7"]
   ["S_STAR" "KC_8"]
   ["S_LPRN" "KC_9"]
   ["S_RPRN" "KC_0"]
   ["S_UNDS" "KC_MINS"]
   ["S_PLUS" "KC_EQL"]
   ["S_LCBR" "KC_LBRC"]
   ["S_RCBR" "KC_RBRC"]
   ["S_LT" "KC_COMM"]
   ["S_GT" "KC_DOT"]
   ["S_COLON" "KC_SCLN"]
   ["S_PIPE" "KC_BSLS"]
   ["S_QUES" "KC_SLSH"]
   ["S_DQT" "KC_QUOT"]])

(defn vowel-combinations []
  [(string-shortcut "OU")
   (string-shortcut "IO")
   (string-shortcut "EA")
   (string-shortcut "IE")
   (string-shortcut "EE")
   (string-shortcut "AI")
   (string-shortcut "IA")
   (string-shortcut "OO")
   (string-shortcut "EI")
   (string-shortcut "UE")
   (string-shortcut "UA")
   (string-shortcut "AU")
   (string-shortcut "UI")
   (string-shortcut "OI")
   (string-shortcut "EO")
   (string-shortcut "OA")
   (string-shortcut "IOU")
   (string-shortcut "OE")
   (string-shortcut "EU")
   (string-shortcut "IU")
   (string-shortcut "II")
   (string-shortcut "AE")
   (string-shortcut "EAU")
   (string-shortcut "EOU")
   (string-shortcut "UO")
   (string-shortcut "III")
   (string-shortcut "UOU")
   (string-shortcut "AO")
   (string-shortcut "UEE")
   (string-shortcut "AA")
   (string-shortcut "UIE")
   (string-shortcut "OUI")
   (string-shortcut "EEI")
   (string-shortcut "IEU")
   (string-shortcut "UAI")
   (string-shortcut "AEO")
   (string-shortcut "UU")
   (string-shortcut "EEA")
   (string-shortcut "EOI")
   (string-shortcut "AUE")
   (string-shortcut "IAE")
   (string-shortcut "IOA")
   (string-shortcut "AIA")
   (string-shortcut "OEU")
   (string-shortcut "OUE")
   (string-shortcut "AII")
   (string-shortcut "IOE")
   (string-shortcut "UEOU")
   (string-shortcut "UEA")
   (string-shortcut "IEEE")
   (string-shortcut "UIA")
   (string-shortcut "IAI")
   (string-shortcut "EIA")
   (string-shortcut "EUE")
   (string-shortcut "UOI")
   (string-shortcut "AIE")
   (string-shortcut "OIE")
   (string-shortcut "UEUE")
   (string-shortcut "AEU")
   (string-shortcut "AEA")
   (string-shortcut "UAE")
   (string-shortcut "AIIA")
   (string-shortcut "IAO")
   (string-shortcut "IOI")
   (string-shortcut "OEA")
   (string-shortcut "OUA")
   (string-shortcut "AOI")
   (string-shortcut "EIE")
   (string-shortcut "OEI")
   (string-shortcut "OOO")
   (string-shortcut "EUI")
   (string-shortcut "EIO")
   (string-shortcut "AIU")
   (string-shortcut "OIA")
   (string-shortcut "AOU")
   (string-shortcut "OOE")
   (string-shortcut "EAE")
   (string-shortcut "AAA")
   (string-shortcut "OOI")
   (string-shortcut "UEU")
   (string-shortcut "OEO")
   (string-shortcut "EIU")
   (string-shortcut "OAU")
   (string-shortcut "EII")
   (string-shortcut "UIEU")
   (string-shortcut "UEI")
   (string-shortcut "UIU")
   (string-shortcut "IIA")
   (string-shortcut "EEO")
   (string-shortcut "IIE")
   (string-shortcut "EEU")
   (string-shortcut "IAU")
   (string-shortcut "EOA")
   (string-shortcut "IAA")
   (string-shortcut "AUI")
   (string-shortcut "OUIE")
   (string-shortcut "OAO")
   (string-shortcut "IOO")
   (string-shortcut "IAEA")
   (string-shortcut "AIO")
   (string-shortcut "UIOU")
   (string-shortcut "OAA")
   (string-shortcut "OEIA")
   (string-shortcut "UOIA")
   (string-shortcut "UEUI")
   (string-shortcut "IUE")
   (string-shortcut "UIO")
   (string-shortcut "UEUEI")
   (string-shortcut "IEI")
   (string-shortcut "AUU")
   (string-shortcut "OAI")
   (string-shortcut "IIIA")
   (string-shortcut "IEA")
   (string-shortcut "AIAA")
   (string-shortcut "AUAI")
   (string-shortcut "IIO")
   (string-shortcut "IIII")
   (string-shortcut "AEI")
   (string-shortcut "IEE")
   (string-shortcut "AAU")
   (string-shortcut "EUA")
   (string-shortcut "OUAI")
   (string-shortcut "AEE")
   (string-shortcut "OOOO")
   (string-shortcut "OEE")
   (string-shortcut "EEE")
   (string-shortcut "AUA")
   (string-shortcut "OOOOO")
   (string-shortcut "OEAE")
   (string-shortcut "IOOO")
   (string-shortcut "OIO")
   (string-shortcut "AUEA")
   (string-shortcut "IIIE")
   (string-shortcut "UOA")
   (string-shortcut "IIIII")
   (string-shortcut "AOA")
   (string-shortcut "IOIO")
   (string-shortcut "OOU")
   (string-shortcut "AAI")
   (string-shortcut "AAAA")
   (string-shortcut "EAI")
   (string-shortcut "OUAU")
   (string-shortcut "EAA")
   (string-shortcut "IEO")
   (string-shortcut "IOUA")
   (string-shortcut "AAE")
   (string-shortcut "AAAI")
   (string-shortcut "IOIA")
   (string-shortcut "UAU")
   (string-shortcut "EOE")
   (string-shortcut "EUO")
   (string-shortcut "AOE")])


(defn words []
  [(string-shortcut "BOOK")
   (string-shortcut "BUILDER")
   (string-shortcut "EXCEL")
   (named-string-shortcut "FALSE" "FALSE_STR")
   (string-shortcut "CLOJURE")
   (string-shortcut "FIGWHEEL")
   (string-shortcut "SERVICE")
   (named-string-shortcut "TRUE" "TRUE_STR")
   (string-shortcut "WORK")
   (string-shortcut "WORKBOOK")
   (string-shortcut "WORKSHEET")])

(defn clojure-commands []
  [["ATOM" (m/make-buffered-macro "ATOM" (m/make-code "(atom "))]
   ["APPLY" (m/make-buffered-macro "APPLY" (m/make-code "(apply "))]
   ["ASSOC" (m/make-buffered-macro "ASSOC" (m/make-code "(assoc "))]
   ["ASSOC_IN" (m/make-buffered-macro "ASSOC_IN" (m/make-code "(assoc-in "))]
   (named-string-shortcut "(comment " "COMMENT_STR")
   ["COND" (m/make-buffered-macro "COND" (m/make-code "cond"))]
   ["COUNT" (m/make-buffered-macro "COUNT" (m/make-code "count"))]
   ["DEF" (m/make-buffered-macro "DEF" (m/make-code "(def "))]
   ["DEFN" (m/make-buffered-macro "DEFN" (m/make-code "(defn "))]
   ["DEFMETHOD" (m/make-buffered-macro "DEFMETHOD" (m/make-code "(defmethod "))]
   ["DEFTEST" (m/make-buffered-macro "DEFTEST" (m/make-code "(deftest "))]
   ["DISSOC" (m/make-buffered-macro "DISSOC" (m/make-code "(dissoc "))]
   ["DISSOC_IN" (m/make-buffered-macro "DISSOC_IN" (m/make-code "(dissoc-in "))]
   ["DROP" (m/make-buffered-macro "DROP" (m/make-code "(drop "))]
   ["DROP_LAST" (m/make-buffered-macro "DROP_LAST" (m/make-code "(drop-last "))]
   ["FILTER" (m/make-buffered-macro "FILTER" (m/make-code "(filter "))]
   ["FIRST" (m/make-buffered-macro "FIRST" (m/make-code "first"))]
   ["KEYWORD" (m/make-buffered-macro "KEYWORD" (m/make-code "keyword"))]
   ["LAST" (m/make-buffered-macro "LAST" (m/make-code "last"))]
   ["MERGE" (m/make-buffered-macro "MERGE" (m/make-code "merge"))]
   ["NAME" (m/make-buffered-macro "NAME" (m/make-code "name"))]
   (named-string-shortcut "(println " "PRINTLN")
   ["REDUCE" (m/make-buffered-macro "REDUCE" (m/make-code "reduce"))]
   ["REQUIRE" (m/make-buffered-macro "REQUIRE" (m/make-code "require"))]
   ["SPEC_DEF" (m/make-buffered-macro "SPEC_DEF" (m/make-code "(s/def "))]
   ["SPEC_FDEF" (m/make-buffered-macro "SPEC_FDEF" (m/make-code "(s/fdef "))]
   ["TAKE" (m/make-buffered-macro "TAKE" (m/make-code "take"))]
   ["TRANSDUCE" (m/make-buffered-macro "TRANSDUCE" (m/make-code "transduce"))]
   ["UPDATE" (m/make-buffered-macro "UPDATE" (m/make-code "update"))]
   ["WHEN" (m/make-buffered-macro "WHEN" (m/make-code "when"))]
   ["ZIPMAP" (m/make-buffered-macro "ZIPMAP" (m/make-code "zipmap"))]])


(defn sql-commands []
  [(string-shortcut "ANY" true)
   (string-shortcut "AND" true)
   (string-shortcut "BETWEEN" true)
   (string-shortcut "CREATE" true)
   (string-shortcut "DISTINCT" true)
   (string-shortcut "FROM" true)
   (string-shortcut "INNER" true)
   ["GROUP_BY" (m/make-buffered-macro "GROUP_BY" (m/make-code "GROUP BY "))]
   (string-shortcut "HAVING" true)
   (string-shortcut "JOIN" true)
   (string-shortcut "LEFT" true)
   ["SQL_NULL" (m/make-buffered-macro "SQL_NULL" (m/make-code "NULL "))]
   (string-shortcut "OUTER" true)
   (string-shortcut "RIGHT" true)
   (string-shortcut "SELECT" true)
   (string-shortcut "UNION" true)
   (string-shortcut "VIEW" true)
   (string-shortcut "WHERE" true)])



(defn custom-cursive-commands []
  [["REPL_FILE" (m/make-buffered-macro "REPL_FILE" (a- (s- (k- "L"))))]
   ["REPL_SYNC" (m/make-buffered-macro "REPL_SYNC" (a- (s- (k- "M"))))]
   ["REPL_TOP" (m/make-buffered-macro "REPL_TOP" (a- (s- (k- "P"))))]
   ["REPL_NS" (m/make-buffered-macro "REPL_NS" (a- (s- (k- "R"))))]
   ["REPL_JUMP" (m/make-buffered-macro "REPL_JUMP" (c- (k- "BSLS")))]
   ["REPL_SRCH" (m/make-buffered-macro "REPL_SRCH" (c- (a- (k- "E"))))]
   ["REPL_MACRO" (m/make-buffered-macro "REPL_MACRO" (c- (a- (k- "BSLS"))))]
   ["REPL_CLEAR" (m/make-buffered-macro "REPL_CLEAR" (c- (k- "ESC")))]

   ["SEXP_FW" (m/make-buffered-macro "SEXP_FW" (c- (k- "RIGHT")))]
   ["SEXP_BW" (m/make-buffered-macro "SEXP_BW" (c- (k- "LEFT")))]

   ["SLURP_BW" (m/make-buffered-macro "SLURP_BW" (c- (a- (k- "J"))))]
   ["SLURP_FW" (m/make-buffered-macro "SLURP_FW" (a- (s- (k- "K"))))]
   ["BARF_BW" (m/make-buffered-macro "BARF_BW" (c- (a- (k- "K"))))]
   ["BARF_FW" (m/make-buffered-macro "BARF_FW" (a- (s- (k- "J"))))]

   ["SPLICE" (m/make-buffered-macro "SPLICE" (a- (k- "S")))]
   ["RAISE" (m/make-buffered-macro "RAISE" (a- (k- "R")))]
   ["KILL" (m/make-buffered-macro "KILL" (a- (k- "D")))]
   ["KILL_SEXP" (m/make-buffered-macro "KILL_SEXP" (c- (a- (k- "D"))))]
   ["COPY_KILL" (m/make-buffered-macro "COPY_KILL" (a- (s- (k- "D"))))]
   ["SPLIT" (m/make-buffered-macro "SPLIT" (a- (s- (k- "S"))))]
   ["SEXP_JOIN" (m/make-buffered-macro "SEXP_JOIN" (c- (s- (k- "S"))))]

   ["WRAP_ROUND" (m/make-buffered-macro "WRAP_ROUND" (c- (s- (k- "9"))))]
   ["WRAP_SQR" (m/make-buffered-macro "WRAP_SQR" (c- (k- "LBRC")))]
   ["WRAP_CURLY" (m/make-buffered-macro "WRAP_CURLY" (c- (s- (k- "LBRC"))))]
   ["WRAP_DQT" (m/make-buffered-macro "WRAP_DQT" (c- (s- (k- "QUOT"))))]

   ["THREAD" (m/make-buffered-macro "THREAD" (c- (a- (k- "COMM"))))]
   ["UNTHREAD" (m/make-buffered-macro "UNTHREAD" (c- (a- (k- "DOT"))))]

   ["FORM_UP" (m/make-buffered-macro "FORM_UP" (c- (s- (k- "UP"))))]
   ["FORM_DOWN" (m/make-buffered-macro "FORM_DOWN" (c- (s- (k- "DOWN"))))]

   ["EXTEND_SEL" (m/make-buffered-macro "EXTEND_SEL" (c- (k- "W")))]
   ["SHRINK_SEL" (m/make-buffered-macro "SHRINK_SEL" (c- (s- (k- "W"))))]
   ["FORMAT_CODE" (m/make-buffered-macro "FORMAT_CODE" (c- (a- (k- "L"))))]


   ["CUT" (m/make-buffered-macro "CUT" (c- (k- "X")))]
   ["COPY" (m/make-buffered-macro "COPY" (c- (k- "C")))]
   ["PASTE" (m/make-buffered-macro "PASTE" (c- (k- "V")))]
   ["UNDO" (m/make-buffered-macro "UNDO" (c- (k- "Z")))]

   ])

(defn cursive-repl-commands []
  [["IG_GO" (m/make-buffered-macro "IG_GO" (c- (a- (s- (k- "I" "G")))))]
   ["IG_HALT" (m/make-buffered-macro "IG_HALT" (c- (a- (s- (k- "I" "H")))))]
   ["IG_RESET" (m/make-buffered-macro "IG_HALT" (c- (a- (s- (k- "I" "R")))))]
   ["KAOCHA_RUN" (m/make-buffered-macro "KAOCHA_RUN" (c- (a- (s- (k- "R" "K")))))]
   ["CLJS_REPL" (m/make-buffered-macro "CLJS_REPL" (c- (a- (s- (k- "R" "C")))))]
   ["CLJS_QUIT" (m/make-buffered-macro "CLJS_QUIT" (c- (a- (s- (k- "R" "Q")))))]])

(defn other-shortcuts []
  [;; Keyboard Shortcuts
   ["TFIRSTB"
    (m/make-buffered-macro
      "TFIRSTB"
      (str "register_code(KC_LSFT);"
           "register_code(KC_COMM);"
           "unregister_code(KC_COMM);"
           "unregister_code(KC_LSFT);"
           "register_code(KC_MINS);"
           "unregister_code(KC_MINS);"))]
   ["TFIRSTF"
    (m/make-buffered-macro
      "TFIRSTF"
      (str "register_code(KC_MINS);"
           "unregister_code(KC_MINS);"
           "register_code(KC_LSFT);"
           "register_code(KC_DOT);"
           "unregister_code(KC_DOT);"
           "unregister_code(KC_LSFT);"))]
   ["TLASTB"
    (m/make-buffered-macro
      "TLASTB"
      (str "register_code(KC_LSFT);"
           "register_code(KC_COMM);"
           "unregister_code(KC_COMM);"
           "register_code(KC_COMM);"
           "unregister_code(KC_COMM);"
           "unregister_code(KC_LSFT);"
           "register_code(KC_MINS);"
           "unregister_code(KC_MINS);"))]
   ["TLASTF"
    (m/make-buffered-macro
      "TLASTF"
      (str "register_code(KC_MINS);"
           "unregister_code(KC_MINS);"
           "register_code(KC_LSFT);"
           "register_code(KC_DOT);"
           "unregister_code(KC_DOT);"
           "register_code(KC_DOT);"
           "unregister_code(KC_DOT);"
           "unregister_code(KC_LSFT);"))]
   ["SUBSCRIBE"
    (m/make-buffered-macro
      "SUBSCRIBE"
      (str "register_code(KC_LSFT);"
           "register_code(KC_COMM);"
           "unregister_code(KC_COMM);"
           "unregister_code(KC_LSFT);"
           "register_code(KC_EQL);"
           "unregister_code(KC_EQL);"
           "register_code(KC_EQL);"
           "unregister_code(KC_EQL);"))]
   ["DISPATCH"
    (m/make-buffered-macro
      "DISPATCH"
      (str "register_code(KC_EQL);"
           "unregister_code(KC_EQL);"
           "register_code(KC_EQL);"
           "unregister_code(KC_EQL);"
           "register_code(KC_LSFT);"
           "register_code(KC_DOT);"
           "unregister_code(KC_DOT);"
           "unregister_code(KC_LSFT);"))]

   ["CTRL_ENTER"
    (m/make-immediate-macro
      (str "register_code(KC_LCTL);"
           "register_code(KC_ENTER);"
           "unregister_code(KC_ENTER);"
           "unregister_code(KC_LCTL);"))]

   ["CTRL_TAB"
    (m/make-immediate-macro
      (str "register_code(KC_LCTL);"
           "register_code(KC_TAB);"
           "unregister_code(KC_TAB);"
           "unregister_code(KC_LCTL);"))]
   ["CMNT_FORM"
    (m/make-buffered-macro
      "CMNT_FORM"
      (str "register_code(KC_LSFT);"
           "register_code(KC_3);"
           "unregister_code(KC_3);"
           "register_code(KC_MINS);"
           "unregister_code(KC_MINS);"
           "unregister_code(KC_LSFT);"
           "register_code(KC_LEFT);"
           "unregister_code(KC_LEFT);"
           "register_code(KC_RIGHT);"
           "unregister_code(KC_RIGHT);"))]

   ["ANON_FN"
    (m/make-immediate-macro
      (str "register_code(KC_LSFT);"
           "register_code(KC_3);"
           "unregister_code(KC_3);"
           "register_code(KC_9);"
           "unregister_code(KC_9);"
           "unregister_code(KC_LSFT);"))]
   ["SET"
    (m/make-immediate-macro
      (str "register_code(KC_LSFT);"
           "register_code(KC_3);"
           "unregister_code(KC_3);"
           "register_code(KC_LBRC);"
           "unregister_code(KC_LBRC);"
           "unregister_code(KC_LSFT);"))]
   ["QUOT_S"
    (m/make-buffered-macro "QUOT_S" (m/make-code "'s"))]
   ])



(defn macros []
  (concat
    (vowel-combinations)
    (cursive-repl-commands)
    (custom-cursive-commands)
    (sql-commands)
    (clojure-commands)
    (words)
    (other-shortcuts)))