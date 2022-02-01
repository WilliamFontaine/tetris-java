package classes.app.controllers;

import classes.app.loaders.Loader;
import classes.content.Partie;
import classes.content.enums.MoveType;
import classes.paramètres.Controles;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GrilleController extends RootController {

    private final Loader loader;
    private final Controles ctrls;
    private Label Score;
    private Canvas canvas;

    public GrilleController(Loader l) {
        loader = l;
        ctrls = new Controles();
    }

    public void KeyPressed(KeyEvent keyEvent) {
        Platform.runLater(() -> {
            if (keyEvent.getCode() == KeyCode.getKeyCode("Esc")) {
                partie.getGrille().paused = true;
                loader.loadwithStage("Pause", false);
            }
            if (keyEvent.getCode() == KeyCode.getKeyCode(ctrls.getDepgauche())) {
                partie.getGrille().movePiece(MoveType.GAUCHE);
            }
            if (keyEvent.getCode() == KeyCode.getKeyCode(ctrls.getDepdroite())) {
                partie.getGrille().movePiece(MoveType.DROITE);
            }
            if (keyEvent.getCode() == KeyCode.getKeyCode(ctrls.getRotdroite())) {
                partie.getGrille().movePiece(MoveType.TOURNER_DROITE);
            }
            if (keyEvent.getCode() == KeyCode.getKeyCode(ctrls.getRotgauche())) {
                partie.getGrille().movePiece(MoveType.TOURNER_GAUCHE);
            }
            if (keyEvent.getCode() == KeyCode.getKeyCode(ctrls.getAcceleration())) {
                partie.getGrille().movePiece(MoveType.DESCENDRE);
                partie.setPoints(partie.getPoints() + 1);
            }
        });

    }

    public void gameOver() {
        Platform.runLater(() -> loader.loadwithStage("GameOver", false));
    }

    public Partie getPartie() {
        return partie;
    }

    public void setPartie(Partie p) {
        this.partie = p;
    }
}
