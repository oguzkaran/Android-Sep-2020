package org.csystem.app.samples.devicesapp;

public final class DevicesPersistenceApp {
    private DevicesPersistenceApp()
    {
    }

    public static void run()
    {
        try (DevicesPersistence devicesPersistence = new DevicesPersistence("devices.dat")) {
            devicesPersistence.run();
        }
        catch (Throwable ex) {
            System.out.println("Beklenmedik bir durum oluştu. Uygulama sonlanıyor...");
        }
    }
}
