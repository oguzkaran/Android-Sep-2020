package org.csystem.app.samples.commandpromptapp;

import org.csystem.util.ArrayUtil;
import org.csystem.util.StringUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class CommandPrompt {
    private static final String [] COMMAND_STRINGS = {"length", "reverse", "upper", "lower", "change", "clear", "gettext", "join"};
    private final ArrayList<String> m_textList;
    private final Scanner m_kb;

    private String m_prompt;

    private static String getCommandByText(String text)
    {
        if (text.length() < 3)
            return "";

        for (String s : COMMAND_STRINGS)
            if (s.startsWith(text))
                return s;

        return "";
    }

    private void lengthProc(String [] commandsStr)
    {
        if (commandsStr.length != 2) {
            System.out.println("length bir tane argüman almalıdır");
            return;
        }

        System.out.println(commandsStr[1].length());
    }

    private void reverseProc(String [] commandsStr)
    {
        if (commandsStr.length != 2) {
            System.out.println("revere bir tane argüman almalıdır");
            return;
        }

        System.out.println(StringUtil.reverse(commandsStr[1]));
    }

    private void upperProc(String [] commandsStr)
    {
        if (commandsStr.length != 2) {
            System.out.println("upper bir tane argüman almalıdır");
            return;
        }
        System.out.println(commandsStr[1].toUpperCase());
    }

    private void lowerProc(String [] commandsStr)
    {
        if (commandsStr.length != 2) {
            System.out.println("lower bir tane argüman almalıdır");
            return;
        }
        System.out.println(commandsStr[1].toLowerCase());
    }

    private void changeProc(String [] commandsStr)
    {
        if (commandsStr.length != 2) {
            System.out.println("change bir tane argüman almalıdır");
            return;
        }
        m_prompt = commandsStr[1];
    }

    private void clearProc(String [] commandsStr)
    {
        for (int i = 0; i < 30; ++i)
            System.out.println();
    }

    private void getTextProc(String [] commandsStr)
    {
        if (commandsStr.length != 1) {
            System.out.println("gettext hiç argüman almamalıdır");
            return;
        }

        System.out.println("Bu menüden çıkmak için quit yazınız");
        for (;;) {
            System.out.print("Yazıyı giriniz:");
            String text = m_kb.nextLine();

            if (text.equals("quit"))
                break;

            m_textList.add(text);
        }
    }

    private void joinProc(String [] commandsStr)
    {
        if (commandsStr.length > 2) {
            System.out.println("join en fazla 2(iki) argüman alabilir");
            return;
        }

        if (m_textList.isEmpty()) {
            System.out.println("Listede hiç yazı yok");
            return;
        }

        String str = ArrayUtil.join(m_textList, commandsStr.length == 1 ? " " : commandsStr[1]);

        System.out.println(str);
    }

    private void doWorkForCommand(String [] commandInfoStr)
    {
        switch (commandInfoStr[0]) {
            case "length":
                lengthProc(commandInfoStr);
                break;
            case "reverse":
                reverseProc(commandInfoStr);
                break;
            case "upper":
                upperProc(commandInfoStr);
                break;
            case "lower":
                lowerProc(commandInfoStr);
                break;
            case "change":
                changeProc(commandInfoStr);
                break;
            case "clear":
                clearProc(commandInfoStr);
                break;
            case "gettext":
                getTextProc(commandInfoStr);
                break;
            case "join":
                joinProc(commandInfoStr);
                break;
        }
    }

    private void parseCommand(String [] commandInfoStr)
    {
        String cmd = getCommandByText(commandInfoStr[0]);

        if (!cmd.isEmpty()) {
            commandInfoStr[0] = cmd;
            doWorkForCommand(commandInfoStr);
        }
        else
            System.out.println("Geçersiz komut");
    }

    public CommandPrompt(String p)
    {
        m_prompt = p;
        m_textList = new ArrayList();
        m_kb = new Scanner(System.in);
    }

    public void run()
    {
        System.out.println("C ve Sistem Programcıları Derneği");

        for (;;) {
            System.out.print(m_prompt + ">");
            String cmd = m_kb.nextLine().trim();

            if (cmd.equals("quit"))
                break;

            parseCommand(cmd.split("[ \t]+"));
        }

        System.out.println("C ve Sistem Programcıları Derneği");
        System.out.println("Tekrar yapıyor musunuz?");
    }
}
