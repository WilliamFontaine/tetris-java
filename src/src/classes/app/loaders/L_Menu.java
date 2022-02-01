package classes.app.loaders;

import classes.app.controllers.MenuController;
import classes.content.Partie;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

/**
 * Cette classe hérite de AnchorPane car le "fx:root" du fichier "Menu.fxml" est de type "AnchorPane"
 */
public class L_Menu extends AnchorPane {

    private MenuController controller;

    /**
     * Constructeur de la structure du menu
     */
    public L_Menu(MenuController controller, Partie p) {
        try {
            URL fxmlURL = Loader.class.getResource("/views/Menu.fxml");
            String css = Objects.requireNonNull(Loader.class.getResource("/css/Menu.css")).toExternalForm();
            this.getStylesheets().add(css);
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);

            this.controller = controller;
            this.controller.setPartie(p);


            //Chaque fenêtre à un "fx:root" qui sera remplacé par l'instance crée
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);

            try {
                //Ceci représente la fenêtre chargée
                fxmlLoader.load();
            } catch (IOException e) {
                System.out.println("Chargement du menu : ");
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ClickMenuToOptions(ActionEvent actionEvent) {
        controller.ClickMenuToOptions(actionEvent);
    }

    public void ClickMenuToLeaderboard(ActionEvent actionEvent) {
        controller.ClickMenuToLeaderboard(actionEvent);
    }

    public void ClickMenuToPlay(ActionEvent actionEvent) {
        controller.ClickMenuToPlay(actionEvent);
    }
}
