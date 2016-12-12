package fr.miage.restaurant;

import com.mongodb.BasicDBList;
import org.bson.Document;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by mathieumourot on 26/10/2016.
 */
public class Restaurant {
    String _id;
    Adresse adresse;
    String ville;
    String cuisine;
    List<Notation> notes;
    String nom;
    int restaurant_id;

    public Restaurant(Document d){
        _id = d.get("_id").toString();
        ville = d.get("borough").toString();
        cuisine = d.get("cuisine").toString();
        nom = d.get("name").toString();
        restaurant_id = Integer.parseInt(d.get("restaurant_id").toString());

        adresse = new Adresse((Document) d.get("address"));

        notes = new LinkedList<Notation>();
        List<Document> notations = (ArrayList<Document>) d.get("grades");
        for (Document grade : notations) {
            if (grade.get("score") == null) {break;}
            notes.add(new Notation(grade));
        }
    }

    public Restaurant(){}

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public List<Notation> getNotes() {
        return notes;
    }

    public void setNotes(List<Notation> notes) {
        this.notes = notes;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "_id=" + _id +
                ", adresse=" + adresse +
                ", ville='" + ville + '\'' +
                ", cuisine='" + cuisine + '\'' +
                ", notes=" + notes +
                ", nom='" + nom + '\'' +
                ", restaurant_id=" + restaurant_id +
                '}';
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }
 
    public Document toDocument() {
        Document db = new Document();
        db.put("address", adresse.toDocument());
        db.put("borough", ville );
        db.put("cuisine", cuisine );
        BasicDBList dbnotes = new BasicDBList();
        for (Notation n : notes) {
            dbnotes.add(n.toDocument());
        }
        db.put("grades",dbnotes);
        db.put("name", nom);
        db.put("restaurant_id", restaurant_id);
        return db;
    }
}
