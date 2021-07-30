package org.csystem.samples.providerapp;

import android.app.Application;
import android.net.Uri;
import android.widget.Toast;

import java.util.ArrayList;

public class ProviderApplication extends Application {
    private static ArrayList<Uri> ms_uris;
    @Override
    public void onCreate()
    {
        try {
            Global.copyFiles(this, "shared_images");
            ms_uris = Global.getUris(this, "shared_images", "org.csystem.shareimage");
            super.onCreate();
        }
        catch (Throwable ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public static ArrayList<Uri> getUris()
    {
        return ms_uris;
    }
}
