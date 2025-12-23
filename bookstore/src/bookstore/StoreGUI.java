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

        String[] buttons = { "Hozzáadás", "Módosítás", "Törlés", "Keresés", "Rendelés", "Feltöltés", "Kilépés"};

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
            case "Hozzáadás":
                addBook();
                break;
            case "Módosítás":
                modifyBook();
                break;
            case "Törlés":
                deleteBook();
                break;
            case "Keresés":
                searchBook();
                break;
            case "Rendelés":
                // Order book logic
                break;
            case "Feltöltés":
                // Upload book logic
                break;
            case "Kilépés":
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
            "Szerző:", authorField,
            "Cím:", titleField,
            "Téma:", topicField,
            "Év:", yearField,
            "Ár:", priceField,
            "Polc szám:", shelfNumberField,
            "Darabszám:", pieceField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Könyv hozzáadás", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String author = authorField.getText();
                String title = titleField.getText();
                String topic = topicField.getText();
                int year = Integer.parseInt(yearField.getText());
                int price = Integer.parseInt(priceField.getText());
                int shelfNumber = Integer.parseInt(shelfNumberField.getText());
                int piece = Integer.parseInt(pieceField.getText());

                Storage newBook = new Storage(author, title, topic, year, price, shelfNumber, piece);
                store.addStorage(newBook);
                store.writeToFile("store.dat");

                JOptionPane.showMessageDialog(this, "Könyv sikeresen hozzáadva!");
            } catch (NumberFormatException _) {
                JOptionPane.showMessageDialog(this, "Hibás adatbevitel! Kérlek ellenőrizd a mezőket.", "Hiba", JOptionPane.ERROR_MESSAGE);
            }
        }
    }



    private void modifyBook() {
        String searchAuthor = JOptionPane.showInputDialog(this, "Add meg a szerző nevét a módosításhoz:");
        String searchTitle = JOptionPane.showInputDialog(this, "Add meg a könyv címét a módosításhoz:");

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
                    "Szerző:", authorField,
                    "Cím:", titleField,
                    "Téma:", topicField,
                    "Év:", yearField,
                    "Ár:", priceField,
                    "Polc szám:", shelfNumberField,
                    "Darabszám:", pieceField
                };

                int option = JOptionPane.showConfirmDialog(this, message, "Könyv módosítás", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    try {
                        storage.setAuthor(authorField.getText());
                        storage.setTitle(titleField.getText());
                        storage.setTopic(topicField.getText());
                        storage.setYear(Integer.parseInt(yearField.getText()));
                        storage.setPrice(Integer.parseInt(priceField.getText()));
                        storage.setShelfNumber(Integer.parseInt(shelfNumberField.getText()));
                        storage.setPiece(Integer.parseInt(pieceField.getText()));

                        store.writeToFile("store.dat");

                        JOptionPane.showMessageDialog(this, "Könyv sikeresen módosítva!");
                    } catch (NumberFormatException _) {
                        JOptionPane.showMessageDialog(this, "Hibás adatbevitel! Kérlek ellenőrizd a mezőket.", "Hiba", JOptionPane.ERROR_MESSAGE);
                    }
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "A megadott könyv nem található.", "Hiba", JOptionPane.ERROR_MESSAGE);
    }




    private void deleteBook() {
        String searchAuthor = JOptionPane.showInputDialog(this, "Add meg a szerző nevét a törléshez:");
        String searchTitle = JOptionPane.showInputDialog(this, "Add meg a könyv címét a törléshez:");

        for (Storage storage : store.getBooks()) {
            if (storage.getAuthor().equalsIgnoreCase(searchAuthor) && storage.getTitle().equalsIgnoreCase(searchTitle)) {
                store.getBooks().remove(storage);
                store.writeToFile("store.dat");
                JOptionPane.showMessageDialog(this, "Könyv sikeresen törölve!");
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "A megadott könyv nem található.", "Hiba", JOptionPane.ERROR_MESSAGE);
    }



    private void searchBook() {
        String[] options = {"Szerző", "Cím", "Téma", "Kiadási év", "Maximális ár"};

        String chosen = (String) JOptionPane.showInputDialog(
                null,
                "Válaszd ki a keresési módot:",
                "Keresés",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (chosen == null) return;

         String input = JOptionPane.showInputDialog("Keresett érték:");

        if (input == null || input.isEmpty()) {
            return;
        }
        	
        List<Storage> results = new java.util.ArrayList<>();
        for (Storage storage : store.getBooks()) {
            switch (chosen) {
                case "Szerző":
                    if (storage.getAuthor().equalsIgnoreCase(input)) {
                        results.add(storage);
                    }
                    break;
                case "Cím":
                    if (storage.getTitle().equalsIgnoreCase(input)) {
                        results.add(storage);
                    }
                    break;
                case "Téma":
                    if (storage.getTopic().equalsIgnoreCase(input)) {
                        results.add(storage);
                    }
                    break;
                case "Kiadási év":
                    if (String.valueOf(storage.getYear()).equals(input)) {
                        results.add(storage);
                    }
                    break;
                case "Maximális ár":
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
            sb.append("Szerző: ").append(r.getAuthor()).append("\n");
            sb.append("Cím: ").append(r.getTitle()).append("\n");
            sb.append("Ár: ").append(r.getPrice()).append("Ft\n\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString());

    }



    public static void main(String[] args) {
        new StoreGUI();
    }





    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}

