package modele;

public class Coup {
    private int numTas;
    private int nbAlu;

    public Coup(int numTas, int nbAlu) {
        this.numTas = numTas;
        this.nbAlu = nbAlu;
    }

    public int getNumTas() {
        return numTas;
    }

    public int getNbAlu() {
        return nbAlu;
    }
}
