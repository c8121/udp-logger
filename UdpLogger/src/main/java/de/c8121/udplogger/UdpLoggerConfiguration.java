package de.c8121.udplogger;

import org.slf4j.spi.LocationAwareLogger;

import java.net.InetAddress;
import java.util.Objects;

public class UdpLoggerConfiguration {

    static int currentLogLevel = LocationAwareLogger.INFO_INT;

    public static void setLogLevel(int level) {
        currentLogLevel = level;
    }

    public static UdpSender udpSender;

    public static void setSender(UdpSender sender) {
        udpSender = Objects.requireNonNull(sender);
    }

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
