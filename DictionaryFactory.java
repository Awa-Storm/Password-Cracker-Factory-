// Classe qui fabrique des objets pour une attaque par dictionnaire
public class DictionaryFactory implements CrackerFactory {

    private String targetType;// Type de cible ("local" ou "online")

    // Constructeur qui initialise le type de cible
    public DictionaryFactory(String targetType) {
        this.targetType = targetType;
    } 
    // Crée et retourne une attaque de type DictionaryAttack
    public Attack createAttack() {
        return new DictionaryAttack();
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
