package com.server.demo;

import com.server.demo.controller.MessageController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

import javax.websocket.Session;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class ServerHandler extends BinaryWebSocketHandler{

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    MessageController messageController;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info(String.format("Connection was opened with server : %s! ", session.getId()));
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
//        logger.info("Message from client: " + message.getPayload());
//        CharBuffer charBuffer = StandardCharsets.UTF_8.decode((ByteBuffer) message.getPayload());
//        logger.info(charBuffer.toString());
//        messageController.handleMessage(charBuffer.toString(), session);

        messageController.handleMessage((String) message.getPayload(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info(String.format("Connection was closed with server : %s! ", session.getId()));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        logger.info("Ooops, something wrong! " + exception.getMessage());
    }

}
