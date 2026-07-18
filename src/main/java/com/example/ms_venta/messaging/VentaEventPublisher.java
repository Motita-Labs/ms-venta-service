package com.example.ms_venta.messaging;

import com.example.ms_venta.dto.VentaResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Component
@ConditionalOnProperty(name = "aws.sqs.enabled", havingValue = "true")
public class VentaEventPublisher {

    private final SqsClient sqsClient;
    private final String queueUrl;

    public VentaEventPublisher(SqsClient sqsClient,
                               @Value("${aws.sqs.queue-url}") String queueUrl) {
        this.sqsClient = sqsClient;
        this.queueUrl = queueUrl;
    }

    public void publishVentaCreada(VentaResponseDTO venta) {
        String body = String.format(
                "{\"evento\":\"VENTA_CREADA\",\"id\":%d,\"fechaArriendo\":\"%s\"}",
                venta.getId(), venta.getFechaArriendo());
        sqsClient.sendMessage(SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(body)
                .build());
    }
}
