package ru.datana.camel.demo.config;

import org.apache.activemq.pool.PooledConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.component.activemq.ActiveMQComponent;
import org.apache.camel.component.jms.JmsConfiguration;
import org.springframework.context.annotation.Bean;

public class DatanaCamelConfig {

    @Bean
    protected ActiveMQComponent datanaJms() {
        ActiveMQComponent result = new ActiveMQComponent();
        result.setConfiguration(camelActiveMQConfig());
        return result;
    }

    private JmsConfiguration camelActiveMQConfig() {
        JmsConfiguration configuration = new JmsConfiguration();
        configuration.setConnectionFactory(pooledConnectionFactory());
        configuration.setTransacted(true);
        configuration.setConcurrentConsumers(15);
        configuration.setDeliveryPersistent(true);
        configuration.setRequestTimeout(10000);
        configuration.setCacheLevelName("CACHE_CONSUMER");
        return configuration;
    }

    private PooledConnectionFactory pooledConnectionFactory() {
        PooledConnectionFactory factory = new PooledConnectionFactory();
        factory.setMaxConnections(10);
        factory.setMaximumActiveSessionPerConnection(10);
        factory.setConnectionFactory(singleFactory());
        return factory;
    }

    private ActiveMQConnectionFactory singleFactory() {
        ActiveMQConnectionFactory f = new ActiveMQConnectionFactory();
        f.setBrokerURL("tcp://172.29.40.42:61616");
        return f;
    }
}
