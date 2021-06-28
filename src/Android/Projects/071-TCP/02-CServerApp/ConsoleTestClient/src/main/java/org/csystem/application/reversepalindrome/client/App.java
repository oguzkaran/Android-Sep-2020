package org.csystem.application.reversepalindrome.client;


import org.csystem.util.Console;

public class App {
    public static void main(String[] args)
    {
        if (args.length != 3) {
            Console.Error.writeLine("Wrong number of arguments");
            System.exit(-1);
        }

        try  {
            var server = new Client(Integer.parseInt(args[1]), Integer.parseInt(args[2]), args[0]);

            server.run();
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid port numbers");
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Server error:%s", ex.getMessage());
        }
    }
}
