
public class CrackerApp {

    public static void main(String[] args) {

        // Vérifie que deux arguments sont fournis (type d'attaque et type de cible)
        if (args.length < 2) {
            System.out.println("Usage: java CrackerApp [brute|dict] [local|online]"); 
            return;// Arrête le programme si les arguments sont manquants
        }

        String attackType = args[0];// Récupère le type d'attaque ("brute" ou "dict")
        String targetType = args[1];// Récupère le type de cible ("local" ou "online")

        CrackerFactory factory;// Fabrique pour créer les objets nécessaires

        // Choisit la fabrique en fonction du type d'attaque
        if (attackType.equals("brute")) {
            factory = new BruteForceFactory(targetType);// fabrique pour force brute
        } else {
            factory = new DictionaryFactory(targetType); // fabrique pour dictionnaire
        }

        Attack attack = factory.createAttack();// Crée l'objet attaque
        Target target = factory.createTarget();// Crée l'objet cible

        attack.execute(target);// Lance l'attaque sur la cible
    }
    }

