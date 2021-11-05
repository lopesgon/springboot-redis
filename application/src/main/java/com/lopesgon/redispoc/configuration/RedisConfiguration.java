package com.lopesgon.redispoc.configuration;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.lopesgon.redispoc.model.ChatReceiver;
import com.lopesgon.redispoc.model.MessageInterface;
import com.lopesgon.redispoc.model.UpdatesReceiver;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.util.ErrorHandler;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfiguration {

	private String host;
	private Integer port;
	private int database;
	private String username;
	private String password;
	private int timeout;

	@Bean
	RedisStandaloneConfiguration redisStandaloneConfiguration() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(host, port);
		redisStandaloneConfiguration.setDatabase(database);

		redisStandaloneConfiguration.setUsername(username);
		redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
		return redisStandaloneConfiguration;
	}

	/**
	 * Lettuce as Redis Client allows for thread-safe sync, async, and reactive usage. Supports Cluster, Sentinel, Pipelining, and codecs.
	 * 
	 * @param redisStandaloneConfiguration
	 * @return LettuceConnectionFactory.class
	 */
	@Bean
	LettuceConnectionFactory lettuceConnectionFactory(RedisStandaloneConfiguration redisStandaloneConfiguration) {
		LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration);
		// lettuceConnectionFactory.afterPropertiesSet();
		// lettuceConnectionFactory.getConnection();
		return lettuceConnectionFactory;
	}

	/*
	 * @Bean JedisConnectionFactory
	 * jedisConnectionFactory(RedisStandaloneConfiguration
	 * redisStandaloneConfiguration) {
	 * JedisClientConfiguration.JedisClientConfigurationBuilder
	 * jedisClientConfiguration = JedisClientConfiguration.builder();
	 * jedisClientConfiguration.connectTimeout(Duration.ofMillis(timeout));
	 * 
	 * return new JedisConnectionFactory(redisStandaloneConfiguration,
	 * jedisClientConfiguration.build()); }
	 */

	@Bean
	public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
			ErrorHandler redisErrorHandler, Map<? extends MessageListener, Collection<? extends Topic>> redisListeners,
			MessageInterface updatesReceiverListener,
			MessageListenerAdapter listenerAdapter, Topic chatTopic) {

		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setErrorHandler(redisErrorHandler);
		// container.setMessageListeners(redisListeners);
		// container.addMessageListener((MessageListener) updatesReceiverListener, (Topic) updatesReceiverListener);
		container.addMessageListener(listenerAdapter, new PatternTopic("chat"));
		log.trace("Bean creation RedisMessageListenerContainer successful.");
		return container;
	}

	@Bean
	ErrorHandler redisErrorHandler() {
		return new ErrorHandler() {
			@Override
			public void handleError(Throwable t) {
				log.error("RedisErrorHandler throw: ", t);
			}
		};
	}

	@Bean
	MessageListenerAdapter listenerAdapter() {
		return new MessageListenerAdapter(UpdatesReceiver.of());
	}

	@Bean
	MessageInterface updatesReceiverListener() {
		return UpdatesReceiver.of();
	}

	@Bean
	MessageInterface chatReceiverListener() {
		return ChatReceiver.of();
	}

	@Bean
	Map<? extends MessageListener, Collection<? extends Topic>> redisListeners() {
		Map<? extends MessageListener, Collection<? extends Topic>> listeners = new HashMap<>();
		// listeners.put(UpdatesReceiver.of(),
		// Arrays.asList(UpdatesReceiver.of().getTopic()));
		return listeners;
	}

	@Bean("chatTopic")
	Topic chatTopic() {
		return new ChannelTopic("chat");
	}

}
