package modele;

import exceptions.CoupJouerInvalideException;

import java.util.Arrays;

public class Tas implements Cloneable {
    private static int NB_MAX_ALLUMETTES;
    private int[] tas;

    public Tas(int nbTas, int nbMaxAllumettes) {
        if (nbMaxAllumettes == 0) NB_MAX_ALLUMETTES = 3;
        else NB_MAX_ALLUMETTES = nbMaxAllumettes;
        this.tas = new int[nbTas];
        for (int i = 0; i < nbTas; i++) {
            this.tas[i] = 2 * i + 1;
        }
    }

    private void setTas(int numTas, int nbAlu) {
        this.tas[numTas] -= nbAlu;
    }

    public int getTailleTas() {
        return this.tas.length;
    }

    public int getValeurTas(int nbTas) {
        return this.tas[nbTas];
    }

    public void changerTas(Coup cp) throws CoupJouerInvalideException {
        int numTas = cp.getNumTas() - 1;//-1 => index du tableau commence à 0
        int nbAlu = cp.getNbAlu();
        if (nbAlu > NB_MAX_ALLUMETTES)
            throw new CoupJouerInvalideException("Nombre maximun d'allumette : " + NB_MAX_ALLUMETTES + " Votre coup : " + nbAlu);
        if (bonChangementTas(numTas, nbAlu)) {
            this.setTas(numTas, nbAlu);
        }
    }

    private boolean bonChangementTas(int numTas, int nbAllu) throws CoupJouerInvalideException {
        if (this.tas.length <= numTas)
            throw new CoupJouerInvalideException("Le coup joué choisi n'est pas valide(numéro tas) : " + numTas + 1);
        if (this.tas[numTas] < nbAllu)
            throw new CoupJouerInvalideException("Le coup joué choisi n'est pas valide(nombre d'alluettes) : " + nbAllu);
        return true;
    }

    public boolean tasVide() {
        return sommeAllumette() == 0;
    }

    private int sommeAllumette() {
        /*int somme=0;
        for(int i=0;i<this.tas.length;i++){
            somme+=this.tas[i];
        }
         return somme;*/
        return Arrays.stream(this.tas).sum();// calcule la somme du tableau
    }

    public Tas clone() { // redéfinition de la méthode clone en implémentant l'interface Cloneable
        Tas o = null;
        try {
            o = (Tas) super.clone();
        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace(System.err);
        }
        assert o != null;
        o.tas = o.tas.clone();
        return o;
    }
}
