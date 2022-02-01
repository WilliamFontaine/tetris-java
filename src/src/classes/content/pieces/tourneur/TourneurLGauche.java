package classes.content.pieces.tourneur;

import classes.content.enums.EtatRotation;
import classes.content.pieces.point.PointBase;

/**
 * Tourneur d'une pièce de type "LGauche"
 */
public class TourneurLGauche {

    /**
     * Méthode permettant de tourner un LGauche vers la gauche.
     *
     * @param pts      points d'une pièce
     * @param rotation état de la rotation de la pièce
     */
    protected void tournerGauche(PointBase[] pts, EtatRotation rotation) {
        switch (rotation) {
            case ANGLE_0 -> {
                pts[0].x--;
                pts[0].y++;
                pts[2].x++;
                pts[2].y--;
                pts[3].x += 2;
            }
            case ANGLE_90 -> {
                pts[0].x--;
                pts[0].y--;
                pts[2].x++;
                pts[2].y++;
                pts[3].y += 2;
            }
            case ANGLE_180 -> {
                pts[0].x++;
                pts[0].y--;
                pts[2].x--;
                pts[2].y++;
                pts[3].x -= 2;
            }
            case ANGLE_270 -> {
                pts[0].x++;
                pts[0].y++;
                pts[2].x--;
                pts[2].y--;
                pts[3].y -= 2;
            }
        }
    }

    /**
     * Méthode permettant de tourner un LGauche vers la droite.
     *
     * @param pts      points d'une pièce
     * @param rotation état de la rotation de la pièce
     */
    protected void tournerDroite(PointBase[] pts, EtatRotation rotation) {
        switch (rotation) {
            case ANGLE_0 -> {
                pts[0].x++;
                pts[0].y++;
                pts[2].x--;
                pts[2].y--;
                pts[3].y -= 2;
            }
            case ANGLE_90 -> {
                pts[0].x--;
                pts[0].y++;
                pts[2].x++;
                pts[2].y--;
                pts[3].x += 2;
            }
            case ANGLE_180 -> {
                pts[0].x--;
                pts[0].y--;
                pts[2].x++;
                pts[2].y++;
                pts[3].y += 2;
            }
            case ANGLE_270 -> {
                pts[0].x++;
                pts[0].y--;
                pts[2].x--;
                pts[2].y++;
                pts[3].x -= 2;
            }
        }
    }
}
