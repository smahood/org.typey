(ns typey.qmk.core
  (:require [clojure.string :as str]
            [clojure.java.shell :refer [sh with-sh-dir]]
            [typey.qmk.bigraphs :refer [bigraphs]]
            [typey.qmk.defs :as defs]
            [typey.qmk.keymaps.keymap-hybrid :as keymap-hybrid]
            [typey.qmk.layers :as layers]
            [typey.qmk.process-record-user :refer [process-record-user]]
            [typey.qmk.shortcut-keys :as shortcuts]
            [typey.qmk.visualizer :as vis]))


(defn buffered-keymap-c [layer-strings symbols bigraphs macros]
  (str/join "\n"
            [(defs/includes)
             (defs/defines)
             (defs/make-enums symbols bigraphs macros)
             (defs/vars)
             (defs/buffered-functions)
             (layers/wrap-keymaps
               (str/join
                 "\n"
                 (into []
                       (map #(layers/wrap-layout (key %) (val %)))
                       layer-strings)))
             (process-record-user)
             (defs/matrix-init)
             (defs/matrix-scan)]))


(defn write-buffered-layout []
  (spit (str/join "/" [defs/keymap-output-path "simple_visualizer.h"])
        (vis/simple_visualizer_h))
  (spit (str/join "/" [defs/keymap-output-path "visualizer.c"])
        (vis/simple_visualizer_c))
  (spit (str/join "/" [defs/keymap-output-path "keymap.c"])
        (buffered-keymap-c
          (keymap-hybrid/key-defs) (shortcuts/shifted-symbols)
          (bigraphs) (shortcuts/macros))))


#_(defn make-linux-strings []
    [(str "sudo make " defs/keyboard-layout)
     (str "sudo make " defs/keyboard-layout " MASTER=right")])





(def keyboard "ergodox_infinity")
(def layout "shaun")
(def qmk-firmware-path "../qmk_firmware")
(def keymap-output-path
  (str/join "/" [qmk-firmware-path "keyboards" keyboard "keymaps" layout]))
(def keyboard-layout (str keyboard ":" layout))
(def serial-number "mk20dx256vlh7")
(def bin-file (str/replace (str keyboard "_" layout ".bin") #"-" "_"))



(defn make-left-firmware []
  (with-sh-dir qmk-firmware-path
               (sh "make"
                   keyboard-layout)))

(defn write-firmware [] (with-sh-dir qmk-firmware-path
                                     (sh "dfu-util"
                                         "-D"
                                         bin-file
                                         "-S"
                                         serial-number)))

(defn mac-make-and-write-left []
  (write-buffered-layout)
  (make-left-firmware)
  (write-firmware))

(comment (mac-make-and-write-left))


(write-buffered-layout)