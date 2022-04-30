package vue;

import controleur.ConstructeurJeu;
import controleur.ControleurJeu;
import modele.Tas;

import java.util.Scanner;

public class Ihm {
    private ConstructeurJeu construcJeu = new ConstructeurJeu();
    private ControleurJeu ctrlJeu;
    private static Scanner in = new Scanner(System.in);

    public void setConstrucJeu(Tas tas) {
        this.construcJeu.setJeu(tas);
    }

    public ConstructeurJeu getConstrucJeu() {
        return this.construcJeu;
    }

    public void setCtrlJeu(ControleurJeu ctrlJeu) {
        this.ctrlJeu = ctrlJeu;
    }

    public int initialisationTas() {
        int nbTas = 0;
        while (nbTas < 1) {
            System.out.print("Nombre de tas d'allumettes voulu : ");
            if (in.hasNextInt()) {
                nbTas = in.nextInt();
                if (nbTas < 1) System.out.println("Un tas d'allumettes doit être supérieur ou égal à 1 !");
            } else {
                in.nextLine();
            }
        }
        return nbTas;
    }

    /*
    Détermine le nombre d'allumette maximale pouvant être prise par tour de jeu
    Forcément supérieur à 0
    0 => valeur par défaut, c'est à dire maximun 3 allumettes par tour
     */
    public int jouerAvecContrainte() {
        int contrainte = -1;
        while (contrainte < 0) {
            System.out.println("------------------Jouer avec une contrainte------------------");
            System.out.println("Tapez un chiffre d'allumettes maximal à retirer lors d'un coup");
            System.out.println("Tapez 0 pour un jeu par défaut");
            System.out.print("Entrée : ");
            if (in.hasNextInt()) {
                contrainte = in.nextInt();
                if (contrainte < 0) System.out.println("Le nombre doit être supérieur ou égal à 0 !");
            } else {
                in.nextLine();
                System.out.println();
            }
        }
        return contrainte;
    }

    public String[] lesJoueurs() {
        String[] lesJoueurs = new String[2];
        String nomJoueur = "";
        while (true) {
            for (int i = 1; i <= 2; i++) {
                System.out.print("Entrez le nom du joueur " + i + " : ");
                nomJoueur = in.next();
                System.out.println("Joueur : " + nomJoueur);
                lesJoueurs[i - 1] = nomJoueur;
            }
            if (!lesJoueurs[0].equals(lesJoueurs[1])) break;
            else System.out.println("Les noms doivent être différents !");
        }
        return lesJoueurs;
    }

    public void etatJeu() {
        Tas jeu = construcJeu.getLesTas();
        int jeuSize = jeu.getTailleTas();
        for (int i = 0; i < jeuSize; i++) {
            System.out.print(i + 1 + "   ");
            for (int j = 0; j < jeu.getValeurTas(i); j++) {
                if (j == 0) {
                    for (int k = jeuSize - 1; k > i; k--) {
                        System.out.print("  ");
                    }
                }
                System.out.print(" |");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void baseJeu() {
        System.out.println("Forme du coup : m n");
        System.out.println("m : ligne du tas");
        System.out.println("n : nombre d'allumette à retirer");
        System.out.println("Bon jeu !");
        entrerPourContinuer();
    }


    public String coupAjouer(String joueurCourant) {
        String coup;
        System.out.println("Au tour de : " + joueurCourant);
        System.out.print("Votre coup : ");
        coup = in.next();
        coup += " " + in.next();
        return coup;
    }

    public boolean finDePartie(String gagnant) {
        String j1 = this.ctrlJeu.getLesJoueurs()[0].getNom();
        String j2 = this.ctrlJeu.getLesJoueurs()[1].getNom();
        int scoreJ1 = this.ctrlJeu.getLesJoueurs()[0].getNbPartieGagnee();
        int scoreJ2 = this.ctrlJeu.getLesJoueurs()[1].getNbPartieGagnee();

        System.out.println("Le gagnant est : " + gagnant);
        System.out.println("*******Récapitulatif*******");
        System.out.println(j1 + " : " + scoreJ1);
        System.out.println(j2 + " : " + scoreJ2);
        System.out.println("****************************");
        System.out.println("Voulez-vous rejouer ? (o/n)");
        do {
            String rejouer = in.next();
            if (rejouer.equals("n")) break;
            else return true;
        } while (true);
        return false;
    }

    public void messageErreur(String s) {
        System.out.println();
        System.out.println("*****************************************");
        System.out.println(s);
        System.out.println("*****************************************");
        entrerPourContinuer();
    }

    private void entrerPourContinuer() {
        System.out.println("Entrer pour continuer");
        in.nextLine();
        while (true) {
            if (in.hasNextLine()) break;
        }
    }
}
