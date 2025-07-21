// Interface pour les fabriques d'objets liés au craquage de mot de passe
public interface CrackerFactory {

    // Méthode pour créer une attaque (force brute ou dictionnaire)
    Attack createAttack();

    // Méthode pour créer une cible (locale ou en ligne)
    Target createTarget();
}
