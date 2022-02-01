package classes.app.controllers;

import classes.app.loaders.Loader;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ConfirmationController extends RootController {

    private final Loader loader;

    public ConfirmationController(Loader l) {
        loader = l;
    }

    /**
     * Se déclenche lorsque le bouton "Oui" est activé
     *
     * @param actionEvent Action du bouton
     */
    public void ClickConfirmationToMenu(ActionEvent actionEvent, Thread play) {
        Button b = (Button) actionEvent.getSource();
        Stage t = (Stage) b.getScene().getWindow();

        Stage base = loader.getStage("Menu");

        Stage pause = loader.getStage("Pause");

        pause.close();
        t.close();

        base.setTitle("Menu");
        loader.load(base, "Menu", true);
        play.interrupt();
    }

    /**
     * Se déclenche lorsque le bouton "Non" est activé
     *
     * @param actionEvent Action du bouton
     */
    public void ClickConfirmationToPause(ActionEvent actionEvent) {
        Button b = (Button) actionEvent.getSource();
        Stage t = (Stage) b.getScene().getWindow();

        t.close();
    }
}
