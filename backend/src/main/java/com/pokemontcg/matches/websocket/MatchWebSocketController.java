package com.pokemontcg.matches.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MatchWebSocketController {

    @MessageMapping("/matches/{matchId}/actions")
    @SendTo("/topic/matches/{matchId}/events")
    public String handleMatchAction(String message) {
        return message;
    }
}