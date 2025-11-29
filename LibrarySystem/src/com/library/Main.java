package com.library;

import com.library.dao.BookDAO;
import com.library.model.Book;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookDAO dao = new BookDAO();
        int choice;

        do {
            System.out.println("\n===== Library Menu =====");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book by ID");
            System.out.println("4. Update Book Price");
            System.out.println("5. Delete Book");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    sc.nextLine();
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();
                    dao.addBook(new Book(title, author, price));
                    break;

                case 2:
                    List<Book> list = dao.getAllBooks();
                    for (Book b : list) {
                        System.out.println(b.getBookId() + " | " + b.getTitle() + " | " + b.getAuthor() + " | " + b.getPrice());
                    }
                    break;

                case 3:
                    System.out.print("Enter Book ID: ");
                    int id = sc.nextInt();
                    Book b = dao.getBookById(id);
                    if (b != null)
                        System.out.println(b.getBookId() + " | " + b.getTitle() + " | " + b.getAuthor() + " | " + b.getPrice());
                    else
                        System.out.println("Book not found!");
                    break;

                case 4:
                    System.out.print("Enter Book ID: ");
                    int id2 = sc.nextInt();
                    System.out.print("Enter New Price: ");
                    double newPrice = sc.nextDouble();
                    dao.updateBook(id2, newPrice);
                    break;

                case 5:
                    System.out.print("Enter Book ID: ");
                    int id3 = sc.nextInt();
                    dao.deleteBook(id3);
                    break;

                case 6:
                    System.out.println("Exiting...");
                    break;
            }
        } while (choice != 6);
    }
}
