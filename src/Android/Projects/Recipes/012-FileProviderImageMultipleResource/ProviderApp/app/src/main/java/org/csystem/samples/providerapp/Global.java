package org.csystem.samples.providerapp;

import android.content.Context;
import android.net.Uri;

import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public final class Global {
    private static final String [] IMAGES;

    static {

        IMAGES = new String[] {"image", "images", "download", "resim"};
    }

    private Global()
    {
    }

    public static void copyFiles(Context context, String filePath) throws IOException
    {
        for (String name : IMAGES)
            ResourceUtil.copyResource(context, context.getResources().getIdentifier(name, "raw", context.getPackageName()), filePath, 1024);
    }

    public static ArrayList<Uri> getUris(Context context, String dirPath, String authority)
    {
        ArrayList<Uri> uris = new ArrayList<>();

        File dir = new File(context.getFilesDir(), dirPath);

        for (File file : dir.listFiles()) {
            uris.add(FileProvider.getUriForFile(context, authority, file));
        }

        return uris;
    }
}
