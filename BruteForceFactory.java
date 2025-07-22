
// Classe qui fabrique des objets pour une attaque par force brute
public class BruteForceFactory implements CrackerFactory {

    private String targetType;// Type de cible ("local" ou autre)
    
    // Constructeur qui initialise le type de cible
    public BruteForceFactory(String targetType) { 
        this.targetType = targetType;
    }
    // Crée et retourne une attaque de type BruteForceAttack
    public Attack createAttack() {
        return new BruteForceAttack();
    }
    // Crée et retourne la cible selon le type choisi
    public Target createTarget() {
        if (targetType.equals("local")) {
            return new LocalTarget();// cible locale
        } else {
            return new OnlineTarget();// cible en ligne
        }
    }
}
