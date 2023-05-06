package connection;

import lombok.extern.log4j.Log4j2;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.UUID;
@Log4j2
public class ClientBuilder {
    private String url;
    private String username;
    private String password;
    private Integer maxReconnectDelay;

    private ClientBuilder(final String url) {
        this.url = url;
    }
    public static ClientBuilder newBuilder(final String url) {
        return new ClientBuilder(url);
    }

    public ClientBuilder username (final String username) {
        this.username = username;
        return this;
    }

    public ClientBuilder password (final String password) {
        this.password = password;
        return this;
    }

    public ClientBuilder maxReconnectDelay (final Integer maxReconnectDelay) {
        this.maxReconnectDelay = maxReconnectDelay;
        return this;
    }

    public IMqttClient build() {
        try {
            final IMqttClient iMqttClient =  new org.eclipse.paho.client.mqttv3.MqttClient(url, getPublisherId());
            final MqttConnectOptions options = createOption();
            iMqttClient.connect(options);

            if(iMqttClient.isConnected()) {
                log.info("Connected..");
            } else {
                log.error("Unable to connect");
                System.exit(0);
            }

            return iMqttClient;
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

    private MqttConnectOptions createOption() {
        final MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(15);
        options.setKeepAliveInterval(30);
        if (maxReconnectDelay != null) {
            options.setMaxReconnectDelay(maxReconnectDelay);
        }
        if (username != null) {
            options.setUserName(username);
        }
        if (password != null) {
            options.setPassword(password.toCharArray());
        }
        return options;
    }

    private static String getPublisherId() {
        return UUID.randomUUID().toString();
    }

}
