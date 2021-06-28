/*----------------------------------------------------------------------
FILE        : UdpUtil.java
AUTHOR      : Oğuz Karan
LAST UPDATE : 18.06.2021

Utility class for UDP socket operations

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

    public static void sendByte(String host, int port, byte val)
    {
        try (DatagramSocket datagramSocket = new DatagramSocket()) {
            byte [] data = BitConverter.getBytes(val);

            datagramSocket.send(createDatagramPacket(data, host, port));
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.sendByte", ex);
        }
    }

    public static void sendShort(String host, int port, short val)
    {
        try (DatagramSocket datagramSocket = new DatagramSocket()) {
            byte [] data = BitConverter.getBytes(val);

            datagramSocket.send(createDatagramPacket(data, host, port));
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.sendShort", ex);
        }
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

    public static void sendLong(String host, int port, long val)
    {
        try (DatagramSocket datagramSocket = new DatagramSocket()) {
            byte [] data = BitConverter.getBytes(val);

            datagramSocket.send(createDatagramPacket(data, host, port));
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.sendLong", ex);
        }
    }

    public static void sendFloat(String host, int port, float val)
    {
        try (DatagramSocket datagramSocket = new DatagramSocket()) {
            byte [] data = BitConverter.getBytes(val);

            datagramSocket.send(createDatagramPacket(data, host, port));
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.sendFloat", ex);
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

    public static void sendChar(String host, int port, char ch)
    {
        try (DatagramSocket datagramSocket = new DatagramSocket()) {
            byte [] data = BitConverter.getBytes(ch);

            datagramSocket.send(createDatagramPacket(data, host, port));
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.sendChar", ex);
        }
    }

    public static void sendBoolean(String host, int port, boolean val)
    {
        try (DatagramSocket datagramSocket = new DatagramSocket()) {
            byte [] data = BitConverter.getBytes(val);

            datagramSocket.send(createDatagramPacket(data, host, port));
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.sendBoolean", ex);
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

    public static byte receiveByte(int port)
    {
        try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
            DatagramPacket datagramPacket = createDatagramPacket(Byte.BYTES);

            datagramSocket.receive(datagramPacket);
            int length = datagramPacket.getLength();
            if (length != Byte.BYTES)
                throw new NetworkException("Invalid data length");

            return datagramPacket.getData()[0];
        }
        catch (NetworkException ex) {
            throw ex;
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.receiveByte", ex);
        }
    }

    public static short receiveShort(int port)
    {
        try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
            DatagramPacket datagramPacket = createDatagramPacket(Short.BYTES);

            datagramSocket.receive(datagramPacket);
            int length = datagramPacket.getLength();
            if (length != Short.BYTES)
                throw new NetworkException("Invalid data length");

            return BitConverter.toShort(datagramPacket.getData());
        }
        catch (NetworkException ex) {
            throw ex;
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.receiveShort", ex);
        }
    }

    public static int receiveInt(int port)
    {
        try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
            DatagramPacket datagramPacket = createDatagramPacket(Integer.BYTES);

            datagramSocket.receive(datagramPacket);
            int length = datagramPacket.getLength();
            if (length != Integer.BYTES)
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

    public static float receiveFloat(int port)
    {
        try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
            DatagramPacket datagramPacket = createDatagramPacket(Float.BYTES);

            datagramSocket.receive(datagramPacket);
            int length = datagramPacket.getLength();
            if (length != Float.BYTES)
                throw new NetworkException("Invalid data length");

            return BitConverter.toFloat(datagramPacket.getData());
        }
        catch (NetworkException ex) {
            throw ex;
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.receiveFloat", ex);
        }
    }

    public static double receiveDouble(int port)
    {
        try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
            DatagramPacket datagramPacket = createDatagramPacket(Double.BYTES);

            datagramSocket.receive(datagramPacket);
            int length = datagramPacket.getLength();
            if (length != Double.BYTES)
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

    public static char receiveChar(int port)
    {
        try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
            DatagramPacket datagramPacket = createDatagramPacket(Character.BYTES);

            datagramSocket.receive(datagramPacket);
            int length = datagramPacket.getLength();
            if (length != Character.BYTES)
                throw new NetworkException("Invalid data length");

            return BitConverter.toChar(datagramPacket.getData());
        }
        catch (NetworkException ex) {
            throw ex;
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.receiveDouble", ex);
        }
    }

    public static boolean receiveBoolean(int port)
    {
        try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
            DatagramPacket datagramPacket = createDatagramPacket(1);

            datagramSocket.receive(datagramPacket);
            int length = datagramPacket.getLength();
            if (length != 1)
                throw new NetworkException("Invalid data length");

            return BitConverter.toBoolean(datagramPacket.getData());
        }
        catch (NetworkException ex) {
            throw ex;
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.receiveBoolean", ex);
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