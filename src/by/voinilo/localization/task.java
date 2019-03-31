package by.voinilo.localization;

import java.io.Console;
import java.util.Locale;
import java.util.Scanner;

public class task {
    static Res res = Res.INSTANCE;
    public static void main(String[] args) {

while (true) {
    Scanner scanner = new Scanner(System.in);
    String s= scanner.nextLine();
    if (s.equals("1")) {
        res.setLocale(new Locale("be", "BY"));
        show();
    }
    if (s.equals("2")) {
        res.setLocale(new Locale("en", "US"));
        show();
    }
    if (s.equals("3")) {
        res.setLocale(new Locale("ru", "RU"));
        show();
    }
    if (s.isEmpty())break;
}

    }

    static void show() {
        System.out.println(res.get(Str.MSG_WELCOME));
        System.out.println(res.get(Str.MSG_QUESTION));
        System.out.println(res.get(Str.MSG_NAME));
    }

}
