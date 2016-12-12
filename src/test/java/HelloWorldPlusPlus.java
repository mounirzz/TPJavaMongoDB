import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import fr.miage.restaurant.Adresse;
import fr.miage.restaurant.Notation;
import fr.miage.restaurant.Restaurant;
import org.bson.Document;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertTrue;


/**
 * Created by mathieumourot on 24/10/2016.
 */
public class HelloWorldPlusPlus {
    static MongoClient mc ;
    static MongoDatabase mdb ;
    static MongoCollection collection;

    @BeforeClass
    public static void init(){
        mc = new MongoClient("localhost",27017);
        mdb = mc.getDatabase("test");
        collection = mdb.getCollection("restaurants");
    }

    @Test
    public void testConnection(){
        DB db = mc.getDB("test");
        Set<String> collections = db.getCollectionNames();
        assertTrue(collections.contains("restaurants"));
    }

    @Test
    public void testParcoursDataSet(){
        FindIterable<Document> res = collection.find();
        for(Document d : res) {
            Restaurant r = new Restaurant(d);
            System.out.println(r);
        }
        assertTrue(true);
    }

    @Test
    public void testSearchWithWhereClause() {
        BasicDBObject where = new BasicDBObject();
        where.append("restaurant_id","40361618");
        FindIterable<Document> res = collection.find(where);
        Document d = res.first();
        Restaurant r = new Restaurant(res.first());
        System.out.println(new Restaurant(d));
        assertTrue(r.getRestaurant_id() == 40361618);
    }

    @Test
    public void testSearchWithInClause(){
        // Les restaurants ayant au un 8 ou un 4:
        List<Integer> list = new ArrayList<Integer>();
        list.add(8);
        list.add(4);
        BasicDBObject inQuery = new BasicDBObject("grades.score", new BasicDBObject("$in",list));
        System.out.println("inQuery = " + inQuery);
        FindIterable<Document> res = collection.find(inQuery);
        for (Document d : res) {
            Restaurant r = new Restaurant(d);
            System.out.println("r = " + r);
        }
    }

    @Test
    public void searchWithRegex() {
        BasicDBObject regexwhere = new BasicDBObject("$regex","sushi");
        regexwhere.append("$options","i");
        BasicDBObject whereClause = new BasicDBObject("name", regexwhere);
        FindIterable<Document> res = collection.find(whereClause);
        for (Document d: res) {
            Restaurant r = new Restaurant(d);
            System.out.println("r = " + r);
        }
    }

    @Test
    public void testSearchVille() {
        DistinctIterable<String> res = collection.distinct("borough", String.class);
        for (String s : res ) {
            System.out.println("s = " + s);
        }
        assertTrue(res.first().equals("Bronx"));
    }

    @Test
    public void testInsert(){
        Restaurant r = new Restaurant();
        Adresse a = new Adresse();
        a.setBatiment("31");
        a.setRue("Rue de la Sapinière");
        a.setCodepost("54520");
        ArrayList<Double> coords = new ArrayList<Double>();
        coords.add(48.6941124);
        coords.add(6.127396900000008);
        a.setCoordonnees(coords);
        r.setAdresse(a);
        r.setCuisine("Fast food");
        r.setNom("Two Golden Arches");
        r.setVille("Laxou");
        Notation n = new Notation();
        n.setDate(new Date());
        n.setGrade("A");
        n.setScore(12);
        ArrayList<Notation> notes = new ArrayList<Notation>();
        notes.add(n);
        r.setNotes(notes);
        r.setRestaurant_id(60000000);
        Document document = r.toDocument();

        collection.insertOne(document);
        BasicDBObject query = new BasicDBObject("borough","Laxou");

        FindIterable<Document> res = collection.find(query);
        int i = 0;
        for (Document d: res) {
            i++;
            Restaurant rest = new Restaurant(d);
            System.out.println("restaurant = " + rest);
        }
        System.out.println("i = " + i);

        assertTrue(i==1);
    }

    @Test
    public void testUpdate(){
        BasicDBObject query = new BasicDBObject("borough","Laxou");

        FindIterable<Document> res = collection.find(query);
        for (Document d: res) {
            Restaurant rest = new Restaurant(d);
            System.out.println("rest = " + rest);
            rest.setNom("McDonald");
            System.out.println("rest = " + rest);
            collection.updateMany(query,new Document("$set" , rest.toDocument()));
        }

        res = collection.find(query);
        int i = 0;
        for (Document d: res) {
            i++;
            Restaurant rest = new Restaurant(d);
            System.out.println("restaurant = " + rest);
        }
        System.out.println("i = " + i);

        assertTrue(i==1);

    }

    @Test
    public void testDelete(){
        collection.deleteMany(new BasicDBObject("borough","Laxou"));
        BasicDBObject query = new BasicDBObject("borough","Laxou");

        FindIterable<Document> res = collection.find(query);
        int i = 0;
        for (Document d: res) {
            i++;
            Restaurant rest = new Restaurant(d);
            System.out.println("restaurant = " + rest);
        }

        assertTrue(i==0);
    }

}
