package bookstore;

import java.io.Serializable;

public class Storage extends Book implements Serializable {

    // Attributes
    protected int shelfNumber;
    protected int rowNumber;
    protected int piece;


    // Constructor
    public Storage(String author, String title, String topic, int year, int price, int shelfNumber, int rowNumber, int piece) {
        super(author, title, topic, year, price);
        this.shelfNumber = shelfNumber;
        this.rowNumber = rowNumber;
        this.piece = piece;
    }


    // Getters

    public int getShelfNumber() { return shelfNumber; }

    public int getRowNumber() { return rowNumber; }

    public int getPiece() { return piece; }


    // Setters

    public void setShelfNumber(int shelfNumber) { this.shelfNumber = shelfNumber; }

    public void setRowNumber(int rowNumber) { this.rowNumber = rowNumber; }

    public void setPiece(int piece) { this.piece = piece; }



    // Methods
    public void addPiece(int piece) {
        this.piece += piece;
    }

}