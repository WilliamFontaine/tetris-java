package classes.parametres;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

import static java.util.Objects.requireNonNull;

public class Controles implements Serializable {
    private static String depdroite;
    private static String depgauche;
    private static String rotdroite;
    private static String rotgauche;
    private static String acceleration;

    public String getDepdroite() {
        return depdroite;
    }

    public void setDepdroite(String depdroite) {
        Controles.depdroite = depdroite;
    }

    public String getDepgauche() {
        return depgauche;
    }

    public void setDepgauche(String depgauche) {
        Controles.depgauche = depgauche;
    }

    public String getRotdroite() {
        return rotdroite;
    }

    public void setRotdroite(String rotdroite) {
        Controles.rotdroite = rotdroite;
    }

    public String getRotgauche() {
        return rotgauche;
    }

    public void setRotgauche(String rotgauche) {
        Controles.rotgauche = rotgauche;
    }

    public String getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(String acceleration) {
        Controles.acceleration = acceleration;
    }

    private void setDefault() {
        setAcceleration("S");
        setDepgauche("Q");
        setDepdroite("D");
        setRotdroite("E");
        setRotgauche("A");
    }

    public void reinitialiser() {
        setDefault();
    }

    public void initialiser() {

        URL s = requireNonNull(Controles.class.getResource("/persistance/Controls.ser"));
        try {
            File f = new File(s.toURI());
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream(f));
            setDepdroite((String) reader.readObject());
            setDepgauche((String) reader.readObject());
            setRotdroite((String) reader.readObject());
            setRotgauche((String) reader.readObject());
            setAcceleration((String) reader.readObject());
        } catch (IOException | URISyntaxException | ClassNotFoundException e2) {
            e2.printStackTrace();
        }
    }

    public void valider() {
        URL s = requireNonNull(Controles.class.getResource("/persistance/Controls.ser"));
        File f = null;
        try {
            f = new File(s.toURI());
            if (!f.exists()) {
                if (f.createNewFile())
                    setDefault();
                else
                    throw new Exception("Le fichier n'a pas été créé correctement.");
            }
            ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(f));
            writer.writeObject(getDepdroite());
            writer.writeObject(getDepgauche());
            writer.writeObject(getRotdroite());
            writer.writeObject(getRotgauche());
            writer.writeObject(getAcceleration());
        } catch (FileNotFoundException e) {
            System.out.println("Fichier \"" + f.getName() + "\" non trouvé \n" +
                    "Chemin : " + f.getPath());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
