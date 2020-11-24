package io.confluent.developer;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KGroupedStream;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

import io.confluent.demo.Anomaly;
import io.confluent.demo.Averagesw;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;

import static io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG;
import static java.lang.Integer.parseInt;
import static java.lang.Short.parseShort;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toMap;
import static org.apache.kafka.common.serialization.Serdes.Double;
import static org.apache.kafka.streams.StreamsConfig.APPLICATION_ID_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.REPLICATION_FACTOR_CONFIG;
import static org.apache.kafka.streams.kstream.Grouped.with;

public class WindowingAnomalyDetection {

  //region buildStreamsProperties
  protected Properties buildStreamsProperties(Properties envProps) {
    Properties config = new Properties();
    config.putAll(envProps);

    config.put(APPLICATION_ID_CONFIG, envProps.getProperty("application.id"));
    config.put(BOOTSTRAP_SERVERS_CONFIG, envProps.getProperty("bootstrap.servers"));
    config.put(DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
    config.put(DEFAULT_VALUE_SERDE_CLASS_CONFIG, Double().getClass());
    config.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, envProps.getProperty("schema.registry.url"));

    config.put(REPLICATION_FACTOR_CONFIG, envProps.getProperty("default.topic.replication.factor"));
    config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, envProps.getProperty("offset.reset.policy"));

    config.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 0);

    return config;
  }
  //endregion

  //region createTopics

  /**
   * Create topics using AdminClient API
   */
  private void createTopics(Properties envProps) {
    Map<String, Object> config = new HashMap<>();

    config.put("bootstrap.servers", envProps.getProperty("bootstrap.servers"));
    AdminClient client = AdminClient.create(config);

    List<NewTopic> topics = new ArrayList<>();

    topics.add(new NewTopic(
        envProps.getProperty("input.topic.name"),
        parseInt(envProps.getProperty("input.topic.partitions")),
        parseShort(envProps.getProperty("input.topic.replication.factor"))));

    topics.add(new NewTopic(
        envProps.getProperty("output.topic.name"),
        parseInt(envProps.getProperty("output.topic.partitions")),
        parseShort(envProps.getProperty("output.topic.replication.factor"))));

    client.createTopics(topics);
    client.close();

  }
  //endregion

  private void run(String confFile) throws Exception {

    Properties envProps = this.loadEnvProperties(confFile);
    Properties streamProps = this.buildStreamsProperties(envProps);
    Topology topology = this.buildTopology(new StreamsBuilder(), envProps);

    this.createTopics(envProps);

    final KafkaStreams streams = new KafkaStreams(topology, streamProps);
    final CountDownLatch latch = new CountDownLatch(1);

    // Attach shutdown handler to catch Control-C.
    Runtime.getRuntime().addShutdownHook(new Thread("streams-shutdown-hook") {
      @Override
      public void run() {
        streams.close(Duration.ofSeconds(5));
        latch.countDown();
      }
    });

    try {
      streams.cleanUp();
      streams.start();
      latch.await();
    } catch (Throwable e) {
      System.exit(1);
    }
    System.exit(0);
  }

  //region buildTopology
  private Topology buildTopology(StreamsBuilder bldr,
                                 Properties envProps) {

    final String avgTopicName = envProps.getProperty("input.topic.name");
    final String anomalyTopicName = envProps.getProperty("output.topic.name");
    final SpecificAvroSerde<Averagesw> averagesSerde = getAveragesSerde(envProps);

    final KStream<String,Averagesw> avgStream = bldr.stream(avgTopicName,Consumed.with(Serdes.String(), averagesSerde));

    final KStream<String,Averagesw> filteredStream = avgStream.filter((key, average) -> average.getAverage() >= 5.0);

    filteredStream.map((String key, Averagesw avg) -> new KeyValue<>(key, Anomaly.newBuilder()
                .setId(key)
                .setWindowEnd(avg.getWindowEnd())
                .setWindowStart(avg.getWindowStart())
                .setWindowHash(avg.getWindowHash())
                .build()))
            .to(anomalyTopicName, Produced.with(Serdes.String(),getAnomalySerde(envProps)));

    // finish the topology
    return bldr.build();
  }
  //endregion

  public static SpecificAvroSerde<Anomaly> getAnomalySerde(Properties envProps) {
    SpecificAvroSerde<Anomaly> idsSerde = new SpecificAvroSerde<>();

    final HashMap<String, String> serdeConfig = new HashMap<>();
    serdeConfig.put(SCHEMA_REGISTRY_URL_CONFIG,
            envProps.getProperty("schema.registry.url"));

    idsSerde.configure(serdeConfig, false);
    return idsSerde;

  }

  public static SpecificAvroSerde<Averagesw> getAveragesSerde(Properties envProps) {
    SpecificAvroSerde<Averagesw> averageAvroSerde = new SpecificAvroSerde<>();

    final HashMap<String, String> serdeConfig = new HashMap<>();
    serdeConfig.put(SCHEMA_REGISTRY_URL_CONFIG,
            envProps.getProperty("schema.registry.url"));

    averageAvroSerde.configure(serdeConfig, false);
    return averageAvroSerde;
  }

  protected static Map<String, String> getSerdeConfig(Properties config) {
    final HashMap<String, String> map = new HashMap<>();

    final String srUrlConfig = config.getProperty(SCHEMA_REGISTRY_URL_CONFIG);
    map.put(SCHEMA_REGISTRY_URL_CONFIG, ofNullable(srUrlConfig).orElse(""));
    return map;
  }

  private Properties loadEnvProperties(String fileName) throws IOException {
        Properties envProps = new Properties();
        FileInputStream input = new FileInputStream(fileName);
        envProps.load(input);
        input.close();

        return envProps;
    }


  public static void main(String[] args)throws Exception {
	if (args.length < 1) {
          throw new IllegalArgumentException("This program takes one argument: the path to an environment configuration file.");
        }
    new WindowingAnomalyDetection().run(args[0]);
  }
}
