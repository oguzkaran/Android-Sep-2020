package org.csystem.app.samples.concatfilesapp;

public final class ConcatFilesApp {
    private ConcatFilesApp()
    {
    }

    public static void run(String [] args)
    {
        ConcatFiles concatFiles = new ConcatFiles(args);

        concatFiles.run();
    }
}
