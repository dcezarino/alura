package br.com.alura.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.math.BigDecimal;
import java.util.Map;

public class FraudeDetectorService {

    public static void main(String[] args) {
        var fraudeService = new FraudeDetectorService();
        try (var service = new KafkaService<>(FraudeDetectorService.class.getSimpleName(),
                "ECOMMERCE_NEW_ORDER",
                fraudeService::parse,
                Order.class,
                Map.of())) {
            service.run();
        }
    }

    private void parse(ConsumerRecord<String, Order> record) {
        System.out.println("----------------------------------------");
        System.out.println("Processing new order, checking for fraud");
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.partition());
        System.out.println(record.offset());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // Ignoring
            e.printStackTrace();
        }
        System.out.println("Order processed");
    }

    public static class Order {

        private final String userId, orderId;
        private final BigDecimal amount;

        public Order(String userId, String orderId, BigDecimal amount) {
            this.userId = userId;
            this.orderId = orderId;
            this.amount = amount;
        }

    }
}
