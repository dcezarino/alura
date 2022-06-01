package br.com.alura.ecommerce;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var producer = new KafkaProducer<String, String>(properties());
        var value = "111111, 222222, 12345";
        var record = new ProducerRecord<>("ECOMMERCE_NEW_ORDER", value, value );

        /**
         * Essa mensagem vai ficar registrada no Kafka, por quanto tempo (producer.send())?
         * Depende da configuração do seu server properties, “Ah, será que o espaço em disco pode acabar?”
         * Pode, se deixar muito tempo, então também tem um server properties, qual o espaço máximo que vai armazenar as mensagens.         *
         **/
        producer.send(record, (data, ex) -> {
            if (ex != null) {
                ex.printStackTrace();
                return;
            }
            System.out.println("sucesso enviando " + data.topic() + "::: partition " + data.partition() + "/ offset " + data.offset() + "/ timestamp " + data.timestamp());
        }).get();
    }

    /**
     * “Tanto a chave, quando o valor, os dois vão transformar a mensagem e a chave, baseado em strings”.
     *  Então, além do tipo, eu tenho que passar transformadores de strings para bytes, serializadores de strings para bytes
     **/
    private static Properties properties() {
        var properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }

}
