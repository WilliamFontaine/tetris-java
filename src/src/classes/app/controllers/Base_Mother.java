package classes.app.controllers;

import classes.content.Partie;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public abstract class Base_Mother {

    protected Pane child;

    protected String texte;

    protected Partie partie;

    @FXML
    protected Pane basePane;

    public void setPartie(Partie partie) {
        this.partie = partie;
    }

    public Pane getChild() {
        return child;
    }

    public void setChild(Pane p) {
        if (child != null) {
            if (child.equals(p)) {
                return;
            }
        }
        basePane.getChildren().remove(child);

        child = p;
        basePane.getChildren().add(child);
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }
}
