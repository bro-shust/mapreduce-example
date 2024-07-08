package com.broshust.mapreduce.mapreduceexample.workflow.config;

import jakarta.jms.ConnectionFactory;
import jakarta.jms.Session;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@EnableJms
@Configuration
@Import(ActiveMQAutoConfiguration.class)
public class ActiveMqConfig {

    @Bean
    public JmsTemplate jsonTemplateQueue(ConnectionFactory jmsConnectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(jmsConnectionFactory);
        jmsTemplate.setMessageConverter(messageConverter());
        return jmsTemplate;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jsonJmsListenerContainerFactory(ConnectionFactory jmsConnectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(jmsConnectionFactory);
        factory.setMessageConverter(messageConverter());
        factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
        return factory;
    }

    @Bean
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
}