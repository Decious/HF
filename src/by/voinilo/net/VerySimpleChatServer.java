package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class VerySimpleChatServer {
    ArrayList clientOutputStreams;

    public class ClientHandler implements Runnable{

        BufferedReader reader;
        Socket socket;

        public ClientHandler(Socket clientSocket) {
            try{
                socket = clientSocket;
                InputStreamReader isr = new InputStreamReader(socket.getInputStream());
                reader = new BufferedReader(isr);
            }catch (Exception ex){ex.printStackTrace();}
        }


        @Override
        public void run() {
            String msg;
            try{
                while ((msg = reader.readLine())!=null){
                    System.out.println("read " + msg);
                    tellEveryone(msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new VerySimpleChatServer().go();
    }

    private void go() {
        clientOutputStreams = new ArrayList();
        try{
            ServerSocket serverSocket = new ServerSocket(5000);

            while(true){
                Socket clientSocket = serverSocket.accept();
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                clientOutputStreams.add(writer);

                Thread t = new Thread (new ClientHandler(clientSocket));
                t.start();
                System.out.println("Got a connection");
            }

        }catch (Exception ex){ex.printStackTrace();}
    }

    private void tellEveryone(String msg) {
        Iterator it = clientOutputStreams.iterator();
        while (it.hasNext()){
            try{
                PrintWriter writer = (PrintWriter) it.next();
                writer.println(msg);
                writer.flush();
            }catch (Exception ex1){
                ex1.printStackTrace();
            }
        }
    }
}
