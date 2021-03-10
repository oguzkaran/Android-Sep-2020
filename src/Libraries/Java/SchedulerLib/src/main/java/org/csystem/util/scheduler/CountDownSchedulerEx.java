/*----------------------------------------------------------------------------------------------------------------------
    CountDownSchedulerEx sınıfı
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.util.scheduler;

import java.util.concurrent.TimeUnit;

public abstract class CountDownSchedulerEx extends CountDownScheduler {
    protected CountDownSchedulerEx(long millisInFuture, long interval)
    {
        this(millisInFuture, interval, TimeUnit.MILLISECONDS);
    }

    protected CountDownSchedulerEx(long millisFuture, long interval, TimeUnit timeUnit)
    {
        super(millisFuture, interval, timeUnit);
    }

    public void startScheduler()
    {
        onStart();
        start();
    }

    public abstract void onStart();
}
