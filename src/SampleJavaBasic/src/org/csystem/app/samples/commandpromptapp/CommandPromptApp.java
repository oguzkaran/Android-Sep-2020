/*----------------------------------------------------------------------------------------------------------------------
    Sınıf Çalışması: Aşağıdaki gibi bir komut yorumlayıcı uygulama yazınız.
    Açıklamalar:
    1. get komutu çalıştırıldığında klavyeden exit girilene kadar alınan yazılar bir ArrayList'e eklenecektir.
    2. join komutu ile Arraylist içerisindeki yazılar join komutu ile alınan yazı ile birleştirilecektir. join komutuna
    hiç yazı verilmezse space karakteri ile yazıları birleştirecektir
    4. CommandPromptApp uygulamasına diğer komutlarla birlikte bu komutlar da eklenecektir
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app.samples.commandpromptapp;

public final class CommandPromptApp {
    private CommandPromptApp()
    {
    }

    public static void run()
    {
        CommandPrompt commandPrompt = new CommandPrompt("CSD");

        commandPrompt.run();
    }
}
