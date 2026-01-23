package bookstore;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

public class StoreGUI extends JFrame implements ActionListener{


    // Attribute
    private Store store = new Store();


    // Constructor
    public StoreGUI() {
        setTitle("Bookstore Management System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 600);
        setLayout(new BorderLayout());

        
        JPanel menuPanel = new JPanel(new GridLayout(7, 1, 20, 20));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        String[] buttons = { "Add", "Modify", "Delete", "Search", "Order", "Upload", "Exit" };

        for (String label : buttons) {
            JButton button = new JButton(label);
            button.setFocusable(false);
            button.setFont(new Font("Comic Sans",Font.BOLD,20));
            button.addActionListener(e -> handleButton(label));
            menuPanel.add(button);
        }

        add(menuPanel, BorderLayout.CENTER);


        setLocationRelativeTo(null);
        setVisible(true);
    }



    // Button Handler
    private void handleButton(String label) {
        switch (label) {
            case "Add":
                addBook();
                break;
            case "Modify":
                modifyBook();
                break;
            case "Delete":
                deleteBook();
                break;
            case "Search":
                searchBook();
                break;
            case "Order":
                orderBook();
                break;
            case "Upload":
                uploadBooks();
                break;
            case "Exit":
                System.exit(0);
                break;
            default:
                break;
        }
    }
    


    // Methods

    private void addBook() {
        JTextField authorField = new JTextField();
        JTextField titleField = new JTextField();
        JTextField topicField = new JTextField();
        JTextField yearField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField shelfNumberField = new JTextField();
        JTextField pieceField = new JTextField();

        Object[] message = {
            "Author:", authorField,
            "Title:", titleField,
            "Topic:", topicField,
            "Year:", yearField,
            "Price:", priceField,
            "Shelf Number:", shelfNumberField,
            "Piece:", pieceField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Book", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String author = authorField.getText();
                String title = titleField.getText();
                String topic = topicField.getText();
                int year = Integer.parseInt(yearField.getText());
                int price = Integer.parseInt(priceField.getText());
                int shelfNumber = Integer.parseInt(shelfNumberField.getText());
                int piece = Integer.parseInt(pieceField.getText());

                if(year <0 || price <0 || piece <0) {
                    JOptionPane.showMessageDialog(this, "Year, Price, and Piece must be non-negative!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Storage newBook = new Storage(author, title, topic, year, price, shelfNumber, piece);
                store.addStorage(newBook);
                store.writeToFile("store.dat");

                JOptionPane.showMessageDialog(this, "Book added successfully!");
            } catch (NumberFormatException _) {
                JOptionPane.showMessageDialog(this, "Invalid input! Please check the fields.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }



    private void modifyBook() {
        String searchAuthor = JOptionPane.showInputDialog(this, "Add the author's name to modify:");
        String searchTitle = JOptionPane.showInputDialog(this, "Add the book title to modify:");
        for (Storage storage : store.getBooks()) {
            if (storage.getAuthor().equalsIgnoreCase(searchAuthor) && storage.getTitle().equalsIgnoreCase(searchTitle)) {
                JTextField authorField = new JTextField(storage.getAuthor());
                JTextField titleField = new JTextField(storage.getTitle());
                JTextField topicField = new JTextField(storage.getTopic());
                JTextField yearField = new JTextField(String.valueOf(storage.getYear()));
                JTextField priceField = new JTextField(String.valueOf(storage.getPrice()));
                JTextField shelfNumberField = new JTextField(String.valueOf(storage.getShelfNumber()));
                JTextField pieceField = new JTextField(String.valueOf(storage.getPiece()));


                Object[] message = {
                    "Author:", authorField,
                    "Title:", titleField,
                    "Topic:", topicField,
                    "Year:", yearField,
                    "Price:", priceField,
                    "Shelf Number:", shelfNumberField,
                    "Piece:", pieceField
                };

                int option = JOptionPane.showConfirmDialog(this, message, "Modify Book", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    try {
                        storage.setAuthor(authorField.getText());
                        storage.setTitle(titleField.getText());
                        storage.setTopic(topicField.getText());
                        storage.setYear(Integer.parseInt(yearField.getText()));
                        storage.setPrice(Integer.parseInt(priceField.getText()));
                        storage.setShelfNumber(Integer.parseInt(shelfNumberField.getText()));
                        storage.setPiece(Integer.parseInt(pieceField.getText()));

                        if(storage.getYear() <0 || storage.getPrice() <0 || storage.getPiece() <0) {
                            JOptionPane.showMessageDialog(this, "Year, Price, and Piece must be non-negative!", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        store.writeToFile("store.dat");

                        JOptionPane.showMessageDialog(this, "Book modified successfully!");
                    } catch (NumberFormatException _) {
                        JOptionPane.showMessageDialog(this, "Invalid input! Please check the fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "The specified book was not found.", "Error", JOptionPane.ERROR_MESSAGE);
    }




    private void deleteBook() {
        String searchAuthor = JOptionPane.showInputDialog(this, "Add the author's name to delete:");
        String searchTitle = JOptionPane.showInputDialog(this, "Add the book title to delete:");

        for (Storage storage : store.getBooks()) {
            if (storage.getAuthor().equalsIgnoreCase(searchAuthor) && storage.getTitle().equalsIgnoreCase(searchTitle)) {
                store.getBooks().remove(storage);
                store.writeToFile("store.dat");
                JOptionPane.showMessageDialog(this, "Book deleted successfully!");
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "The specified book was not found.", "Error", JOptionPane.ERROR_MESSAGE);
    }



    private void searchBook() {
        String[] options = {"Author", "Title", "Topic", "Year", "Max Price"};

        String chosen = (String) JOptionPane.showInputDialog(
                null,
                "Choose the search method:",
                "Search Book",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (chosen == null) return;

         String input = JOptionPane.showInputDialog("Search value:");
        if (input == null || input.isEmpty()) {
            return;
        }
        	
        List<Storage> results = new java.util.ArrayList<>();
        for (Storage storage : store.getBooks()) {
            switch (chosen) {
                case "Author":
                    if (storage.getAuthor().equalsIgnoreCase(input)) {
                        results.add(storage);
                    }
                    break;
                case "Title":
                    if (storage.getTitle().equalsIgnoreCase(input)) {
                        results.add(storage);
                    }
                    break;
                case "Topic":
                    if (storage.getTopic().equalsIgnoreCase(input)) {
                        results.add(storage);
                    }
                    break;
                case "Year":
                    if (String.valueOf(storage.getYear()).equals(input)) {
                        results.add(storage);
                    }
                    break;
                case "Max Price":
                    if (storage.getPrice() <= Integer.parseInt(input)) {
                        results.add(storage);
                    }
                    break;
                default:
                    break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Storage r : results) {
            sb.append("Author: ").append(r.getAuthor()).append("\n");
            sb.append("Title: ").append(r.getTitle()).append("\n");
            sb.append("Price: ").append(r.getPrice()).append("Ft\n\n");
        }

        if(results.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No books found.");
            return;
        }

        JOptionPane.showMessageDialog(null, sb.toString());

    }



    private void orderBook() {

        List results = new java.util.ArrayList<>();

        for (Storage storage : store.getBooks()) {
            if (storage.getPiece() < 10) {
                results.add(storage);
            }
        }

        if(results.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No books need to be ordered.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Object r : results) {
            Storage book = (Storage) r;
            sb.append("Author: ").append(book.getAuthor()).append("\n");
            sb.append("Title: ").append(book.getTitle()).append("\n");
            sb.append("Piece: ").append(book.getPiece()).append("\n\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString());
    }


    private void uploadBooks() {
        boolean anyLowStock = false;
        for (Storage storage : store.getBooks()) {
            if (storage.getPiece() < 10) {
                int load = Integer.parseInt(JOptionPane.showInputDialog("Author: " + storage.getAuthor() + "\nTitle: " + storage.getTitle() + "\nCurrent piece: " + storage.getPiece() + "\nHow many arrived?"));
                storage.addPiece(load);
                anyLowStock = true;
            }
        }
        if (!anyLowStock) {
            JOptionPane.showMessageDialog(this, "No books need to be uploaded.");
        } else {
            store.writeToFile("store.dat");
            JOptionPane.showMessageDialog(this, "Books successfully uploaded!");
        }
    }



    public static void main(String[] args) {
        new StoreGUI();
    }





    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}

