package com.simulator.oilstation.sender;

import com.simulator.oilstation.model.Frame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Collection;

@Slf4j
public class KafkaFrameSenderImpl implements FrameSender {
    private KafkaTemplate<String, Collection<Frame>> kafkaTemplate;
    private final String TOPIC = "frames";

    public KafkaFrameSenderImpl(KafkaTemplate<String, Collection<Frame>> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(Collection<Frame> frames) {
        ListenableFuture<SendResult<String, Collection<Frame>>> future = kafkaTemplate.send(TOPIC, frames);

        future.addCallback(new ListenableFutureCallback<SendResult<String, Collection<Frame>>>() {

            @Override
            public void onSuccess(SendResult<String, Collection<Frame>> stringCollectionSendResult) {
                log.info("Sent message with offset=[" + stringCollectionSendResult.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Unable to send message due to : {}", ex.getMessage());
            }
        });
    }
}
