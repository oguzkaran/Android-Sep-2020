package org.csystem.application.reversepalindrome.server.runner;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

public final class SchedulerUtil {
    private SchedulerUtil()
    {
    }

    public static void schedulerFilterCallback(ClientInfo ci)
    {
        try {
            ci.getSocket().close();
        }
        catch (IOException ignore) {

        }
    }

    public static boolean isRemovable(int key, Map<Integer, ClientInfo> clients, int threshold, String unitStr)
    {
        var now = LocalDateTime.now();
        var ci = clients.get(key);

        var  status = ChronoUnit.valueOf(unitStr).between(ci.getLastUpdate(), now) > threshold;

        try {
            ci.getSocket().close();
        }
        catch (IOException ignore) {

        }

        return status;
    }

}
