package com.simulator.oilstation.service.frame.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.simulator.oilstation.service.frame.FrameSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oil.station.domain.frame.Frame;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaFrameSenderImpl implements FrameSender {

    private final KafkaTemplate<String, Collection<Frame>> kafkaTemplate;

    @Value("${topic.name}")
    private String topic;

    @Override
    public void send(final Collection<Frame> frames) {
        final ListenableFuture<SendResult<String, Collection<Frame>>> future = this.kafkaTemplate
            .send(this.topic, frames);

        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(SendResult<String, Collection<Frame>> result) {
                log.info("Sent message with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Unable to send message due to : {}", ex.getMessage());
            }
        });
    }
}
