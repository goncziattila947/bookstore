package bookstore;

public class Book {

    // Attributes
    protected String author;
    protected String title;
    protected String topic;
    protected int year;
    protected int price;


    // Constructor
    public Book(String title, String author, String topic, int year, int price) {
        this.author = author;
        this.title = title;
        this.topic = topic;
        this.year = year;
        this.price = price;
    }


    // Getters
    
    public String getAuthor() { return author; }

    public String getTitle() { return title; }

    public String getTopic() { return topic; }

    public int getYear() { return year; }

    public int getPrice() { return price; }



    // Setters

    public void setAuthor(String author) { this.author = author; }

    public void setTitle(String title) { this.title = title; }

    public void setTopic(String topic) { this.topic = topic; }

    public void setYear(int year) { this.year = year; }

    public void setPrice(int price) {this.price = price; }
}