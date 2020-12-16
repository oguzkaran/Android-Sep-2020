/*----------------------------------------------------------------------------------------------------------------------
    Sınıf Çalışması: Aşağıdaki açıklanan programı yazınız:
    - Program içerisinde bir menü olacaktır
    - Menüler sırasıyla "Ekle", "Listele" ve çıkış şeklindedir
    - Cihazlara ilişkin bilgiler (id, name, host) devices.dat isimli bir dosyada saklanacaktır
    - Dosya programın sonuna kadar açık kalacaktır
    - Örnek basit olması açısından dosya formatı kontrolü yapılmamıştır
    - Fonksiyonel programlama kullanılarak daha iyi şekilde yazılabilir
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import org.csystem.app.samples.devicesapp.DevicesPersistenceApp;

class App {
    public static void main(String[] args)
    {
        DevicesPersistenceApp.run();
    }
}
