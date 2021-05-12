package org.csystem.application.reversepalindrome.server.runner;

import java.net.Socket;
import java.time.LocalDateTime;

public class ClientInfo {
    private Socket m_socket;
    private LocalDateTime m_lastUpdate = LocalDateTime.now();
    private final int m_transmissionPort;

    //...

    public ClientInfo(int transmissionPort)
    {
        m_transmissionPort = transmissionPort;
    }

    public ClientInfo(Socket socket, int transmissionPort)
    {
        m_socket = socket;
        m_transmissionPort = transmissionPort;
    }

    public Socket getSocket()
    {
        return m_socket;
    }

    public void setSocket(Socket socket)
    {
        m_socket = socket;
    }

    public LocalDateTime getLastUpdate()
    {
        return m_lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate)
    {
        m_lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object obj)
    {
        return ((ClientInfo)obj).m_transmissionPort == m_transmissionPort;
    }
}
