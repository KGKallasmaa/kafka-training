package kafka.advanced.exercise5.exercise5c;

import kafka.advanced.exercise5.exercise5a.model.TemperatureKey;
import kafka.advanced.exercise5.exercise5a.model.Temperature;
import kafka.advanced.exercise5.exercise5a.serialization.TemperatureKeySerializer;
import kafka.advanced.exercise5.exercise5a.serialization.TemperatureValueSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Random;


public class Exe6TemperatureProducer {
    public void createProducer() {

        Random random = new Random(1);

        Properties props = new Properties();
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, TemperatureKeySerializer.class.getName());
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, TemperatureValueSerializer.class.getName());
        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, RoomPartitioner.class.getName());

        // TODO: Create a KafkaProducer

        try (KafkaProducer<TemperatureKey, Temperature> termometer = new KafkaProducer<>(props)) {

            while (true) {

                TemperatureKey key = new TemperatureKey("room" + random.nextInt(5));

                int temperature = random.nextInt(40);

                if (random.nextBoolean())
                    temperature = -1 * temperature;

                Temperature value = new Temperature(temperature, System.currentTimeMillis());

                ProducerRecord<TemperatureKey, Temperature> record =
                        new ProducerRecord<>("temperature", key, value);

                termometer.send(record);

                Thread.sleep(10001);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Exe6TemperatureProducer helloProducer = new Exe6TemperatureProducer();
        helloProducer.createProducer();
    }
}
