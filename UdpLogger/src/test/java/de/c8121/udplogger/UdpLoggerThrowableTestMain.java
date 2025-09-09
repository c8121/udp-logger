package de.c8121.udplogger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.EventConstants;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

class UdpLoggerThrowableTestMain {

    private final static Logger LOGGER = LoggerFactory.getLogger(UdpLoggerTestMain.class);

    public static void main(String[] args) throws UnknownHostException, SocketException {

        UdpLoggerConfiguration.setLogLevel(EventConstants.TRACE_INT);

        var sender = new UdpSender(InetAddress.getByName("zabbix.gefro.de"), 9990)
                .setMaxMessageLength(1400)
                .replaceNewline(true);

        UdpLoggerConfiguration.setSender(sender);

        LOGGER.error("Test Exception", new Exception("Test"));
    }
}