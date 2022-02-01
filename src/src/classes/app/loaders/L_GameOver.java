package classes.app.loaders;

import classes.app.controllers.GameOverController;
import classes.content.Partie;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class L_GameOver extends AnchorPane {

    public GameOverController controller;
    private Partie partie;

    public L_GameOver(GameOverController controller, Partie p) {
        try {
            URL fxmlURL = Loader.class.getResource("/views/GameOver.fxml");
            String css = Objects.requireNonNull(Loader.class.getResource("/css/GameOver.css")).toExternalForm();
            this.getStylesheets().add(css);

            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);

            controller.setPartie(p);
            this.controller = controller;
            partie = p;

            //Chaque fenêtre à un "fx:root" qui sera remplacé par l'instance crée
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);

            try {
                //Ceci représente la fenêtre chargée
                fxmlLoader.load();
            } catch (IOException e) {
                System.out.println("Chargement de la grille : ");
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ClickConfirmationToGrille(ActionEvent actionEvent) {
        controller.getPartie().getLeaderboard().addScore(controller.getPartie().getPoints());
        controller.getPartie().getLeaderboard().sauvegarder();
        controller.ClickConfirmationToGrille(actionEvent, partie);

    }

    public void ClickConfirmationToMenu(ActionEvent actionEvent) {
        controller.ClickConfirmationToMenu(actionEvent);
        controller.getPartie().getLeaderboard().addScore(controller.getPartie().getPoints());
        controller.getPartie().getLeaderboard().sauvegarder();
    }

}
