package fr.miage.restaurant;

import org.bson.Document;

import java.util.ArrayList;

/**
 * Created by mathieumourot on 26/10/2016.
 */
public class Adresse {
    String batiment;
    ArrayList<Double> coordonnees;
    String rue;
    String codepost;

    public Adresse(Document d) {
        batiment = d.get("building").toString();
        coordonnees= (ArrayList<Double>) d.get("coord");
        rue = d.get("street").toString();
        codepost = d.get("zipcode").toString();
    }

    @Override
    public String toString() {
        return "Adresse{" +
                "batiment=" + batiment +
                ", coordonnees=" + coordonnees +
                ", rue='" + rue + '\'' +
                ", codepost='" + codepost + '\'' +
                '}';
    }
}
