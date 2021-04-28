package org.csystem.samples.application.samplesqlite.data.util;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class AndroidIOUtil {
    private static void copy(InputStream src, OutputStream dest, int blockSize) throws IOException
    {
        int read;
        byte [] buf = new byte[blockSize];

        while ((read = src.read(buf)) > 0)
            dest.write(buf, 0, read);

        dest.flush();
    }

    private AndroidIOUtil()
    {}
    public static void copyDbIfNotExists(Context context, String dbName, int blockSize) throws IOException
    {
        File path = context.getDatabasePath(dbName);

        path.getParentFile().mkdirs();

        if (!path.exists())
            copy(context.getAssets().open(dbName), new FileOutputStream(path), blockSize);
    }
}
