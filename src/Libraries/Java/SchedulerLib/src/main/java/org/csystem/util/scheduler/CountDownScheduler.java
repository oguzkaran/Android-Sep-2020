/*----------------------------------------------------------------------------------------------------------------------
    CountDownScheduler sınıfı
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.util.scheduler;

import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public abstract class CountDownScheduler {
    private final Scheduler m_scheduler;
    private final long m_millisInFuture;
    private final long m_interval;
    private final TimerTask m_timerTask;

    private TimerTask createTimerTask()
    {
        return new TimerTask() {
            private long m_value;

            public void run()
            {
                long millisUntilFinished = m_millisInFuture - m_value;

                onTick(millisUntilFinished);
                m_value += m_interval;
                if (m_value < m_millisInFuture)
                    return;

                onFinish();
                m_scheduler.cancel();
            }
        };
    }

    protected CountDownScheduler(long millisInFuture, long interval)
    {
        this(millisInFuture, interval, TimeUnit.MILLISECONDS);
    }

    protected CountDownScheduler(long millisFuture, long interval, TimeUnit timeUnit)
    {
        m_millisInFuture = timeUnit != TimeUnit.MILLISECONDS ? TimeUnit.MILLISECONDS.convert(millisFuture, timeUnit) : millisFuture;
        m_interval = timeUnit != TimeUnit.MILLISECONDS ? TimeUnit.MILLISECONDS.convert(interval, timeUnit) : interval;
        m_scheduler = new Scheduler(m_interval);
        m_timerTask = createTimerTask();
    }

    public abstract void onTick(long millisUntilFinished);

    public abstract void onFinish();

    public final void start()
    {
        m_scheduler.schedule(m_timerTask);
    }

    public final void cancel()
    {
        m_scheduler.cancel();
    }
}
