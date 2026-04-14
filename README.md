# udp-logger
SLF4J Logger sending messages via UDP


## Usage

### Dependencies

*settings.gradle:*
```gradle
include path/to/udplogger
```


*build.gradle:*
```gradle
implementation project(":udplogger")
implementation 'org.slf4j:slf4j-api:2.0.17'
```

### Configuration File (Properties)

If the configuration file `resources/updlogger.properties` with the following properties is present, a UdpLogger will be created.

```
de.c8121.udplogger.udpServer=receiving.server.name
de.c8121.udplogger.udpPort=9990
```


### Programmatically

If there is no configuration file, an UdpLogger can be created programmatically:

```java
public class ExampleLogger {
    private final static Logger LOGGER = LoggerFactory.getLogger(ExampleLogger.class);
    
    public static void main(String[] args) {
        
        UdpLoggerConfiguration.createSender(UDP_LOGGER_SERVER, UDP_LOGGER_PORT);
        
        LOGGER.error("Hello from {}", ExampleLogger.class);
    }
}
```

