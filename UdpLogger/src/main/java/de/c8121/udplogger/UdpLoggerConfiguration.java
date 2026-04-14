package de.c8121.udplogger;

import org.slf4j.spi.LocationAwareLogger;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Objects;
import java.util.Properties;

public class UdpLoggerConfiguration {

    public static final String CONFIGURATION_FILE = "udplogger.properties";

    private static final Properties PROPERTIES = new Properties();

    public static final String PROPERTY_NAME_PREFIX = "de.c8121.udplogger.";

    public static final String PROPERTY_NAME_UDP_SERVER = PROPERTY_NAME_PREFIX + "udpServer";
    public static final String PROPERTY_NAME_UDP_PORT = PROPERTY_NAME_PREFIX + "udpPort";

    /**
     * Load properties & create {@link UdpSender} if required properties found
     */
    static void init() {
        loadProperties();

        var server = getProperty(PROPERTY_NAME_UDP_SERVER);
        var port = getProperty(PROPERTY_NAME_UDP_PORT);

        if (server != null && !server.isBlank() && port != null && !port.isBlank()) {
            createSender(server, Integer.parseInt(port));
        }
    }

    /**
     * Get property: First from System.getProperty and second from loaded properties
     */
    private static String getProperty(String name) {
        String value = null;
        try {
            value = System.getProperty(name);
        } catch (Exception e) {
            //Ignored
        }
        if (value != null)
            return value;
        return PROPERTIES.getProperty(name);
    }

    /**
     * Load properties from #CONFIGURATION_FILE, if exists
     */
    private static void loadProperties() {
        var threadClassLoader = Thread.currentThread().getContextClassLoader();
        try (var in = threadClassLoader != null
                ? threadClassLoader.getResourceAsStream(CONFIGURATION_FILE)
                : ClassLoader.getSystemResourceAsStream(CONFIGURATION_FILE)) {

            if (in != null) {
                PROPERTIES.load(in);
            }

        } catch (IOException e) {
            //Ignored
            e.printStackTrace();
        }
    }

    static int currentLogLevel = LocationAwareLogger.INFO_INT;

    /**
     * Set log level for console logging
     */
    public static void setLogLevel(int level) {
        currentLogLevel = level;
    }

    static int currentUdpLogLevel = LocationAwareLogger.WARN_INT;

    /**
     * Set log level for UDP logging
     */
    public static void setUdpLogLevel(int level) {
        currentUdpLogLevel = level;
    }

    public static UdpSender udpSender;

    /**
     * Set {@link UdpSender} to be used (only one at a time is possible)
     */
    public static void setSender(UdpSender sender) {
        udpSender = Objects.requireNonNull(sender);
    }

    /**
     * Create & set a new {@link UdpSender}
     */
    public static void createSender(String server, int port) {
        Objects.requireNonNull(server, "server must not be null");
        try {
            var sender = new UdpSender(InetAddress.getByName(server), port);
            setSender(sender);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
