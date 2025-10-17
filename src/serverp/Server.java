package serverp;
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket socketserveur = new ServerSocket(1234)) {
            System.out.println("En attente de connexion...");
            Socket socket = socketserveur.accept();
            System.out.println("Un client est connecté.");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            while (true) {
                int n1 = in.readInt();
                String op = in.readUTF();
                int n2 = in.readInt();
                if ("EXIT".equals(op)) {
                    System.out.println("Client déconnecté.");
                    break;
                }
                int res = 0;
                switch (op) {
                    case "+": res = n1 + n2; break;
                    case "-": res = n1 - n2; break;
                    case "*": res = n1 * n2; break;
                    case "/": res = (n2 != 0) ? n1 / n2 : 0; break;
                }
                out.writeUTF(String.valueOf(res));
                System.out.println(n1 + op + n2 + "=" + res);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
