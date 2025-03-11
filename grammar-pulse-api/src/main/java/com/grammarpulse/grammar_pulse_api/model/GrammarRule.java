package com.grammarpulse.grammar_pulse_api.model;

public class GrammarRule {
    private final String issue;
    private final String suggestedCorrection;
    private final String ruleExplanation;

    public GrammarRule(String issue, String suggestedCorrection, String ruleExplanation) {
        this.issue = issue;
        this.suggestedCorrection = suggestedCorrection;
        this.ruleExplanation = ruleExplanation;
    }

    public String getIssue() {
        return issue;
    }

    public String getSuggestedCorrection() {
        return suggestedCorrection;
    }

    public String getRuleExplanation() {
        return ruleExplanation;
    }
}
