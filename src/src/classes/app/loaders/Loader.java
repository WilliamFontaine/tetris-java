package classes.app.loaders;

import classes.app.controllers.*;
import classes.app.dessinateurs.DessinateurBasique;
import classes.app.observateurs.ObservateurAbstrait;
import classes.content.Partie;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Loader {

    private final Partie partie;
    private final Map<String, Stage> loadedStage = new HashMap<>();
    private final ObservateurAbstrait observateur;
    public String currentWindow;
    public FXMLLoader fxmlLoader;
    private Map<String, String> vues = new HashMap<>();

    public Loader(Partie p, ObservateurAbstrait observateur) {
        this.partie = p;
        this.observateur = observateur;
        partie.attach(observateur);
    }

    /**
     * Charger une nouvelle fenetre sur un stage existant
     *
     * @param stage   Nom du stage sur lequel la fenetre va s'afficher
     * @param root    Nom de la fenetre à afficher
     * @param sizable Définit si la taille de la fenetre sera modifiable ou non
     */
    public void load(Stage stage, String root, boolean sizable) {
        currentWindow = root;
        Scene s = setWRoot(root);
        stage.setScene(s);
        stage.setResizable(sizable);
        stage.show();
    }

    /**
     * Charger une nouvelle fenetre sur un nouveau stage
     *
     * @param root    Nom de la fenetre à afficher
     * @param sizable Définit si la taille de la fenetre sera modifiable ou non
     */
    public void loadwithStage(String root, boolean sizable) {
        currentWindow = root;
        Scene s = setWRoot(root);
        Stage stage = new Stage();
        stage.setScene(s);
        stage.setResizable(sizable);
        addStage(root, stage);
        stage.show();

        /*try {

            //Choix du template


            //Création et ajout du stage au stages chargés
            Stage stage = new Stage();
            addStage(root, stage);

            //Chargement de la fenetre et de son controleur
            fxmlLoader = new FXMLLoader(Loader.class.getResource(Objects.requireNonNull(template)));
            Parent p = fxmlLoader.load();

            bContr = fxmlLoader.getController();



            bContr.setPartie(partie);


            //Création de la scène
            Scene scene = new Scene(p);

            template = template.replace(".fxml", "").replace("/views/", "");

            //Gestion du css
            if (Loader.class.getResource("/css/" + template + ".css") != null) {
                String css = Objects.requireNonNull(Loader.class.getResource("/css/" + template + ".css")).toExternalForm();
                scene.getStylesheets().add(css);
            }

            //Affichage de la fenetre
            stage.setScene(scene);
            stage.setTitle(root);
            stage.setResizable(sizable);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public Scene setWRoot(String root) {
        currentWindow = root;

        Pane pane = null;

        String template = "";
        Base_Mother controller;
        try {
            template = switch (root) {
                case "Menu", "Controls", "Grille" -> getCheminVue("Base");
                case "Pause", "Confirmation", "GameOver" -> getCheminVue("BasePause");
                case "Options", "LeaderBoard" -> getCheminVue("BaseOptLead");
                default -> null;
            };
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        assert template != null;
        if (template.equals(getCheminVue("Base"))) {
            controller = new BaseController();
        } else if (template.equals(getCheminVue("BasePause"))) {
            controller = new BasePauseController();
        } else if (template.equals(getCheminVue("BaseOptLead"))) {
            controller = new BaseOptLeadController(this);
        } else {
            controller = null;
        }


        fxmlLoader = new FXMLLoader(Loader.class.getResource(Objects.requireNonNull(template)));
        RootController controller_window;

        assert controller != null;
        controller.setPartie(partie);
        fxmlLoader.setController(controller);
        try {
            Parent p = fxmlLoader.load();
            Scene s = new Scene(p);
            template = template.replace(".fxml", "").replace("/views/", "");
            if (Loader.class.getResource("/css/" + template + ".css") != null) {
                String css = Objects.requireNonNull(Loader.class.getResource("/css/" + template + ".css")).toExternalForm();
                s.getStylesheets().add(css);
            }
            Thread boucle = new Thread(partie.getGrille());
            switch (root) {
                case "Menu" -> {
                    controller_window = new MenuController(this);
                    pane = new L_Menu((MenuController) controller_window, partie);
                }
                case "Grille" -> {
                    controller_window = new GrilleController(this);
                    pane = new L_Grille((GrilleController) controller_window, partie, observateur, new DessinateurBasique(null), boucle);
                }
                case "Options" -> {
                    controller_window = new OptionsController(partie, this);
                    assert controller instanceof BaseOptLeadController;
                    pane = new L_Options((OptionsController) controller_window, partie, (BaseOptLeadController) controller);
                }
                case "LeaderBoard" -> {
                    controller_window = new LeaderBoardController();
                    assert controller instanceof BaseOptLeadController;
                    pane = new L_Leaderboard((LeaderBoardController) controller_window, partie, (BaseOptLeadController) controller);
                }
                case "Controls" -> {
                    controller_window = new ControlsController(this);
                    pane = new L_Controls((ControlsController) controller_window, partie);
                }
                case "Pause" -> {
                    controller_window = new PauseController(this);
                    pane = new L_Pause((PauseController) controller_window, partie, boucle);
                }
                case "Confirmation" -> {
                    controller_window = new ConfirmationController(this);
                    pane = new L_Confirmation((ConfirmationController) controller_window, partie, boucle);
                }
                case "GameOver" -> {
                    controller_window = new GameOverController(this);
                    pane = new L_GameOver((GameOverController) controller_window, partie);
                }
                default -> controller_window = null;
            }
            controller.setChild(pane);
            return s;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Ajout d'un stage dans le tableau des stages chargés
     *
     * @param nom   Nom du stage à ajouter
     * @param stage Stage à ajouter
     */
    private void addStage(String nom, Stage stage) {
        loadedStage.put(nom, stage);
    }

    /**
     * @param nom Nom du stage chargé
     * @return Le stage chargé correspondant au nom
     * @throws NoSuchElementException Renvoyée si le stage n'est pas chargé
     */
    public Stage getStage(String nom) throws NoSuchElementException {
        for (Map.Entry<String, Stage> e : loadedStage.entrySet()) {
            if (e.getKey().equals(nom)) {
                return e.getValue();
            }
        }
        throw new NoSuchElementException("Le stage \" " + nom + " \" n'est pas chargé");
    }

    /**
     * Permet d'obtenir le chemin d'une vue
     *
     * @param nom Nom de la vue
     * @return Le chemin de la vue
     * @throws NoSuchElementException Renvoyée si la vue n'existe pas
     */
    private String getCheminVue(String nom) throws NoSuchElementException {
        for (Map.Entry<String, String> e : vues.entrySet()) {
            if (e.getKey().equals(nom)) {
                return e.getValue();
            }
        }
        throw new NoSuchElementException("Le nom \" " + nom + " \" n'existe pas");
    }

    /**
     * Chargeur des vues
     */
    public void loadVuesAndRoot() {
        vues = new HashMap<>();
        vues.put("Base", "/views/Base.fxml");
        vues.put("BasePause", "/views/BasePause.fxml");
        vues.put("BaseOptLead", "/views/BaseOptLead.fxml");
    }
}
