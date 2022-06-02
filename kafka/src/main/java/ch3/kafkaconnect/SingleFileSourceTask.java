package ch3.kafkaconnect;

import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SingleFileSourceTask extends SourceTask {
    private Logger logger = LoggerFactory.getLogger(SingleFileSourceTask.class);
    private String topic;
    private String file;
    private Map<String, String> fileNamePartition;
    private Map<String, Object> offset;
    private String FILENAME_FIELD = "filename";
    private String POSITION_FIELD = "position";
    private long position = -1;

    @Override
    public String version() {
        return null;
    }

    @Override
    public void start(Map<String, String> props) {
        try {
            SingleFileSourceConnectorConfig config = new SingleFileSourceConnectorConfig(props);
            topic = config.getString(SingleFileSourceConnectorConfig.TOPIC_NAME);
            file = config.getString(SingleFileSourceConnectorConfig.DIR_FILE_NAME);
            fileNamePartition = Collections.singletonMap(FILENAME_FIELD, file);
            offset = context.offsetStorageReader().offset(fileNamePartition);

            if(offset != null) {
                Object lastReadFileOffset = offset.get(POSITION_FIELD);
                if(lastReadFileOffset != null) {
                    position = (Long) lastReadFileOffset;
                }
            } else {
                position = 0;
            }
        } catch (Exception e) {

        }
    }

    @Override
    public List<SourceRecord> poll() {
        List<SourceRecord> results = new ArrayList<>();
        try {
            Thread.sleep(1000);
            List<String> lines = getLines(position);

            if(lines.size() > 0) {
                lines.forEach(line -> {
                    Map<String, Long> sourceOffset = Collections.singletonMap(POSITION_FIELD, ++position);
                    SourceRecord sourceRecord =
                            new SourceRecord(fileNamePartition, sourceOffset, topic, Schema.STRING_SCHEMA, line);
                    results.add(sourceRecord);
                });
            }
            return results;
        } catch (Exception e) {

        }
        return null;
    }

    private List<String> getLines(long readLine) throws IOException {
        BufferedReader reader = Files.newBufferedReader(Paths.get(file));
        return reader.lines().skip(readLine).collect(Collectors.toList());
    }

    @Override
    public void stop() {

    }
}
