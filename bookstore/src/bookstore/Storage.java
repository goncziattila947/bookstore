package bookstore;

import java.io.Serializable;

public class Storage extends Book implements Serializable {

    // Attributes
    protected int shelfNumber;
    protected int piece;


    // Constructor
    public Storage(String author, String title, String topic, int year, int price, int shelfNumber, int piece) {
        super(author, title, topic, year, price);
        this.shelfNumber = shelfNumber;
        this.piece = piece;
    }


    // Getters

    public int getShelfNumber() { return shelfNumber; }

    public int getPiece() { return piece; }


    // Setters

    public void setShelfNumber(int shelfNumber) { this.shelfNumber = shelfNumber; }

    public void setPiece(int piece) { this.piece = piece; }



    // Methods
    public void addPiece(int piece) {
        this.piece += piece;
    }

}