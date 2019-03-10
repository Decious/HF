package BeatBoxFinal;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class MusicServer {
    public static void main(String[] args) {
        new MusicServer().go();
    }

    ArrayList<ObjectOutputStream> clientOutputStreams;

    public class ClientHandler implements Runnable {
        ObjectInputStream in;
        Socket clientSocket;


        public ClientHandler(Socket socket) {
            try {
                 clientSocket=socket;
                in = new ObjectInputStream(clientSocket.getInputStream());

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void run() {
            String msg;
            Object o2= null;
            Object o1= null;
            try {
                while ((o1 = in.readObject()) != null) {
                    o2 = in.readObject();
                    System.out.println("read two objects");
                    tellEveryone(o1,o2);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    private void tellEveryone(Object one, Object two) {
        Iterator it = clientOutputStreams.iterator();
        while (it.hasNext()) {
            try {
                ObjectOutputStream out =  (ObjectOutputStream)it.next();
                out.writeObject(one);
                out.writeObject(two);
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }
    }

    private void go() {
        clientOutputStreams = new ArrayList<ObjectOutputStream>();
        try {
            ServerSocket serverSocket = new ServerSocket(4242);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());

                clientOutputStreams.add(out);

                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();
                System.out.println("Got a connection");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
