package com.scarit.configuration;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class ThreadPoolConfiguration {

    @Bean
    public ExecutorService executorService(){
        return new ThreadPoolExecutor
                (10, 20, 120, TimeUnit.SECONDS,
                        new ArrayBlockingQueue<>(100),
                        new BasicThreadFactory.Builder().namingPattern("scaritlog-%d").daemon(true).build(),
                        new ThreadPoolExecutor.AbortPolicy());
    }
}
