package com.grammarpulse.grammar_pulse_api.service;

import com.grammarpulse.grammar_pulse_api.model.GrammarResponse;
import com.grammarpulse.grammar_pulse_api.model.GrammarRule;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class GrammarService {
    private static final String LANGUAGETOOL_URL = "https://api.languagetool.org/v2/check";

    public GrammarResponse checkGrammar(String text) {
        RestTemplate restTemplate = new RestTemplate();
        String requestUrl = LANGUAGETOOL_URL + "?language=en-US&text=" + text;

        String jsonResponse = restTemplate.getForObject(requestUrl, String.class);
        JSONObject responseObj = new JSONObject(jsonResponse);
        JSONArray matches = responseObj.getJSONArray("matches");

        List<GrammarRule> ruleList = new ArrayList<>();
        for (int i = 0; i < matches.length(); i++) {
            JSONObject match = matches.getJSONObject(i);
            String ruleDescription = match.getJSONObject("rule").getString("description");
            String suggestedReplacement = match.getJSONArray("replacements").length() > 0
                    ? match.getJSONArray("replacements").getJSONObject(0).getString("value")
                    : "No suggestion";

            ruleList.add(new GrammarRule(match.getString("message"), suggestedReplacement, ruleDescription));
        }

        return new GrammarResponse(text, ruleList);
    }
}
