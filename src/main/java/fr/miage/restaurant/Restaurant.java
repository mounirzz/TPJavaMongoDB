package fr.miage.restaurant;

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
}
