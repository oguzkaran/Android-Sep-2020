package org.csystem.android.application;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import static java.lang.Thread.setDefaultUncaughtExceptionHandler;

public final class ApplicationUtil {
    private static <T extends Context> void uncaughtExceptionProc(Thread thread,
                                                                    Throwable throwable,
                                                                    Context context,
                                                                    Class<T> cls,
                                                                    long ms,
                                                                    IAction action)
    {
        if (action != null)
            action.run();

        Intent intent = new Intent(context, cls);

        intent.putExtra("crash", true);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(context
                , 0, intent, PendingIntent.FLAG_ONE_SHOT);

        AlarmManager alarmManager  = (AlarmManager)context
                .getSystemService(Context.ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + ms, pendingIntent);

        System.exit(2);
    }

    private static void enableDisableLauncher(Context context, Class<?> cls, boolean enable)
    {
        PackageManager packageManager = context.getPackageManager();
        ComponentName componentName = new ComponentName(context, cls);

        packageManager.setComponentEnabledSetting(componentName,
                enable ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED : PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }

    private ApplicationUtil() {}

    public static void disableLauncher(Context context, Class<?> cls)
    {
        enableDisableLauncher(context, cls, false);
    }

    public static void enableLauncher(Context context, Class<?> cls)
    {
        enableDisableLauncher(context, cls, true);
    }


    public static <T extends Context> void setUncaughtExceptionHandler(
            Context context, Class<T> cls, long ms, IAction action)
    {
        setDefaultUncaughtExceptionHandler(
                (thread, throwable) -> uncaughtExceptionProc(thread, throwable, context, cls, ms, action));
    }

    public static <T extends Context> void setUncaughtExceptionHandler(
            Context context, Class<T> cls, long ms)
    {
        setUncaughtExceptionHandler(context, cls, ms, null);
    }

    public static <T extends Context> void setUncaughtExceptionHandler(
            Context context, Class<T> cls)
    {
        setUncaughtExceptionHandler(context, cls, null);
    }

    public static <T extends Context> void setUncaughtExceptionHandler(
            Context context, Class<T> cls, IAction action)
    {
        setUncaughtExceptionHandler(context, cls, 0, action);
    }
}
