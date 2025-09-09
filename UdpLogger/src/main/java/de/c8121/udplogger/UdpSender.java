package de.c8121.udplogger;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdpSender {

    public final static int DEFAULT_MAX_MESSAGE_LENGTH = 512;

    private final InetAddress server;
    private final int port;
    private final DatagramSocket socket;

    private int maxMessageLength = DEFAULT_MAX_MESSAGE_LENGTH;
    private boolean replaceNewline = true;

    public UdpSender(InetAddress server, int port) throws SocketException {
        this.server = server;
        this.port = port;
        this.socket = new DatagramSocket();
    }

    public UdpSender setMaxMessageLength(int maxMessageLength) {
        this.maxMessageLength = maxMessageLength;
        return this;
    }

    public UdpSender replaceNewline(boolean replaceNewline) {
        this.replaceNewline = replaceNewline;
        return this;
    }

    public void send(final String text) {

        byte[] out = text.getBytes();

        if (replaceNewline) {
            for (int i = 0; i < out.length; i++) {
                if (out[i] == '\n' || out[i] == '\r')
                    out[i] = '-';
            }
        }

        DatagramPacket packet = new DatagramPacket(out, Math.min(out.length, maxMessageLength), this.server, this.port);

        try {
            this.socket.send(packet);
        } catch (Exception ex) {
            //Do not use Logger here.
            //Logger itself sends UDP messages.
            ex.printStackTrace();
        }
    }
}
