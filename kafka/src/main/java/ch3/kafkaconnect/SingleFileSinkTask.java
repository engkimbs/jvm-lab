package ch3.kafkaconnect;

import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;

import java.io.File;
import java.io.FileWriter;
import java.io.IOError;
import java.io.IOException;
import java.net.ConnectException;
import java.util.Collection;
import java.util.Map;

public class SingleFileSinkTask extends SinkTask {
    private SingleFileSinkConnectorConfig config;
    private File file;
    private FileWriter fileWriter;

    @Override
    public String version() {
        return "1.0";
    }

    @Override
    public void start(Map<String, String> props) {
        try {
            config = new SingleFileSinkConnectorConfig(props);
            file = new File(config.getString(config.DIR_FILE_NAME));
            fileWriter = new FileWriter(file, true);
        } catch (Exception e) {

        }
    }

    @Override
    public void put(Collection<SinkRecord> records) {
        try {
            for(SinkRecord record : records) {
                fileWriter.write(record.value().toString() + "\n");
            }
        }catch (Exception e) {

        }
    }

    @Override
    public void flush(Map<TopicPartition, OffsetAndMetadata> currentOffsets) {
        try {
            fileWriter.flush();
        } catch (IOException e) {

        }
    }

    @Override
    public void stop() {
        try {
            fileWriter.close();
        } catch (Exception e) {

        }
    }
}
