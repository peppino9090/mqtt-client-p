package utils.enums;

public enum SensorEnum {
    SENSOR_1("1","zigbee2mqtt/temperature_1","sensor1","TEMPERATURE",1);

    public String id;
    public String address;
    public String name;
    public String description;
    public Integer qos;

    SensorEnum(final String id,
               final String address,
               final String name,
               final String description,
               final Integer qos) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.description = description;
        this.qos = qos;
    }
}