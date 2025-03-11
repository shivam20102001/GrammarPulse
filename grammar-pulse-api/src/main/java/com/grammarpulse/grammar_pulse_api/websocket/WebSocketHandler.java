package com.grammarpulse.grammar_pulse_api.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grammarpulse.grammar_pulse_api.model.GrammarResponse;
import com.grammarpulse.grammar_pulse_api.service.GrammarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private GrammarService grammarService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String userText = message.getPayload();
        GrammarResponse response = grammarService.checkGrammar(userText);
        session.sendMessage(new TextMessage(objectMapper.writeValueAsString(response)));
    }
}
