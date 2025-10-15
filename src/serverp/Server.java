package serverp;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Server {
    public static void main(String[] args) {
        try {
            InetAddress bindAddress = InetAddress.getByName("0.0.0.0");
            InetSocketAddress socketAddress = new InetSocketAddress(bindAddress, 12340);
            ServerSocket socketserveur = new ServerSocket();
            socketserveur.bind(socketAddress);

            System.out.println("Serveur en attente...");
            Socket socket = socketserveur.accept();
            System.out.println("Client connecté : " + socket.getInetAddress());

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            while (true) {
            	int n1 = in.read();
                String op= in.readUTF(); //na9rou byhe chaine 'readUTF'
                int n2= in.read();
                if ("EXIT".equals(op)) {
                    System.out.println("Client déconnecté.");
                    break;
                }

                int res = 0;

                switch (op) {
                    case "+":
                        res = n1 + n2;
                        break;
                    case "-":
                        res= n1 - n2;
                        break;
                    case "*":
                        res= n1 * n2;
                        break;
                    case "/":
                        if (5 != 0) {
                            res = n1 / n2;
                        }
                        break;
                    default:
                        res = 0;
                }

                out.writeInt(res);
                System.out.println(n1 + op + n2+ "="+ res);
            }

            in.close();
            out.close();
            socket.close();
            socketserveur.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}