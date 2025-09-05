package de.c8121.udplogger;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class UdpLoggerFactory implements ILoggerFactory {

    private final ConcurrentMap<String, Logger> logger = new ConcurrentHashMap<>();

    @Override
    public Logger getLogger(String name) {
        return logger.computeIfAbsent(name, this::createLogger);
    }

    protected Logger createLogger(String name) {
        return new UdpLogger(name);
    }
}
