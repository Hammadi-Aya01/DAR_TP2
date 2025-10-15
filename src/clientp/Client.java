package clientp;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String SERVER_IP = "192.168.56.1";

        try {
            InetAddress serverAddress = InetAddress.getByName(SERVER_IP);
            InetSocketAddress socketAddress = new InetSocketAddress(serverAddress, 12340);
            Socket socket = new Socket();
            socket.connect(socketAddress);

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("1.  + ");
                System.out.println("2.  - ");
                System.out.println("3.  * ");
                System.out.println("4.  / ");
                System.out.println("0. Quitter");
                System.out.print("Votre choix (0-4) : ");

                int choix = scanner.nextInt();

                if (choix == 0) {
                    out.writeInt(0);
                    break;
                }

                if (choix < 1 || choix > 4) {
                    System.out.println("Choix invalide !");
                    continue;
                }

                System.out.print("Entrez la valeur de x : ");
                int x = scanner.nextInt();

                out.writeInt(choix);
                out.writeInt(x);

                int resultat = in.readInt();
                System.out.println("Resultat : " + resultat);
            }

            out.close();
            in.close();
            socket.close();
            System.out.println("Deconnexion terminee.");

        } catch (IOException e) {
            System.err.println("Erreur de connexion au serveur.");
            e.printStackTrace();
        }
    }
}