package com.grammarpulse.grammar_pulse_api;

import com.grammarpulse.grammar_pulse_api.ui.GlobalKeyListener;
import com.grammarpulse.grammar_pulse_api.ui.GrammarPulseTray;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrammarPulseApiApplication {

	public static void main(String[] args) {

		// Start system tray
		GrammarPulseTray.initialize();

		// Register keyboard shortcut
		GlobalKeyListener.register();

		SpringApplication.run(GrammarPulseApiApplication.class, args);
	}

}
