/*----------------------------------------------------------------------
FILE        : UdpUtil.java
AUTHOR      : Oğuz Karan
LAST UPDATE : 18.10.2020

UdpUtil class for UDP socket operations

Copyleft (c) 1993 by C and System Programmers Association (CSD)
All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.util.net;

import java.net.*;

public final class UdpUtil {
    private static DatagramPacket createDatagramPacket(byte [] data, String host, int port) throws UnknownHostException
    {
        return new DatagramPacket(data, 0, data.length, InetAddress.getByName(host), port);
    }

    private static DatagramPacket createDatagramPacket(int length)
    {
        return new DatagramPacket(new byte[length], length);
    }

    private UdpUtil()
    {
    }

    public static void sendInt(String host, int port, int val)
    {
        try (DatagramSocket datagramSocket = new DatagramSocket()) {
            byte [] data = BitConverter.getBytes(val);

            datagramSocket.send(createDatagramPacket(data, host, port));
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.sendInt", ex);
        }
    }

    public static int receiveInt(int port)
    {
        try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
            DatagramPacket datagramPacket = createDatagramPacket(4);

            datagramSocket.receive(datagramPacket);
            int length = datagramPacket.getLength();
            if (length != 4)
                throw new NetworkException("Invalid data length");

            return BitConverter.toInt(datagramPacket.getData());
        }
        catch (NetworkException ex) {
            throw ex;
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.receiveInt", ex);
        }
    }

    public static void sendDouble(String host, int port, double val)
    {
        try (DatagramSocket datagramSocket = new DatagramSocket()) {
            byte [] data = BitConverter.getBytes(val);

            datagramSocket.send(createDatagramPacket(data, host, port));
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.sendDouble", ex);
        }
    }

    public static double receiveDouble(int port)
    {
        try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
            DatagramPacket datagramPacket = createDatagramPacket(8);

            datagramSocket.receive(datagramPacket);
            int length = datagramPacket.getLength();
            if (length != 8)
                throw new NetworkException("Invalid data length");

            return BitConverter.toDouble(datagramPacket.getData());
        }
        catch (NetworkException ex) {
            throw ex;
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.receiveDouble", ex);
        }
    }

    public static void sendString(String host, int port, String str)
    {
        try (DatagramSocket datagramSocket = new DatagramSocket()) {
            byte [] data = BitConverter.getBytes(str);

            datagramSocket.send(createDatagramPacket(data, host, port));
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.sendString", ex);
        }
    }

    public static String receiveString(int port, int maxLength)
    {
        try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
            DatagramPacket datagramPacket = createDatagramPacket(maxLength);

            datagramSocket.receive(datagramPacket);

            return BitConverter.toString(datagramPacket.getData());
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.receiveString", ex);
        }
    }
}
