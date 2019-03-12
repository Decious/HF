package net;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DailyActiveServer {
    String[] adviceList = {"Ешьте меньшими порциями", "Купите облегающие джинсы. Нет, они не делают вас полнее",
            "Два слова: не годится", "Будьте честны хотя бы сегодня. Скажите начальнику все, что вы *на самом деле* о нем думаете",
            "Возможно вам стоит выбрать другую прическу"};

    public void go() {
        try {
            ServerSocket serverSocket = new ServerSocket(4242);
            while (true) {
                Socket socket = serverSocket.accept();

                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                String advice = getAdvice();
                writer.println(advice);
                writer.close();
                System.out.println(advice);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getAdvice() {
    int random = (int)(Math.random()*adviceList.length);
    return adviceList[random];
    }

    public static void main(String[] args) {
        DailyActiveServer server = new DailyActiveServer();
        server.go();
    }
}
