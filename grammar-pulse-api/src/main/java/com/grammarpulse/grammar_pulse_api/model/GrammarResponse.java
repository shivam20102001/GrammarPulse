package com.grammarpulse.grammar_pulse_api.model;

import java.util.List;

public class GrammarResponse {
    private final String originalText;
    private final List<GrammarRule> corrections;

    public GrammarResponse(String originalText, List<GrammarRule> corrections) {
        this.originalText = originalText;
        this.corrections = corrections;
    }

    public String getOriginalText() {
        return originalText;
    }

    public List<GrammarRule> getCorrections() {
        return corrections;
    }
}
