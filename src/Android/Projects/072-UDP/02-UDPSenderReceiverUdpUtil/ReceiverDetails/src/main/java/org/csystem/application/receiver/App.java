package org.csystem.application.receiver;

import org.csystem.util.Console;

public class App {
    public static void main(String[] args)
    {
        if (args.length != 1) {
            Console.Error.writeLine("Wrong number of arguments");
            System.exit(-1);
        }

        try  {
            var receiver = new Receiver(Integer.parseInt(args[0]));

            receiver.run();
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid port number");
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Server error:%s", ex.getMessage());
        }
    }
}
