package classes.app.observateurs;

import classes.content.grille.Grille;

public class ObservateurGrille extends ObservateurAbstrait {
    private Grille value;

    @Override
    public void update() {
        value = sujet.getGrille();
        System.out.println("Changement de valeur de la grille");
        looker.dessinerGrille();
    }

    public void gameOver() {
        looker.GameOver();
    }
}
