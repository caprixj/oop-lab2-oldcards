package org.example.model;

public class OldCard {
    private String Thema;
    private org.example.model.Type Type;
    private boolean Sent;
    private String Country;
    private int Year;
    private String Author;
    private org.example.model.Valuable Valuable;

    public OldCard() {
    }

    public OldCard(String thema, org.example.model.Type type, boolean Sent, String country, int year, String author, org.example.model.Valuable valuable) {
        Thema = thema;
        Type = type;
        this.Sent = Sent;
        Country = country;
        Year = year;
        Author = author;
        Valuable = valuable;
    }

    public String getThema() {
        return Thema;
    }

    public void setThema(String thema) {
        Thema = thema;
    }

    public org.example.model.Type getType() {
        return Type;
    }

    public void setType(org.example.model.Type type) {
        Type = type;
    }

    public boolean isSent() {
        return Sent;
    }

    public void setSent(boolean sent) {
        this.Sent = sent;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public org.example.model.Valuable getValuable() {
        return Valuable;
    }

    public void setValuable(org.example.model.Valuable valuable) {
        Valuable = valuable;
    }

    @Override
    public String toString() {
        return "OldCard {\n" +
                "\tThema = " + Thema + '\n' +
                "\tType = " + Type + '\n' +
                "\tSent = " + Sent + '\n' +
                "\tCountry = " + Country + '\n' +
                "\tYear = " + Year + '\n' +
                "\tAuthor = " + Author + '\n' +
                "\tValuable = " + Valuable + '\n' +
                "}\n";
    }
}

