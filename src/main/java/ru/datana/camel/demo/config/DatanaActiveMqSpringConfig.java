//package ru.datana.camel.demo.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.activemq.ActiveMQConnectionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jms.annotation.EnableJms;
//import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
//import org.springframework.jms.core.JmsTemplate;
//
//import javax.jms.ConnectionFactory;
//
///**
// * Для теста: ActiveMQ коннект на базе бинов
// */
//@Configuration
//@EnableJms
//@Slf4j
//public class DatanaActiveMqSpringConfig {
//
//
//    @Autowired
//    protected JmsProperties jmsProperties;
//
//    @Bean
//    protected ConnectionFactory connectionFactory() {
//        return new ActiveMQConnectionFactory(jmsProperties.getBrokerUrl());
//    }
//
//    @Bean
//    public JmsTemplate jmsTemplate(){
//        JmsTemplate template = new JmsTemplate();
//        template.setConnectionFactory(connectionFactory());
//        return template;
//    }
//
//    @Bean
//    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
//        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory());
//        factory.setConcurrency("1-1");
//        return factory;
//    }
//
//}
