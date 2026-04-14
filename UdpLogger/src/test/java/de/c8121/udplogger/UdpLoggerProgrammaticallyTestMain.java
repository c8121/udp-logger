package de.c8121.udplogger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.EventConstants;

class UdpLoggerProgrammaticallyTestMain {

    private final static Logger LOGGER = LoggerFactory.getLogger(UdpLoggerProgrammaticallyTestMain.class);

    public static void main(String[] args) {

        UdpLoggerConfiguration.setLogLevel(EventConstants.TRACE_INT);
        UdpLoggerConfiguration.createSender("zabbix.gefro.de", 9990);

        LOGGER.trace("trace");
        LOGGER.debug("debug");
        LOGGER.info("info");
        LOGGER.warn("warn");
        LOGGER.error("error");
    }
}