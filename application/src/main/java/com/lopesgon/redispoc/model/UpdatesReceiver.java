package com.lopesgon.redispoc.model;

import static java.lang.String.format;

import org.springframework.data.redis.connection.Message;
import org.springframework.lang.Nullable;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class UpdatesReceiver implements MessageInterface {

    public static UpdatesReceiver of() {
        return new UpdatesReceiver();
    }

    @Override
    public void onMessage(Message message, @Nullable byte[] pattern) {
        log.trace(format("Received message: [%s]",message));
    }
    
    @Override
    public String getTopic() {
        return "updates";
    }
}
