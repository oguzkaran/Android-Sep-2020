package org.csystem.app.samples.devicesapp;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class DevicesPersistence implements Closeable {
    private final Scanner m_kb = new Scanner(System.in);
    private final  RandomAccessFile m_randomAccessFile;

    private void displayMenu()
    {
        System.out.println("1.Ekle");
        System.out.println("2.Listele");
        System.out.println("3.Çıkış");
        System.out.print("Seçenek:");
    }

    private void insertProc() throws IOException
    {
        System.out.print("id değerini giriniz:");
        int id = Integer.parseInt(m_kb.nextLine());

        System.out.print("Adını giriniz:");
        String name = m_kb.nextLine();

        System.out.print("Ip adresini giriniz:");
        String host = m_kb.nextLine();

        m_randomAccessFile.seek(m_randomAccessFile.length());
        m_randomAccessFile.writeInt(id);
        m_randomAccessFile.writeUTF(name);
        m_randomAccessFile.writeUTF(host);
    }

    private void listProc() throws IOException
    {
        try {
            m_randomAccessFile.seek(0);
            for (;;) {
                int id = m_randomAccessFile.readInt();
                String name = m_randomAccessFile.readUTF();
                String host = m_randomAccessFile.readUTF();

                System.out.printf("[%d]%s-%s%n", id, name, host);
            }
        }
        catch (EOFException ex) {
            System.out.println("Okuma tamamlandı");
        }
    }

    private void exitProc()
    {
        System.out.println("C ve Sistem Programsıları Derneği");
        System.out.println("Tekrar yapıyor musunuz?");
        System.exit(0);
    }

    private void doWorkForOption(int option) throws IOException
    {
        switch (option) {
            case 1:
                this.insertProc();
                break;
            case 2:
                listProc();
                break;
            case 3:
                exitProc();
                break;
            default:
                System.out.println("Geçersiz seçenek");
        }
    }

    public DevicesPersistence(String path) throws IOException
    {
        m_randomAccessFile = new RandomAccessFile(path, "rw");
    }

    public void run()
    {
        for (;;) {
            try {
                this.displayMenu();
                this.doWorkForOption(Integer.parseInt(m_kb.nextLine()));
            }
            catch (NumberFormatException ex) {
                System.out.println("Geçersiz giriş");
            }
            catch (Throwable ex) {
                System.out.println("Beklenmedik bir durum oluştu");
            }
        }
    }

    public void close() throws IOException
    {
        m_randomAccessFile.close();
    }
}
