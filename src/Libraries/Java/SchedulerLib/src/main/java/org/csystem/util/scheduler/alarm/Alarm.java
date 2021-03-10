/*----------------------------------------------------------------------------------------------------------------------
    Alarm sınıfı
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.util.scheduler.alarm;

import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class Alarm {
    private final Timer m_timer;
    private final LocalTime m_time;

    public Alarm(int hour, int minute)
    {
        this(hour, minute, 0);
    }

    public Alarm(int hour, int minute, int second)
    {
        //...
        m_time = LocalTime.of(hour, minute, second);
        m_timer = new Timer();
    }
    public void run(Runnable alarmTask)
    {
        run(null, alarmTask);
    }

    public void run(Runnable periodRunnable, Runnable alarmRunnable)
    {
        m_timer.scheduleAtFixedRate(new TimerTask() {
            public void run()
            {
                if (periodRunnable != null)
                    periodRunnable.run();

                LocalTime now = LocalTime.now();

                if (m_time.compareTo(now) <= 0) {
                    alarmRunnable.run();
                    m_timer.cancel();
                }
            }
        }, 0, 1000);
    }
}
