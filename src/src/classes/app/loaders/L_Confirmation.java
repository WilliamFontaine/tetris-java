package classes.app.loaders;

import classes.app.controllers.ConfirmationController;
import classes.content.Partie;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class L_Confirmation extends AnchorPane {

    private ConfirmationController controller;
    private Thread play;

    public L_Confirmation(ConfirmationController controller, Partie p, Thread play) {
        try {
            URL fxmlURL = Loader.class.getResource("/views/Confirmation.fxml");
            String css = Objects.requireNonNull(Loader.class.getResource("/css/Confirmation.css")).toExternalForm();
            this.getStylesheets().add(css);
            this.play = play;

            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            controller.setPartie(p);
            this.controller = controller;
            //Chaque fenêtre à un "fx:root" qui sera remplacé par l'instance crée
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);

            try {
                //Ceci représente la fenêtre chargée
                fxmlLoader.load();
            } catch (IOException e) {
                System.out.println("Chargement de la confirmation : ");
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ClickConfirmationToMenu(ActionEvent actionEvent) {
        controller.ClickConfirmationToMenu(actionEvent, play);
    }

    /**
     * Se déclenche lorsque le bouton "Non" est activé
     *
     * @param actionEvent Action du bouton
     */
    public void ClickConfirmationToPause(ActionEvent actionEvent) {
        controller.ClickConfirmationToPause(actionEvent);
    }
}
