package modele;

public class Joueur {
    private String nom;
    private int nbPartieGagnee = 0;

    public Joueur(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public int getNbPartieGagnee() {
        return nbPartieGagnee;
    }

    public void incrementPartieGagnee() {
        this.nbPartieGagnee++;
    }
}
