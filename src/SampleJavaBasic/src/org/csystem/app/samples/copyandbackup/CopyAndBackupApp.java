package org.csystem.app.samples.copyandbackup;

public final class CopyAndBackupApp {
    private CopyAndBackupApp()
    {
    }

    public static void run(String [] args)
    {
        CopyAndBackup copyAndBackup = new CopyAndBackup(args);

        copyAndBackup.run();
    }
}
