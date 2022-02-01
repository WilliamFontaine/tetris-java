package classes.app.controllers;

import classes.app.loaders.Loader;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class BaseOptLeadController extends Base_Mother {

    private final Loader loader;

    public BaseOptLeadController(Loader l) {
        loader = l;
    }

    public void ClickOptionsToMenu(ActionEvent actionEvent) {
        Button b = (Button) actionEvent.getSource();
        Stage t = (Stage) b.getScene().getWindow();

        loader.load(t, "Menu", true);
    }
}
