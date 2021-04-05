package org.csystem.app.samples.copyandbackup;

import org.csystem.util.CommanLineUtil;

import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;

public class CopyAndBackup {
    private Path m_sourcePath;
    private Path m_destinationPath;

    private void parseCommand(String [] args)
    {
        args = CommanLineUtil.getArguments(args, "Kaynak dosya ve hedef dosya yol ifadelerini giriniz:", new Scanner(System.in));

        if (args.length != 2) {
            System.out.println("Kullanım:: java CopyAndBackupApp <kaynak dosya> <hedef dosya>");
            System.exit(-1);
        }

        m_sourcePath = Path.of(args[0]);
        m_destinationPath = Path.of(args[1]);
    }

    private void backupAndCopy() throws IOException
    {
        Files.move(m_destinationPath, Path.of(m_destinationPath.toString() + "-bak"), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(m_sourcePath, m_destinationPath);
        System.out.println("Kopyalama tamamlandı eski dosya kaydedildi");
    }

    private void copy() throws IOException
    {
        try {
            Files.copy(m_sourcePath, m_destinationPath);
            System.out.println("Kopyalama tamamlandı");
        }
        catch (FileAlreadyExistsException ex) {
            this.backupAndCopy();
        }
    }

    public CopyAndBackup(String [] args)
    {
        this.parseCommand(args);
    }

    public void run()
    {
        try {
            this.copy();
        }
        catch (NoSuchFileException ex) {
            System.out.println("Kaynak dosya bulunamadı");
        }
        catch (Throwable ex) {
            System.out.println("Belirlenemeyen bir durum oluştu");
        }
    }
}
