package ru.datana.camel.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.ConnectionFactory;

/**
 * Для теста: ActiveMQ коннект на базе бинов
 */
@Configuration
@EnableJms
@Slf4j
public class DatanaActiveMqSpringConfig {


    @Autowired
    protected JmsProperties jmsProperties;

    @Bean
    protected ConnectionFactory activeMqJMSConnectionFactory() {
        return new ActiveMQConnectionFactory(jmsProperties.getBrokerUrl());
    }

}
