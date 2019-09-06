(ns typey.qmk.core
  (:require [clojure.string :as str]
            [clojure.java.shell :refer [sh with-sh-dir]]
            [typey.qmk.defs :as defs]
            [typey.qmk.keymaps.keymap-hybrid :as keymap-hybrid]
            [typey.qmk.layers :as layers]
            [typey.qmk.process-record-user :refer [process-record-user]]
            [typey.qmk.shortcut-keys :as shortcuts]
            [typey.qmk.visualizer :as vis]))


(defn buffered-keymap-c [layer-strings symbols macros]
  (str/join "\n"
            [(defs/includes)
             (defs/defines)
             (defs/make-enums symbols macros)
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
          (keymap-hybrid/key-defs) (shortcuts/shifted-symbols) (shortcuts/macros))))


(defn make-linux-strings []
  [(str "sudo make " defs/keyboard-layout)
   (str "sudo make " defs/keyboard-layout " MASTER=right")])


(defn make-left-firmware []
  (with-sh-dir defs/qmk-firmware-path
               (sh "make"
                   defs/keyboard-layout)))


(defn write-firmware [] (with-sh-dir defs/qmk-firmware-path
                                     (sh "dfu-util"
                                         "-D"
                                         defs/bin-file
                                         "-S"
                                         defs/serial-number)))


(defn mac-make-and-write-left []
  (write-buffered-layout)
  (make-left-firmware)
  (write-firmware))

(comment (mac-make-and-write-left))

(comment (make-linux-strings))

(write-buffered-layout)