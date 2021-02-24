package org.csystem.android.util.resource;

import android.content.Context;

import org.csystem.util.io.IOUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class ResourcesUtil {
    private ResourcesUtil()
    {
    }

    public static void copyRawResource(Context context, int resId, String path) throws IOException
    {
        InputStream inputStream = context.getResources().openRawResource(resId);
        FileOutputStream fileOutputStream = new FileOutputStream(path);

        IOUtil.copy(inputStream, fileOutputStream, 1024);
    }

    public static void copyRawResourceToFilesDir(Context context, int resId, String filename) throws IOException
    {
        copyRawResource(context, resId, new File(context.getFilesDir(), filename).getAbsolutePath());
    }
}
