package com.jdd.springcloud.streamproducer;

import com.jdd.springcloud.streamproducer.model.Player;
import com.jdd.springcloud.streamproducer.model.TestPlayer;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Supplier;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class StreamProducerConfiguration {

  @Bean
  @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
  public Supplier<TestPlayer> supplier() {
    return () -> {
      return playerBlockingQueue().poll();
    };
  }

  @Bean
  @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
  public BlockingQueue<TestPlayer> playerBlockingQueue() {
    return new LinkedBlockingQueue<>();
  }
}
