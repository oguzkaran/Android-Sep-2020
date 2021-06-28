package org.csystem.application.reversepalindrome.server;

import java.net.Socket;
import java.time.LocalDateTime;

public class ClientInfo {
    private Socket m_socket;
    private LocalDateTime m_lastUpdate;

    //...

    public ClientInfo(Socket socket, LocalDateTime lastUpdate)
    {
        m_socket = socket;
        m_lastUpdate = lastUpdate;
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
}
