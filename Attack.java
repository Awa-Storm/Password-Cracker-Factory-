
import java.io.*;

// Interface représentant une attaque sur un mot de passe
public interface Attack {

    // Méthode à implémenter pour exécuter l'attaque sur une cible
    void execute(Target target);
}
// Implémentation d'une attaque par force brute
class BruteForceAttack implements Attack {

    // Exécute l'attaque par force brute sur la cible
    public void execute(Target target) {
        String chars = "abcdefghijklmnopqrstuvwxyz0123456789";// caractères possibles
        int maxLength = 4; // Longueur maximale du mot de passe à tester

        // Génération de tous les mots de passe possibles jusqu'à la longueur maximale
        // et test de chacun d'eux sur la cible
        for (int i = 0; i < Math.pow(chars.length(), maxLength); i++) {
            String password = generatePassword(i, chars, maxLength);// génère un mot de passe
            if (target.tryPassword(password)) {// teste le mot de passe sur la cible
                System.out.println("Mot de passe trouvé : " + password);// affiche si trouvé
                break;// arrête la recherche
            }
        }
    }
    // Génère un mot de passe à partir d'un index donné, en utilisant les caractères spécifiés
    private String generatePassword(int index, String chars, int length) {
        StringBuilder sb = new StringBuilder();

        // Convertit l'index en mot de passe selon les caractères disponibles
        while (index > 0) {
            sb.append(chars.charAt(index % chars.length()));
            index /= chars.length();
        }

         // Complète le mot de passe si nécessaire
        while (sb.length() < length) {
            sb.append(chars.charAt(0));
        } 
        // Retourne le mot de passe dans le bon ordre
        return sb.reverse().toString();
    }
}

// Implémentation d'une attaque par dictionnaire
// Cette attaque utilise un fichier texte contenant une liste de mots de passe possibles
class DictionaryAttack implements Attack {

    // Exécute l'attaque par dictionnaire sur la cible
    public void execute(Target target) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("dictionnaire.txt"));
            String password;

            // Parcourt chaque mot du dictionnaire
            while ((password = reader.readLine()) != null) {
                if (target.tryPassword(password)) { // teste le mot de passe sur la cible
                    System.out.println("Mot de passe trouvé : " + password);// affiche si trouvé
                    break;// arrête la recherche
                }
            }
            reader.close();// ferme le fichier
        } catch (IOException e) {
            // Gère les erreurs de lecture du fichier
            System.out.println("Erreur lors de la lecture du dictionnaire : " + e.getMessage());
        }
    }
}
