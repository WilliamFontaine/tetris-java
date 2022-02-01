package classes.content.pieces;

import classes.content.Partie;
import classes.content.enums.PieceType;
import classes.content.grille.GrilleBase;
import classes.content.pieces.point.Point;

/**
 * Piece de type "LGauche"
 */
public class PieceLGauche extends PieceBase {

    /**
     * Constructeur d'une pièce LGauche
     *
     * @param type   type de la pièce
     * @param x      position de la case centrale sur l'axe des abscisses
     * @param grille grille de jeu
     * @param partie partie possédant les attributs nécessaires
     */
    public PieceLGauche(PieceType type, int x, GrilleBase grille, Partie partie) {
        super(type, grille, partie);
        for (int i = 0; i < numPoints - 1; i++) {
            this.points[i] = new Point(x, i);
        }
        this.points[numPoints - 1] = new Point(x - 1, 2);
    }
}