# Bookstore Management System

This project is a Java-based desktop application designed for managing bookstore inventory. It provides a Graphical User Interface to register, modify, delete, and search for books, as well as track stock levels and manage restocking orders.

## 📋 Features

The system offers the following main functions with the main menu:

* **Add:** Register a new book in the system (Author, Title, Topic, Year, Price, Shelf Number, Piece).
* **Modify:** Edit the details of an existing book based on Author and Title.
* **Delete:** Permanently remove a book from the system.
* **Search:** Find books based on various criteria:
    * Author
    * Title
    * Topic
    * Publication Year
    * Max Price (lists books cheaper than the specified amount)
* **Order:** Lists books that are running low on stock (**fewer than 10 pieces**).
* **Upload:** Allows restocking of low-stock items. It prompts for the incoming quantity for every book with fewer than 10 pieces.
* **Data Persistence:** The application automatically saves all changes to a `store.dat` file (using Java Serialization), ensuring data is preserved between restarts.

## 🛠 Technologies

* **Language:** Java (JDK 8+)
* **GUI:** Java Swing
* **Data Management:** Java I/O (Serializable, ObjectOutputStream/ObjectInputStream)
* **Architecture:** Object-Oriented Programming

## 📂 File Structure and Classes

The project is located in the `bookstore` package:

1.  **`Book.java`**: The base class containing the abstract data of a book (author, title, topic, year, price).
2.  **`Storage.java`**: Extends `Book`. Adds physical inventory attributes: shelf number (`shelfNumber`) and quantity (`piece`).
3.  **`Store.java`**: The Data Access Object. It manages the `ArrayList` of books and handles reading/writing to the `store.dat` file.
4.  **`StoreGUI.java`**: The main application class. It contains the `main` method, the GUI layout, event listeners, and input validation logic.

## 🚀 Installation and Usage

You need the Java Development Kit (JDK) installed to run this program.