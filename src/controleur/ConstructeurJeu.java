package controleur;

import modele.Tas;
import vue.Ihm;

public class ConstructeurJeu {
    private static int NB_TAS;
    private static int CONTRAINTE;
    private Tas jeu;

    public ConstructeurJeu(Ihm vue) {
        NB_TAS = vue.initialisationTas();
        CONTRAINTE = vue.jouerAvecContrainte();
    }

    public ConstructeurJeu() {

    }

    public void construireJeu() {
        jeu = new Tas(NB_TAS, CONTRAINTE);
    }

    public Tas getLesTas() {
        return jeu;
    }

    public void setJeu(Tas jeu) {
        this.jeu = jeu;
    }
}
