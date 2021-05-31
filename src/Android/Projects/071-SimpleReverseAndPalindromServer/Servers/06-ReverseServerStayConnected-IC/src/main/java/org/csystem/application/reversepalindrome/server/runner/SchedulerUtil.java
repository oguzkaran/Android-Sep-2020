package org.csystem.application.reversepalindrome.server.runner;

import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

public final class SchedulerUtil {
    private SchedulerUtil()
    {
    }

    public static boolean isRemovable(Socket key, Map<Socket, ClientInfo> clients, int threshold, String unitStr)
    {
        var now = LocalDateTime.now();
        var ci = clients.get(key);

        var  status = ChronoUnit.valueOf(unitStr).between(ci.getLastUpdate(), now) > threshold;

        try {
            if (status)
                ci.getSocket().close();
        }
        catch (IOException ignore) {

        }

        return status;
    }
}
