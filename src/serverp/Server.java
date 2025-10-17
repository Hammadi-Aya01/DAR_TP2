package serverp;
import java.io.*;
import java.net.*;
public class Server {
    public static void main(String[] args) {
        try (ServerSocket socketserveur = new ServerSocket(1254)) {
            System.out.println("En attente de connexion...");
            Socket socket = socketserveur.accept();
            System.out.println("Un client est connecté.");
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            while (true) {
                // Lit l'objet envoyé par le client
            	Operation opObj = (Operation) in.readObject();
                String op = opObj.getOperateur();
                if ("EXIT".equals(op)) {
                	System.out.println("Client déconnecté.");
                    break;
                }
                int n1 = opObj.getOperande1();
                int n2 = opObj.getOperande2();
                int res = 0;
                switch (op) {
                    case "+": res = n1 + n2; break;
                    case "-": res = n1 - n2; break;
                    case "*": res = n1 * n2; break;
                    case "/": 
                        if (n2 == 0) {
                            out.writeUTF("Erreur: division par zéro");
                            continue;
                        }
                        res = n1 / n2;
                        break;
                    default:
                        out.writeUTF("Opérateur inconnu");
                        continue;
                }
               out.writeUTF(String.valueOf(res));
                System.out.println(n1 + op + n2 + " = " + res);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}