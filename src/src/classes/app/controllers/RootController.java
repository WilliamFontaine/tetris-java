package classes.app.controllers;

import classes.content.Partie;

public abstract class RootController {
    protected Partie partie;

    public Partie getPartie() {
        return partie;
    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }
}
