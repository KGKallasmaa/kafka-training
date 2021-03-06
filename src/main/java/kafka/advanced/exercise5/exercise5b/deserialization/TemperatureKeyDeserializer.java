package kafka.advanced.exercise5.exercise5b.deserialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import kafka.advanced.exercise5.exercise5a.model.TemperatureKey;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class TemperatureKeyDeserializer implements Deserializer<TemperatureKey> {
    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public TemperatureKey deserialize(String topic, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        TemperatureKey user = null;
        try {
            user = mapper.readValue(data, TemperatureKey.class);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void close() {

    }
}
