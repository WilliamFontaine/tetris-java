package classes.app.loaders;

import classes.app.controllers.ControlsController;
import classes.content.Partie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

/**
 * Cette classe hérite de VBox car le "fx:root" du fichier "Controls.fxml" est de type "VBox"
 */
public class L_Controls extends VBox {

    public ControlsController controller;

    @FXML
    private Button Inputdepdroite;

    @FXML
    private Button Inputdepgauche;

    @FXML
    private Button Inputrotdroite;

    @FXML
    private Button Inputrotgauche;

    @FXML
    private Button Inputacc;

    /**
     * Constructeur de la structure des controles
     */
    public L_Controls(ControlsController controller, Partie p) {
        try {
            URL fxmlURL = Loader.class.getResource("/views/Controls.fxml");
            String css = Objects.requireNonNull(Loader.class.getResource("/css/Controls.css")).toExternalForm();
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
                System.out.println("Chargement des controles : ");
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialisation() {

    }

    public void ClickControlsInputDepGauche(ActionEvent actionEvent) {
        controller.ClickControlsInputDepGauche(actionEvent, Inputdepgauche);
    }

    public void ClickControlsInputDepDroite(ActionEvent actionEvent) {
        controller.ClickControlsInputDepDroite(actionEvent, Inputdepdroite);
    }

    public void ClickControlsInputRotDroite(ActionEvent actionEvent) {
        controller.ClickControlsInputRotDroite(actionEvent, Inputrotdroite);
    }

    public void ClickControlsInputRotGauche(ActionEvent actionEvent) {
        controller.ClickControlsInputRotGauche(actionEvent, Inputrotgauche);
    }

    public void ClickControlsInputAcceleration(ActionEvent actionEvent) {
        controller.ClickControlsInputAcceleration(actionEvent, Inputacc);
    }

    public void ClickAnnulation(ActionEvent actionEvent) {
        controller.ClickAnnulation(actionEvent);
    }

    public void ClickReinitialisation(ActionEvent actionEvent) {
        controller.ClickReinitialisation(actionEvent);
    }

    public void ClickValidation(ActionEvent actionEvent) {
        controller.ClickValidation(actionEvent);
    }

    public void ControlChange(KeyEvent event) {
        controller.ControlChange(event, Inputdepdroite, Inputdepgauche, Inputrotdroite, Inputrotgauche, Inputacc);
    }
}
