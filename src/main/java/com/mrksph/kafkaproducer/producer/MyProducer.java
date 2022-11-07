package com.mrksph.kafkaproducer.producer;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@RequiredArgsConstructor
public class MyProducer {
    @Value(value = "${kafka.topicName}")
    private String topicName;

    private final KafkaTemplate<String, String> template;

    public void produceMessage(String msg) {
        ListenableFuture<SendResult<String, String>> future =
                template.send(topicName, msg);

        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent msg=[" + msg +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send msg=["
                        + msg + "] due to : " + ex.getMessage());
            }
        });
    }
}
