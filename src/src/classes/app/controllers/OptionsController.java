package classes.app.controllers;

import classes.app.loaders.Loader;
import classes.content.Partie;
import classes.parametres.Difficulte;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class OptionsController extends RootController {

    private final Loader loader;
    private Partie partie;

    public OptionsController(Partie p, Loader l) {
        partie = p;
        loader = l;
    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }

    public void ClickOptionsToControls(ActionEvent actionEvent) {
        Button b = (Button) actionEvent.getSource();
        Stage t = (Stage) b.getScene().getWindow();
        loader.load(t, "Controls", false);
    }

    public void initialisation(Canvas canvas, Slider slider, Label difficulte) {
        if (Difficulte.getDifficulte() == 0) {
            slider.setValue(0);
            difficulte.setText("Facile");

            changeCanvasAndGrid(0, canvas);
        } else if (Difficulte.getDifficulte() == 1) {
            slider.setValue(1);
            difficulte.setText("Normal");

            changeCanvasAndGrid(1, canvas);
        } else if (Difficulte.getDifficulte() == 2) {
            slider.setValue(2);
            difficulte.setText("Difficile");

            changeCanvasAndGrid(2, canvas);
        } else {
            slider.setValue(3);
            difficulte.setText("Extrême");

            changeCanvasAndGrid(3, canvas);
        }
    }

    private void changeCanvasAndGrid(int difficulte, Canvas canvas) {
        canvas.setWidth(200);
        partie.setNbLignes(25 - difficulte * 5);
        canvas.setHeight(partie.getNbLignes() * 20);

        Difficulte.setDifficulte(difficulte);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setFill(Color.WHITE);

        for (int i = 20; i < canvas.getWidth(); i += 20) {
            gc.fillRect(i, 0, 0.1, canvas.getHeight());
        }
        for (int j = 20; j < canvas.getHeight(); j += 20) {
            gc.fillRect(0, j, canvas.getWidth(), 0.1);
        }
    }

    public void ClickSliderDifficulty(Slider slider, Label difficulte, Canvas canvas) {
        if (slider.getValue() < 1) {
            slider.setValue(0);
            difficulte.setText("Facile");

            changeCanvasAndGrid(0, canvas);
        } else if (slider.getValue() < 2) {
            slider.setValue(1);
            difficulte.setText("Normal");

            changeCanvasAndGrid(1, canvas);
        } else if (slider.getValue() < 3) {
            slider.setValue(2);
            difficulte.setText("Difficile");

            changeCanvasAndGrid(2, canvas);
        } else {
            slider.setValue(3);
            difficulte.setText("Extrême");

            changeCanvasAndGrid(3, canvas);
        }
    }
}
