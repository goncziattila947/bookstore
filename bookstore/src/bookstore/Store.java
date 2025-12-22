package bookstore;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Store implements Serializable {

    // Attributes
    private ArrayList<Storage> books;


    // Constructor
    public Store() {
        readFromFile("store.dat");
    }

    
    // Getters
    public ArrayList<Storage> getStore() { return books; }



    // File Methods
    public void writeToFile(String filename) {
	    try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename))) {
	    	os.writeObject(books);
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	}

    public void readFromFile(String filename) {
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(filename))) {
	        books = (ArrayList<Storage>) is.readObject();
	    }
	    catch (Exception e) {
	        books = new ArrayList<>();
	    }
    }



    // Methods
    public void addStorage(Storage storage) {
        books.add(storage);
    }

}