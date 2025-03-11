package com.grammarpulse.grammar_pulse_api.ui;

import dorkbox.systemTray.MenuItem;
import dorkbox.systemTray.SystemTray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GrammarPulseTray {

    public static void initialize() {
        SystemTray tray = SystemTray.get();
        tray.setImage("icon.png"); // Set your icon

        // Create menu items
        MenuItem checkGrammarItem = new MenuItem("Check Grammar");
        checkGrammarItem.setCallback(e -> checkGrammar());

        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setCallback(e -> System.exit(0));

        // Add menu items
        tray.getMenu().add(checkGrammarItem);
        tray.getMenu().add(exitItem);
    }

    private static void checkGrammar() {
        try {
            // Step 1: Get selected text from the clipboard
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            String selectedText = (String) clipboard.getData(DataFlavor.stringFlavor);

            if (selectedText == null || selectedText.trim().isEmpty()) {
                showNotification("GrammarPulse", "No text selected to check.");
                return;
            }

            // Step 2: Send text to backend API
            String response = sendToGrammarAPI(selectedText);

            // Step 3: Show grammar correction notification
            if (response != null) {
                showNotification("GrammarPulse - Correction", response);
            } else {
                showNotification("GrammarPulse", "No issues found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            showNotification("GrammarPulse - Error", "Failed to check grammar.");
        }
    }

    private static String sendToGrammarAPI(String text) {
        try {
            URL url = new URL("http://localhost:8080/api/grammar/check");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Create JSON request
            JSONObject json = new JSONObject();
            json.put("text", text);

            // Send request
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = json.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Read response
            if (connection.getResponseCode() == 200) {
                return new String(connection.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void showNotification(String title, String message) {
        SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE));
    }
}
