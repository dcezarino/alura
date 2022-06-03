package br.com.alura.ecommerce;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
       try (var dispatcher = new KafkaDispatcher()) {
        for (int i = 0; i < 10; i++) {
            var key = UUID.randomUUID().toString();
            var value = key + ", 33333, 44444";
            dispatcher.send("ECOMMERCE_NEW_ORDER", key, value);

            /*
             * Então com esse callback, eu preciso de um e-mail record aqui também,
             * emailRecord, ProducerRecord e o tópico é e-ECOMMERCE_SEND_EMAIL,
             * aqui eu estou pedindo realmente para enviar um e-mail.
             */
            var email = "Thank you for your order! We are processing your order!";

            /*
             * Passando o valor do e-mail, tanto a chave, quanto o valor.
             */
            dispatcher.send("ECOMMERCE_NEW_ORDER", key, email);
        }
       }
    }
}
