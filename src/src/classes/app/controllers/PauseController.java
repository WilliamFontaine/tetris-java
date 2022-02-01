package classes.app.controllers;

import classes.app.loaders.Loader;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PauseController extends RootController {

    private final Loader loader;

    public PauseController(Loader l) {
        loader = l;
    }

    public void ClickPauseToConfirmation(ActionEvent actionEvent) {
        loader.loadwithStage("Confirmation", false);
    }

    public void ClickPauseToGrille(ActionEvent actionEvent) {
        Button b = (Button) actionEvent.getSource();
        Stage t = (Stage) b.getScene().getWindow();

        partie.getGrille().paused = false;

        t.close();
    }
}
