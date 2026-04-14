package de.c8121.udplogger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.EventConstants;

class UdpLoggerThrowableTestMain {

    private final static Logger LOGGER = LoggerFactory.getLogger(UdpLoggerThrowableTestMain.class);

    public static void main(String[] args) {

        UdpLoggerConfiguration.setLogLevel(EventConstants.TRACE_INT);

        LOGGER.error("Test Exception", new Exception("Test"));
    }
}