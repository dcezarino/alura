package br.com.alura.ecommerce;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.Closeable;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaDispatcher  implements Closeable {

    private final KafkaProducer<String, String> producer;

    KafkaDispatcher(){
        this.producer = new KafkaProducer<>(properties());
    }

    /*
     * �Tanto a chave, quando o valor, os dois v�o transformar a mensagem e a chave, baseado em strings�.
     *  Ent�o, al�m do tipo, eu tenho que passar transformadores de strings para bytes, serializadores de strings para bytes
     */
    private static Properties properties() {
        var properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }

    void send(String topic, String key, String value) throws ExecutionException, InterruptedException {
        var record = new ProducerRecord<>(topic, key, value);
        /*
         * Essa mensagem vai ficar registrada no Kafka, por quanto tempo (producer.send())?
         * Depende da configura��o do seu server properties, �Ah, ser� que o espa�o em disco pode acabar?�
         * Pode, se deixar muito tempo, ent�o tamb�m tem um server properties, qual o espa�o m�ximo que vai armazenar as mensagens.
         */
        Callback callback = (data, ex) -> {
            if (ex != null) {
                ex.printStackTrace();
                return;
            }
            System.out.println("sucesso enviando " + data.topic() + "::: partition " + data.partition() + "/ offset " + data.offset() + "/ timestamp " + data.timestamp());
        };
        /*
         * Processando a compra, enviando uma mensagem, que � atrav�s do t�pico ECOMMERCE_NEW_ORDER
         * e uma atrav�s do t�pico ECOMMERCE_SEND_EMAIL.
         */
        producer.send(record, callback).get();
    }

    @Override
    public void close() {
        producer.close();
    }
}
