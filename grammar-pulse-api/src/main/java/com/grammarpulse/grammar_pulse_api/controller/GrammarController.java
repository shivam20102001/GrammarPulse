package com.grammarpulse.grammar_pulse_api.controller;

import com.grammarpulse.grammar_pulse_api.model.GrammarRequest;
import com.grammarpulse.grammar_pulse_api.model.GrammarResponse;
import com.grammarpulse.grammar_pulse_api.service.GrammarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/grammar")
public class GrammarController {

    @Autowired
    private GrammarService grammarService;

    @PostMapping("/check")
    public GrammarResponse checkGrammar(@RequestBody GrammarRequest request) {
        return grammarService.checkGrammar(request.getText());
    }
}
