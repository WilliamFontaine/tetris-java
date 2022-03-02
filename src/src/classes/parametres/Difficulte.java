package classes.parametres;

import java.io.Serializable;

public class Difficulte implements Serializable {
    private static int difficulte = 1;

    public static int getDifficulte() {
        return difficulte;
    }

    public static void setDifficulte(int difficulte) {
        Difficulte.difficulte = difficulte;
    }
}
