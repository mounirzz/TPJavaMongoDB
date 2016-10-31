package fr.miage.restaurant;

import org.bson.Document;

import java.util.Date;

/**
 * Created by mathieumourot on 26/10/2016.
 */
public class Notation {
    Date date;
    String grade;
    int score;

    public Notation(Document d) {
        date = (Date) d.get("date");
        grade = d.get("grade").toString();
        score = Integer.parseInt(d.get("score").toString());
    }

    @Override
    public String toString() {
        return "Notation{" +
                "date=" + date +
                ", grade=" + grade +
                ", score=" + score +
                '}';
    }
}
