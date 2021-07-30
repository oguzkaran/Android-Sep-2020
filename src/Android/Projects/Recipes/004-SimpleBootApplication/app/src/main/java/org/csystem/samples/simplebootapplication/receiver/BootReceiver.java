package org.csystem.samples.simplebootapplication.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import org.csystem.samples.simplebootapplication.MainActivity;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent)
    {
        //Toast.makeText(context, "onReceive", Toast.LENGTH_SHORT).show();
        Intent activityIntent = new Intent(context, MainActivity.class);

        activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(activityIntent);
    }
}
