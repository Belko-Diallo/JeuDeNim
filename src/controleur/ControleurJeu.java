package controleur;

import exceptions.CoupJouerInvalideException;
import modele.Coup;
import modele.Joueur;
import modele.Tas;
import vue.Ihm;

public class ControleurJeu {
    private Ihm ihm;
    private String joueurCourant; //permet de passer d'un joueur à l'autre en fonction de son tour
    private Joueur[] lesJoueurs;
    private Tas clone;

    public ControleurJeu(Ihm ihm, Tas lesTas) {
        this.ihm = ihm;
        this.ihm.setConstrucJeu(lesTas);// lie le tas crée au jeu
        this.ihm.setCtrlJeu(this);//lie lecontroleur à IHM

        String[] joueurs = ihm.lesJoueurs();
        Joueur j1 = new Joueur(joueurs[0]);
        Joueur j2 = new Joueur(joueurs[1]);

        joueurCourant = joueurs[0]; //initialise le joueur courant
        this.lesJoueurs = new Joueur[]{j1, j2};
        this.ihm.baseJeu();// énonce la syntaxe à adopter pour jouer

        this.clone = this.ihm.getConstrucJeu().getLesTas().clone();// clonage du tas de départ
    }

    public void commencerJeu() {
        int[] coup;
        do {
            this.ihm.etatJeu();
            String preCoup = this.ihm.coupAjouer(joueurCourant); // récupère le choix du joueur courant
            coup = verificationCoup(preCoup);

            try {
                this.ihm.getConstrucJeu().getLesTas().changerTas(new Coup(coup[0], coup[1]));// modifie le tas si coup valide
            } catch (CoupJouerInvalideException e) {
                this.ihm.messageErreur(e.getMessage()); // on capture les erreurs lié à la méthode changerTas et ses sous méthodes
                this.commencerJeu(); // on relance le jeu sans changer le joueur courant
            }
            if (partieTerminee()) { // Arrêt de la boucle infinie
                break;
            } else { // sinon change le joueur courant et Affiche les tas d'allumettes
                this.setJoueurCourant();
            }
        } while (true);
        this.incrementerScoreGagnant();// le joueur courant gagne la partie
        if (this.ihm.finDePartie(joueurCourant)) {
            rejouer();
        }

    }

    private int[] verificationCoup(String preCoup) {
        String[] lesValeurs = preCoup.split(" "); // scinde le string en un tableau de string avec comme délimiteur un espace vide
        int numTas = 0, nbAllu = 0;
        try {
            numTas = Integer.parseInt(lesValeurs[0]); // transforme la chaine de caractères en int
            nbAllu = Integer.parseInt(lesValeurs[1]);
            if (numTas < 1 || nbAllu < 1) { // les chiffres négatifs n'ont pas lieu d'être
                throw new CoupJouerInvalideException("Le coup joué n'est pas valide !");
            }
        } catch (NumberFormatException e) {
            this.ihm.messageErreur("Le coup est incorrect !");
            this.ihm.baseJeu();
            this.commencerJeu();// relance le jeu, permettant au joueur de rejouer
        } catch (CoupJouerInvalideException e) {
            this.ihm.messageErreur(e.getMessage());// tranfère le message d'erreur à la Vue qui elle se chargera de l'afficher
            this.commencerJeu();// relance le jeu, permettant au joueur de rejouer
        }
        return new int[]{numTas, nbAllu}; // retour le coup à jouer
    }

    private boolean partieTerminee() {
        return this.ihm.getConstrucJeu().getLesTas().tasVide();
    }

    private void rejouer() {
        setJoueurCourant(); // on passe la main au prochain joueur
        this.ihm.setConstrucJeu(this.clone);//on insere la clone du tas faite au début
        this.clone = this.clone.clone();// on refait un clone du clone

        commencerJeu(); // début d'un nouveau jeu
    }

    private void incrementerScoreGagnant() {
        if (lesJoueurs[0].getNom().equals(joueurCourant)) {
            lesJoueurs[0].incrementPartieGagnee();
        } else {
            lesJoueurs[1].incrementPartieGagnee();
        }
    }

    /*
    Passes le tour d'un joueur à l'autre
     */
    private void setJoueurCourant() {
        if (joueurCourant.equals(lesJoueurs[0].getNom())) {
            joueurCourant = lesJoueurs[1].getNom();
        } else {
            joueurCourant = lesJoueurs[0].getNom();
        }
    }

    public Joueur[] getLesJoueurs() {
        return lesJoueurs;
    }
}
