import connection.ClientBuilder;
import lombok.extern.log4j.Log4j2;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import utils.enums.SensorEnum;

import java.util.Date;

@Log4j2
public class Main {
    private final static String URL = "tcp://url";
    private final static String USERNAME = "user";
    private final static String PASSWORD = "pass";

    public static void main(String[] args) {
        IMqttClient iMqttClient = ClientBuilder.newBuilder(URL)
                                               .username(USERNAME)
                                               .password(PASSWORD)
                                               .build();
        reader(iMqttClient);
    }


    private static void reader(final IMqttClient iMqttClient) {
        for (SensorEnum sensor : SensorEnum.values()) {
            log.info("##CONNECT SENSOR -> " + sensor.name);
            try {
                iMqttClient.subscribe(sensor.address, sensor.qos, (topic, message) -> {
                    log.info("##Address: " + topic);
                    log.info("##SensorName: " + sensor.name);
                    log.info("##EventDate: " + new Date());
                    log.info("##Message: " + message);
                    log.info("------------------------------------------------------------");
                });
            } catch (MqttException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
