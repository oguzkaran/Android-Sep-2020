/*----------------------------------------------------------------------------------------------------------------------
    ScheduleUtil sınıfı
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.util.scheduler;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public final class ScheduleUtil {
    private ScheduleUtil()
    {}

    public static void schedule(Runnable runnable, int delay, long period, TimeUnit timeUnit)
    {
        long delayInMs = TimeUnit.MILLISECONDS.convert(delay, timeUnit);
        long periodInMs = TimeUnit.MILLISECONDS.convert(period, timeUnit);

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            public void run()
            {
                runnable.run();
            }
        }, delayInMs, periodInMs);
    }
}
