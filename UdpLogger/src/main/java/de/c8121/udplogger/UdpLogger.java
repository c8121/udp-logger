package de.c8121.udplogger;

import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.event.EventConstants;
import org.slf4j.helpers.MessageFormatter;

public class UdpLogger implements Logger {

    private final String name;


    public UdpLogger(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * Send log via UPD.
     * Additionally call {@link #consoleWrite(int, String)}
     */
    protected void write(int level, String message, Object... args) {

        boolean consoleEnabled = isLevelEnabled(level);
        boolean udpEnabled = isUdpLevelEnabled(level);
        if (!consoleEnabled && !udpEnabled)
            return;

        var text = "[" + levelName(level) + "] " +
                name + ": " +
                MessageFormatter.arrayFormat(message, args).getMessage();

        if (udpEnabled && UdpLoggerConfiguration.udpSender != null) {
            UdpLoggerConfiguration.udpSender.send(text);
        }

        if (consoleEnabled) {
            consoleWrite(level, text);
        }
    }

    /**
     * Additionally write log to console.
     */
    protected void consoleWrite(int level, String text) {
        var out = level > EventConstants.INFO_INT
                ? System.err
                : System.out;

        out.println(text);
    }


    /**
     * Convert level int to name
     */
    protected static String levelName(int level) {
        return switch (level) {
            case EventConstants.TRACE_INT -> "TRACE";
            case EventConstants.DEBUG_INT -> "DEBUG";
            case EventConstants.INFO_INT -> "INFO";
            case EventConstants.WARN_INT -> "WARN";
            case EventConstants.ERROR_INT -> "ERROR";
            default -> "UNKNOWN(" + level + ")";
        };
    }


    public boolean isLevelEnabled(int level) {
        return level >= UdpLoggerConfiguration.currentLogLevel;
    }

    public boolean isUdpLevelEnabled(int level) {
        return level >= UdpLoggerConfiguration.currentUdpLogLevel;
    }

    @Override
    public boolean isTraceEnabled() {
        return isLevelEnabled(EventConstants.TRACE_INT) || isUdpLevelEnabled(EventConstants.TRACE_INT);
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return isTraceEnabled();
    }

    @Override
    public void trace(String s) {
        write(EventConstants.TRACE_INT, s);
    }

    @Override
    public void trace(String s, Object o) {
        write(EventConstants.TRACE_INT, s, o);
    }

    @Override
    public void trace(String s, Object o, Object o1) {
        write(EventConstants.TRACE_INT, s, o, o1);
    }

    @Override
    public void trace(String s, Object... objects) {
        write(EventConstants.TRACE_INT, s, objects);
    }

    @Override
    public void trace(String s, Throwable throwable) {
        if (throwable == null)
            write(EventConstants.TRACE_INT, s);
        else
            write(EventConstants.TRACE_INT, s, throwable);
    }

    @Override
    public void trace(Marker marker, String s) {
        trace(s);
    }

    @Override
    public void trace(Marker marker, String s, Object o) {
        trace(s, o);
    }

    @Override
    public void trace(Marker marker, String s, Object o, Object o1) {
        trace(s, o, o1);
    }

    @Override
    public void trace(Marker marker, String s, Object... objects) {
        trace(s, objects);
    }

    @Override
    public void trace(Marker marker, String s, Throwable throwable) {
        trace(s, throwable);
    }

    @Override
    public boolean isDebugEnabled() {
        return isLevelEnabled(EventConstants.DEBUG_INT) || isUdpLevelEnabled(EventConstants.DEBUG_INT);
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return isDebugEnabled();
    }

    @Override
    public void debug(String s) {
        write(EventConstants.DEBUG_INT, s);
    }

    @Override
    public void debug(String s, Object o) {
        write(EventConstants.DEBUG_INT, s, o);
    }

    @Override
    public void debug(String s, Object o, Object o1) {
        write(EventConstants.DEBUG_INT, s, o, o1);
    }

    @Override
    public void debug(String s, Object... objects) {
        write(EventConstants.DEBUG_INT, s, objects);
    }

    @Override
    public void debug(String s, Throwable throwable) {
        write(EventConstants.DEBUG_INT, s, throwable);
    }

    @Override
    public void debug(Marker marker, String s) {
        debug(s);
    }

    @Override
    public void debug(Marker marker, String s, Object o) {
        debug(s, o);
    }

    @Override
    public void debug(Marker marker, String s, Object o, Object o1) {
        debug(s, o, o1);
    }

    @Override
    public void debug(Marker marker, String s, Object... objects) {
        debug(s, objects);
    }

    @Override
    public void debug(Marker marker, String s, Throwable throwable) {
        debug(s, throwable);
    }

    @Override
    public boolean isInfoEnabled() {
        return isLevelEnabled(EventConstants.INFO_INT) || isUdpLevelEnabled(EventConstants.INFO_INT);
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        return isInfoEnabled();
    }

    @Override
    public void info(String s) {
        write(EventConstants.INFO_INT, s);
    }

    @Override
    public void info(String s, Object o) {
        write(EventConstants.INFO_INT, s, o);
    }

    @Override
    public void info(String s, Object o, Object o1) {
        write(EventConstants.INFO_INT, s, o, o1);
    }

    @Override
    public void info(String s, Object... objects) {
        write(EventConstants.INFO_INT, s, objects);
    }

    @Override
    public void info(String s, Throwable throwable) {
        write(EventConstants.INFO_INT, s, throwable);
    }

    @Override
    public void info(Marker marker, String s) {
        info(s);
    }

    @Override
    public void info(Marker marker, String s, Object o) {
        info(s, o);
    }

    @Override
    public void info(Marker marker, String s, Object o, Object o1) {
        info(s, o, o1);
    }

    @Override
    public void info(Marker marker, String s, Object... objects) {
        info(s, objects);
    }

    @Override
    public void info(Marker marker, String s, Throwable throwable) {
        info(s, throwable);
    }

    @Override
    public boolean isWarnEnabled() {
        return isLevelEnabled(EventConstants.WARN_INT) || isUdpLevelEnabled(EventConstants.WARN_INT);
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return isWarnEnabled();
    }

    @Override
    public void warn(String s) {
        write(EventConstants.WARN_INT, s);
    }

    @Override
    public void warn(String s, Object o) {
        write(EventConstants.WARN_INT, s, o);
    }

    @Override
    public void warn(String s, Object... objects) {
        write(EventConstants.WARN_INT, s, objects);
    }

    @Override
    public void warn(String s, Object o, Object o1) {
        write(EventConstants.WARN_INT, s, o, o1);
    }

    @Override
    public void warn(String s, Throwable throwable) {
        write(EventConstants.WARN_INT, s, throwable);
    }

    @Override
    public void warn(Marker marker, String s) {
        warn(s);
    }

    @Override
    public void warn(Marker marker, String s, Object o) {
        warn(s, o);
    }

    @Override
    public void warn(Marker marker, String s, Object o, Object o1) {
        warn(s, o, o1);
    }

    @Override
    public void warn(Marker marker, String s, Object... objects) {
        warn(s, objects);
    }

    @Override
    public void warn(Marker marker, String s, Throwable throwable) {
        warn(s, throwable);
    }

    @Override
    public boolean isErrorEnabled() {
        return isLevelEnabled(EventConstants.ERROR_INT) || isUdpLevelEnabled(EventConstants.ERROR_INT);
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return isErrorEnabled();
    }

    @Override
    public void error(String s) {
        write(EventConstants.ERROR_INT, s);
    }

    @Override
    public void error(String s, Object o) {
        write(EventConstants.ERROR_INT, s, o);
    }

    @Override
    public void error(String s, Object o, Object o1) {
        write(EventConstants.ERROR_INT, s, o, o1);
    }

    @Override
    public void error(String s, Object... objects) {
        write(EventConstants.WARN_INT, s, objects);
    }

    @Override
    public void error(String s, Throwable throwable) {
        write(EventConstants.ERROR_INT, s, throwable);
    }

    @Override
    public void error(Marker marker, String s) {
        error(s);
    }

    @Override
    public void error(Marker marker, String s, Object o) {
        error(s, o);
    }

    @Override
    public void error(Marker marker, String s, Object o, Object o1) {
        error(s, o, o1);
    }

    @Override
    public void error(Marker marker, String s, Object... objects) {
        error(s, objects);
    }

    @Override
    public void error(Marker marker, String s, Throwable throwable) {
        error(s, throwable);
    }
}
