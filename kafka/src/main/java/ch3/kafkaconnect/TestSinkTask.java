package ch3.kafkaconnect;

import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;

import java.util.Collection;
import java.util.Map;

public class TestSinkTask extends SinkTask {
    @Override
    public String version() {
        return "1.0";
    }

    @Override
    public void start(Map<String, String> props) {

    }

    @Override
    public void put(Collection<SinkRecord> records) {

    }

    @Override
    public void stop() {

    }
}
