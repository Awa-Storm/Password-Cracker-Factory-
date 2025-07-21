
import java.net.*;
import java.io.*;

// Interface représentant une cible à attaquer
public interface Target {

    // Méthode pour tester un mot de passe sur la cible
    boolean tryPassword(String password);
}

// Cible locale : vérifie le mot de passe en mémoire
class LocalTarget implements Target {

    private String correctPassword = "passer123";// Mot de passe correct

    // Teste si le mot de passe proposé est correct
    public boolean tryPassword(String password) {
        if (password.equals(correctPassword)) {
            System.out.println("Connexion réussie avec : " + password);
            return true;// Mot de passe trouvé
        }
        return false;// Mot de passe incorrect
    }
}

// Cible en ligne : vérifie le mot de passe via une requête HTTP
class OnlineTarget implements Target {

    // Teste le mot de passe en envoyant une requête au serveur distant
    public boolean tryPassword(String password) {
        try {
             // Prépare l'URL avec le login et le mot de passe à tester
            String urlString = "http://localhost/login.php?login=admin&password=" + URLEncoder.encode(password, "UTF-8");
            URL url = new URL (urlString);

            // Ouvre la connexion et lit la réponse
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String response;
            
            // Parcourt la réponse pour détecter une connexion réussie
            while ((response = in.readLine()) != null) {
                if (response.contains("Connexion réussie")) {
                    System.out.println("Connexion réussie avec : " + password);
                    return true;// Mot de passe trouvé
                }
            }
            in.close();// Ferme le flux
        } catch (Exception e) {

            // Affiche une erreur en cas de problème de connexion
            System.out.println("Erreur lors de la connexion en ligne : " + e.getMessage());
        }
        return false;// Mot de passe incorrect ou erreur
    }
}
