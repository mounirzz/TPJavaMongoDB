package fr.miage.restaurant;

import com.mongodb.BasicDBList;
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

    public Adresse() {}

    public String getBatiment() {
        return batiment;
    }

    public void setBatiment(String batiment) {
        this.batiment = batiment;
    }

    public ArrayList<Double> getCoordonnees() {
        return coordonnees;
    }

    public void setCoordonnees(ArrayList<Double> coordonnees) {
        this.coordonnees = coordonnees;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCodepost() {
        return codepost;
    }

    public void setCodepost(String codepost) {
        this.codepost = codepost;
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

    public Document toDocument() {
        Document db = new Document();
        db.put("building", batiment);
        BasicDBList dbcoords = new BasicDBList();
        for (Double d: coordonnees) {
            dbcoords.add(d);
        }
        db.put("coord", dbcoords);
        db.put("street", rue );
        db.put("zipcode", codepost);
        return db;
    }
}
