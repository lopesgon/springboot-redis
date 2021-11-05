package com.lopesgon.redispoc.model;

import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.Topic;

public interface MessageInterface extends MessageListener, Topic {

}
