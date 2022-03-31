package me.dyatkokg.balancecachingapi;

import me.dyatkokg.balancecachingapi.queue.QueueProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@SpringBootApplication
@EnableBinding(QueueProcessor.class)
@EnableScheduling
public class BalanceCachingApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BalanceCachingApiApplication.class, args);
    }

}
