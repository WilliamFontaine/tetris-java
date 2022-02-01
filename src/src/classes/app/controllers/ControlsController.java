package classes.app.controllers;

import classes.app.loaders.Loader;
import classes.paramètres.Controles;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ControlsController extends RootController {

    private final Loader loader;
    private final Controles ctrls;
    public String InputDepGauche;
    public String InputDepDroite;
    public String InputRotGauche;
    public String InputRotDroite;
    public String InputAcc;
    boolean changedepdroite = false;
    boolean changedepgauche = false;
    boolean changerotdroite = false;
    boolean changerotgauche = false;
    boolean changeacc = false;

    public ControlsController(Loader l) {
        loader = l;
        ctrls = new Controles();
    }

    /**
     * Se déclenche lorsque l'on clique
     *
     * @param actionEvent Action du bouton
     */
    public void ClickControlsInputDepGauche(ActionEvent actionEvent, Button Inputdepgauche) {

        resetAll();
        Inputdepgauche.setText("Press key");
        changedepgauche = true;
    }

    public void ClickControlsInputDepDroite(ActionEvent actionEvent, Button Inputdepdroite) {


        resetAll();
        Inputdepdroite.setText("Press key");
        changedepdroite = true;
    }

    public void ClickControlsInputRotDroite(ActionEvent actionEvent, Button Inputrotdroite) {


        resetAll();
        Inputrotdroite.setText("Press key");
        changerotdroite = true;
    }

    public void ClickControlsInputRotGauche(ActionEvent actionEvent, Button Inputrotgauche) {

        resetAll();
        Inputrotgauche.setText("Press key");
        changerotgauche = true;
    }

    public void ClickControlsInputAcceleration(ActionEvent actionEvent, Button Inputacc) {

        resetAll();
        Inputacc.setText("Press key");
        changeacc = true;
    }

    public void ClickAnnulation(ActionEvent actionEvent) {
        Button b = (Button) actionEvent.getSource();
        Stage t = (Stage) b.getScene().getWindow();

        t.setTitle("Options");
        ctrls.initialiser();
        loader.load(t, "Options", true);
    }

    public void ClickReinitialisation(ActionEvent actionEvent) {
        ctrls.reinitialiser();
        Button b = (Button) actionEvent.getSource();
        Stage t = (Stage) b.getScene().getWindow();
        loader.load(t, "Controls", true);
    }

    public void ClickValidation(ActionEvent actionEvent) {

        ctrls.valider();
        Button b = (Button) actionEvent.getSource();
        Stage t = (Stage) b.getScene().getWindow();

        loader.load(t, "Options", true);
    }

    private void resetAll() {
        changedepdroite = false;
        changedepgauche = false;
        changerotdroite = false;
        changerotgauche = false;
        changeacc = false;
    }

    public void ControlChange(KeyEvent event, Button Inputdepdroite, Button Inputdepgauche, Button Inputrotdroite, Button Inputrotgauche, Button Inputacc) {
        if (changedepdroite) {
            Inputdepdroite.setText(event.getCode().getName());
            ctrls.setDepdroite(event.getCode().getName());
            changedepdroite = false;
        } else if (changedepgauche) {
            Inputdepgauche.setText(event.getCode().getName());
            ctrls.setDepgauche(event.getCode().getName());
            changedepgauche = false;

        } else if (changerotdroite) {
            Inputrotdroite.setText(event.getCode().getName());
            ctrls.setRotdroite(event.getCode().getName());
            changerotdroite = false;
        } else if (changerotgauche) {
            ctrls.setRotgauche(event.getCode().getName());
            Inputrotgauche.setText(event.getCode().getName());
            changerotgauche = false;
        } else if (changeacc) {
            Inputacc.setText(event.getCode().getName());
            ctrls.setAcceleration(event.getCode().getName());
            changeacc = false;
        }
    }
}
