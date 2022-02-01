package classes.app.controllers;

import classes.app.loaders.Loader;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController extends RootController {

    private final Loader loader;

    public MenuController(Loader l) {
        loader = l;
    }

    public void ClickMenuToOptions(ActionEvent actionEvent) {
        Button b = (Button) actionEvent.getSource();
        Stage t = (Stage) b.getScene().getWindow();

        loader.load(t, "Options", true);
    }

    public void ClickMenuToLeaderboard(ActionEvent actionEvent) {
        Button b = (Button) actionEvent.getSource();
        Stage t = (Stage) b.getScene().getWindow();

        loader.load(t, "LeaderBoard", true);
    }

    public void ClickMenuToPlay(ActionEvent actionEvent) {

        Button b = (Button) actionEvent.getSource();
        Stage t = (Stage) b.getScene().getWindow();

        loader.load(t, "Grille", true);
    }
}
