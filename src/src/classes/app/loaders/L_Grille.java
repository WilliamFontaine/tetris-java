package classes.app.loaders;

import classes.app.controllers.GrilleController;
import classes.app.dessinateurs.Dessinateur;
import classes.app.observateurs.ObservateurAbstrait;
import classes.content.Partie;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

/**
 * Cette classe hérite de AnchorPane car le "fx:root" du fichier "Grille.fxml" est de type "AnchorPane"
 */
public class L_Grille extends AnchorPane {

    private final Thread play;
    private final ObservateurAbstrait observateur;
    private final Dessinateur dessinateur;
    public GrilleController controller;
    @FXML
    private Canvas canvas;
    @FXML
    private Label Score;
    @FXML
    private Label bestScore;

    /**
     * Constructeur de la structure de la grille de jeu
     */
    public L_Grille(GrilleController controller, Partie p, ObservateurAbstrait observateur, Dessinateur dessinateur, Thread play) {
        this.observateur = observateur;
        this.dessinateur = dessinateur;
        this.observateur.setLooker(this);
        this.observateur.setSujet(p);
        this.play = play;

        try {
            URL fxmlURL = Loader.class.getResource("/views/Grille.fxml");
            String css = Objects.requireNonNull(Loader.class.getResource("/css/Grille.css")).toExternalForm();
            this.getStylesheets().add(css);

            this.controller = controller;
            this.controller.setPartie(p);

            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);

            //Chaque fenêtre à un "fx:root" qui sera remplacé par l'instance crée
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);

            try {
                //Ceci représente la fenêtre chargée
                fxmlLoader.load();
                canvas.setFocusTraversable(true);
                this.dessinateur.setCanvas(canvas);
                chargerGrille();
                this.play.start();
            } catch (IOException e) {
                System.out.println("Chargement de la grille : ");
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void KeyPressed(KeyEvent event) {
        System.out.println("Ok");
        controller.KeyPressed(event);
    }

    public void dessinerGrille() {
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                dessinateur.dessinerGrille(controller.getPartie(), Score);
                if (controller.getPartie().getLeaderboard().getScores().size() > 0) {
                    if (controller.getPartie().getPoints() >= controller.getPartie().getLeaderboard().getScores().get(0)) {
                        Score.setText("Nouveau meilleur score !\nScore : \n" + controller.getPartie().getPoints());
                    }
                } else {
                    if (controller.getPartie().getPoints() >= 0) {
                        Score.setText("Nouveau meilleur score !\nScore : \n" + controller.getPartie().getPoints());
                    }
                }

            }
        });

    }

    public void chargerGrille() {
        dessinateur.chargerGrille(controller.getPartie());
        if (controller.getPartie().getLeaderboard().getScores().size() == 0) {
            bestScore.setText("Meilleur score : \n" + 0);
        } else {
            bestScore.setText("Meilleur score : \n" + controller.getPartie().getLeaderboard().getScores().get(0));
        }
    }

    public void GameOver() {
        controller.gameOver();
    }
}
