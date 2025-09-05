package de.c8121.udplogger;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdpSender {

    private final InetAddress server;
    private final int port;
    private final DatagramSocket socket;

    public UdpSender(InetAddress server, int port) throws SocketException {
        this.server = server;
        this.port = port;
        this.socket = new DatagramSocket();
    }

    public void send(final String text) {

        byte[] out = text.getBytes();
        DatagramPacket packet = new DatagramPacket(out, out.length, this.server, this.port);

        try {
            this.socket.send(packet);
        } catch (Exception ex) {
            //Do not use Logger here.
            //Logger itself sends UDP messages.
            ex.printStackTrace();
        }
    }
}
