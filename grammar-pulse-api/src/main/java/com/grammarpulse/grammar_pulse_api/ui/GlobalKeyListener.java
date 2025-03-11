package com.grammarpulse.grammar_pulse_api.ui;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import java.awt.*;

public class GlobalKeyListener implements NativeKeyListener {

    public static void register() {
        try {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(new GlobalKeyListener());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        // If user presses "Ctrl + Shift + G", trigger grammar check
        if (e.getKeyCode() == NativeKeyEvent.VC_G &&
                NativeKeyEvent.getModifiersText(e.getModifiers()).contains("Ctrl") &&
                NativeKeyEvent.getModifiersText(e.getModifiers()).contains("Shift")) {
            showGrammarCheckPopup();
        }
    }

    private void showGrammarCheckPopup() {
        SystemTray tray = SystemTray.getSystemTray();
        TrayIcon[] icons = tray.getTrayIcons();
        if (icons.length > 0) {
            icons[0].displayMessage("Grammar Check", "Checking grammar for selected text...", TrayIcon.MessageType.INFO);
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
    }
}
