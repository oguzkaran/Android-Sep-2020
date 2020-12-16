package org.csystem.app.samples.concatfilesapp;

import org.csystem.util.CommanLineUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class ConcatFiles {
    private final ArrayList<Path> m_sourcePaths = new ArrayList<>();
    private File m_destinationFile;

    private void parseCommand(String [] args)
    {
        args = CommanLineUtil.getArguments(args, "Kaynak dosyalar ve hedef dosyaları giriniz:", new Scanner(System.in));

        if (args.length < 2) {
            System.out.println("Kullanım:: java CopyAndBackupApp <file1> <file2> ...<fileN> <destination file>");
            System.exit(-1);
        }

        for (int i = 0; i < args.length - 1; ++i)
            m_sourcePaths.add(Path.of(args[i]));

        m_destinationFile = new File(args[args.length - 1]);
        m_destinationFile.delete();
    }

    private void concat() throws IOException
    {
        try (FileOutputStream fileOutputStream = new FileOutputStream(m_destinationFile, true)) {
            for (Path path : m_sourcePaths)
                Files.copy(path, fileOutputStream);
        }
        catch (NoSuchFileException ex) {
            System.out.println("Kaynak dosya bulunamadı");
            m_destinationFile.delete();
            System.exit(-1);
        }
    }

    public ConcatFiles(String [] args)
    {
        this.parseCommand(args);
    }

    public void run()
    {
        try {
            this.concat();
        }
        catch (Throwable ex) {
            System.out.println("Belirlenemeyen bir durum oluştu");
        }
    }
}
