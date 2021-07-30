package org.csystem.samples.providerapp;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class ResourceUtil {
    private ResourceUtil()
    {
    }

    public static void copyResource(Context context, int resId, String filePath, int blockSize) throws IOException
    {
        String filename = context.getResources().getResourceEntryName(resId);
        File file = new File(new File(context.getFilesDir(), filePath), filename);

        file.getParentFile().mkdirs();

        try (InputStream inputStream = context.getResources().openRawResource(resId);
            OutputStream outputStream = new FileOutputStream(file)) {
            byte [] buf = new byte[blockSize];
            int read;

            while ((read = inputStream.read(buf)) > 0)
                outputStream.write(buf, 0, read);

            outputStream.flush();
        }
        catch (Throwable ex){
            throw ex;
        }
    }
}
