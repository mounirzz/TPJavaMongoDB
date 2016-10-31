import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import fr.miage.restaurant.Restaurant;
import org.bson.Document;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


/**
 * Created by mathieumourot on 24/10/2016.
 */
public class HelloWorld {
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
        FindIterable<Document> res = collection.find();
        for(Document d : res) {
            Restaurant r = new Restaurant(d);
            System.out.println("restaurant  = " + r);
        }
        Document d = res.first();
        assertTrue(true);
    }

    @Test
    public void testInsert(){
        assertTrue(true);
    }

}
