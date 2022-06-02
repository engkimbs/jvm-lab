package ch3.kafkaconnect;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Type;
import org.apache.kafka.common.config.ConfigDef.Importance;

import java.util.Map;

public class SingleFileSourceConnectorConfig extends AbstractConfig {

    public static final String DIR_FILE_NAME = "file";
    public static final String DIR_FILE_NAME_DEFAULT_VALUE = "/tmp/kafka.txt";
    public static final String DIR_FILE_NAME_DOC = "";

    public static final String TOPIC_NAME = "topic";
    public static final String TOPIC_DEFAULT_VALUE = "test";
    public static final String TOPIC_DOC = "";

    public static ConfigDef CONFIG = new ConfigDef()
            .define(DIR_FILE_NAME, Type.STRING, DIR_FILE_NAME_DEFAULT_VALUE, Importance.HIGH, DIR_FILE_NAME_DOC)
            .define(TOPIC_NAME, Type.STRING, TOPIC_DEFAULT_VALUE, Importance.HIGH, TOPIC_DOC);

    public SingleFileSourceConnectorConfig(Map<String, String> props) {
        super(CONFIG, props);
    }
}
