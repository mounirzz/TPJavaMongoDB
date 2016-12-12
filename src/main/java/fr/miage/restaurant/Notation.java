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

    public Notation() {}

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Notation{" +
                "date=" + date +
                ", grade=" + grade +
                ", score=" + score +
                '}';
    }

    public Document toDocument() {
        Document db = new Document();
        db.put("date", date );
        db.put("grade", grade );
        db.put("score", score );
        return db;
    }
}
