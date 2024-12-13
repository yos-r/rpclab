import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class Client {
    private static Scanner scanner = new Scanner(System.in);
    private static XmlRpcClient client;

    public static void main(String[] args) throws Exception {
        // Configuration du client XML-RPC
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL("http://localhost:8081"));

        client = new XmlRpcClient();
        client.setConfig(config);

        while (true) {
            afficherMenu();
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne

            switch (choix) {
                case 1: calculerNotes(); break;
                case 2: gererNotes(); break;
                case 0: 
                    System.out.println("Fin du programme.");
                    return;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

    private static void afficherMenu() {
        System.out.println("\n--- Menu Principal ---");
        System.out.println("1. Calculatrice de Notes");
        System.out.println("2. Gestion des Notes");
        System.out.println("0. Quitter");
        System.out.print("Votre choix : ");
    }

    private static void calculerNotes() throws XmlRpcException {
        System.out.println("\n--- Calculatrice de Notes ---");
        System.out.print("Première note : ");
        double note1 = scanner.nextDouble();
        System.out.print("Deuxième note : ");
        double note2 = scanner.nextDouble();

        System.out.println("1. Addition");
        System.out.println("2. Soustraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");
        System.out.print("Opération : ");
        int op = scanner.nextInt();

        String methode = "";
        switch (op) {
            case 1: methode = "addition"; break;
            case 2: methode = "soustraction"; break;
            case 3: methode = "multiplication"; break;
            case 4: methode = "division"; break;
        }

        Object[] params = {note1, note2};
        double resultat = (Double) client.execute("Calculatrice." + methode, params);
        System.out.println("Résultat : " + resultat);
    }

    private static void gererNotes() throws XmlRpcException {
        while (true) {
            System.out.println("\n--- Gestion des Notes ---");
            System.out.println("1. Ajouter une note");
            System.out.println("2. Supprimer une note");
            System.out.println("3. Modifier une note");
            System.out.println("4. Lister les notes");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne

            switch (choix) {
                case 1:
                    System.out.print("Note à ajouter : ");
                    double nouvelleNote = scanner.nextDouble();
                    client.execute("GestionNotes.ajouterNote", new Object[]{nouvelleNote});
                    System.out.println("Note ajoutée.");
                    break;
                case 2:
                    System.out.print("Note à supprimer : ");
                    double noteASupprimer = scanner.nextDouble();
                    boolean supprime = (Boolean) client.execute("GestionNotes.supprimerNote", new Object[]{noteASupprimer});
                    System.out.println(supprime ? "Note supprimée." : "Note non trouvée.");
                    break;
                case 3:
                    System.out.print("Ancienne note : ");
                    double ancienneNote = scanner.nextDouble();
                    System.out.print("Nouvelle note : ");
                    double nouvelleNoteModifiee = scanner.nextDouble();
                    boolean modifie = (Boolean) client.execute("GestionNotes.modifierNote", new Object[]{ancienneNote, nouvelleNoteModifiee});
                    System.out.println(modifie ? "Note modifiée." : "Note non trouvée.");
                    break;
                case 4:
                    List<Double> notes = (List<Double>) client.execute("GestionNotes.listerNotes", new Object[]{});
                    System.out.println("Liste des notes : " + notes);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }
}