package classes.app.controllers;

import classes.app.loaders.Loader;
import classes.content.Partie;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GameOverController extends RootController {

    private final Loader loader;

    public GameOverController(Loader l) {
        loader = l;
    }

    public void ClickConfirmationToGrille(ActionEvent actionEvent, Partie partie) {
        partie.getGrille().clearGrille();
        Button b = (Button) actionEvent.getSource();
        Stage t = (Stage) b.getScene().getWindow();

        t.close();
        Stage grille = loader.getStage("Menu");
        loader.load(grille, "Grille", true);

    }

    public void ClickConfirmationToMenu(ActionEvent actionEvent) {
        partie.getGrille().clearGrille();
        Button b = (Button) actionEvent.getSource();
        Stage t = (Stage) b.getScene().getWindow();

        t.close();
        Stage menu = loader.getStage("Menu");
        loader.load(menu, "Menu", true);
    }
}
