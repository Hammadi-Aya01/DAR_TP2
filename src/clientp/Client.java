package clientp;
import java.io.*;
import serverp.Operation;
import java.net.*;
import java.util.Scanner;
import java.util.regex.Pattern;
public class Client {
    private static final Pattern VALID_INPUT = Pattern.compile("^\\s*(-?\\d+)\\s*([+\\-*/])\\s*(-?\\d+)\\s*$");
    public static void main(String[] args) {
        try (
            Socket socket = new Socket("127.0.0.1", 1254);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("Client connecté au serveur.");
            System.out.println("Entrez une opération (ex: 55 * 25) ou '0' pour quitter :");

            while (true) {
                System.out.print(">>> ");
                String input = scanner.nextLine().trim();

                if ("0".equals(input)) {
                    out.writeObject(new Operation(0, "EXIT", 0));
                    break;
                }

                var matcher = VALID_INPUT.matcher(input);
                if (!matcher.matches()) {
                    System.out.println("Syntaxe invalide");
                    continue;
                }

                int n1 = Integer.parseInt(matcher.group(1));
                String op = matcher.group(2);
                int n2 = Integer.parseInt(matcher.group(3));
                // Envoie l'objet complet
                out.writeObject(new Operation(n1, op, n2));
                String response = in.readUTF();
                System.out.println("Résultat : " + response);
            }

            System.out.println("Fin de la communication.");

        } catch (IOException e) {
            System.err.println("Impossible de se connecter au serveur.");
            e.printStackTrace();
        }
    }
}