package de.c8121.udplogger;

import org.slf4j.spi.LocationAwareLogger;

import java.net.InetAddress;
import java.util.Objects;

public class UdpLoggerConfiguration {

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
