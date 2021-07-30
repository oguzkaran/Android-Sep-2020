package org.csystem.android.application;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class AndroidIOUtil {
    private static void copy(InputStream src, OutputStream dest, int blockSize, boolean flush) throws IOException
    {
        int read;
        byte [] buf = new byte[blockSize];

        while ((read = src.read(buf)) > 0)
            dest.write(buf, 0, read);

        if (flush)
            dest.flush();
    }

    private AndroidIOUtil() {}

    public static void copyResource(Context context, int resId, String filePath, int blockSize) throws IOException
    {
        String filename = context.getResources().getResourceEntryName(resId);
        File path = new File(new File(context.getFilesDir(), filePath), filename);

        path.getParentFile().mkdirs();

        copy(context.getResources().openRawResource(resId), new FileOutputStream(path), blockSize, true);
    }

    public static void copyDbIfNotExists(String dbName, int blockSize) throws IOException
    {
        File path = App.getInstance().getApplicationContext().getDatabasePath(dbName);

        path.getParentFile().mkdirs();

        if (!path.exists())
            copy(App.getInstance().getAssets().open(dbName), new FileOutputStream(path), blockSize, true);
    }
}
