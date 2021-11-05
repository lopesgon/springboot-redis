package com.lopesgon.redispoc.model;

import org.springframework.data.redis.connection.Message;
import org.springframework.lang.Nullable;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class ChatReceiver implements MessageInterface {

    public static ChatReceiver of() {
        return new ChatReceiver();
    }

    @Override
    public void onMessage(Message message, @Nullable byte[] pattern) {
        log.trace("Received message [%s]", message);
    }

    @Override
    public String getTopic() {
        return "chat";
    }    
}
