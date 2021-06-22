package com.jdd.springcloud.streamproducer;

import com.jdd.springcloud.streamproducer.model.Player;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Supplier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class StreamProducerConfiguration {

  @Bean
  @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
  public Supplier<Player> supplier() {
    return () -> {
      return playerBlockingQueue().poll();
    };
  }

  @Bean
  @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
  public BlockingQueue<Player> playerBlockingQueue() {
    return new LinkedBlockingQueue<>();
  }

}
