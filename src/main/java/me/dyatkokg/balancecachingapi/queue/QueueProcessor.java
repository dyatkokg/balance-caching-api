package me.dyatkokg.balancecachingapi.queue;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface QueueProcessor {

    String BALANCE_OUTPUT = "balance-output";

    @Output(BALANCE_OUTPUT)
    MessageChannel balance();

}
