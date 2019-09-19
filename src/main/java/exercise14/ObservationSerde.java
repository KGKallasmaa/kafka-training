package exercise14;

import exercise7.deserialization.ObservationDeserializer;
import exercise7.model.Observation;
import exercise7.serialization.ObservationSerializer;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class ObservationSerde implements Serde<Observation> {

    ObservationSerializer serializer = new ObservationSerializer();
    ObservationDeserializer deserializer = new ObservationDeserializer();

    @Override
    public Serializer<Observation> serializer() {
        return (topic, data) -> serializer.serialize(topic, data);
    }

    @Override
    public Deserializer<Observation> deserializer() {
        return (topic, data) -> deserializer.deserialize(topic, data);
    }
}
