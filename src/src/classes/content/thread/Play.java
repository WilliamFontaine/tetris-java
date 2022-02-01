package classes.content.thread;

import classes.content.Partie;
import classes.content.enums.MoveType;
import classes.content.grille.Grille;

/**
 * Classe possédant la matérialisation de la boucle de jeu.
 */
public class Play extends Grille implements Runnable {

    public boolean paused = false;

    /**
     * Constructeur d'un Play
     *
     * @param nbLignes   nombre de lignes de la grille
     * @param nbColonnes nombre de colonnes de la grille
     * @param p          partie possédant les attributs nécessaires
     */
    public Play(int nbLignes, int nbColonnes, Partie p) {
        super(nbLignes, nbColonnes, p);
    }

    @Override
    public void run() {
        float i = 500;
        addNewPiece();
        update();
        while (currentPiece.canGoBas()) {
            do {
                if (!paused) {
                    try {
                        //noinspection BusyWait
                        CustomThread.sleep((long) i);
                        if (i > 75)
                            i -= 0.5;
                    } catch (InterruptedException e) {
                        System.out.println("Arret");
                        return;
                    }
                    movePiece(MoveType.DESCENDRE);
                    update();
                }

            } while (currentPiece.canGoBas());
            this.type = 0;
            currentPiece.dessiner();
            clearFullLine();
            addNewPiece();
            update();
        }
        gameOver();
    }
}
