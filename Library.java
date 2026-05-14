import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

public class Library {
  ArrayList<Book> books = new ArrayList<>();

  // Agregar un libro
  public void addBook(Scanner sc) {
    System.out.print("Title: ");
    String title = sc.nextLine();

    System.out.print("Author: ");
    String author = sc.nextLine();

    books.add(new Book(title, author));
    this.saveBookToFile();
  }

  // Mostrar todo los libros
  public void showBook() {
    System.out.println("---------------------");
    for (Book book : books) {
      book.showInfo();
    }
    System.out.println("---------------------");
  }

  public void borrowBook(Scanner sc) {
    System.out.print("ID del libro: ");
    String id = sc.nextLine();

    Book book = this.findBookById(id);

    if (book == null) {
      showBookNotFound();
      return;
    }

    if (book.isAvailable()) {
      book.borrow();
      this.saveBookToFile();
      System.out.println("Book successfully loaned.");
    } else {
      System.out.println("That book is already on loan.");
    }
  }

  public void returnBook(Scanner sc) {
    System.out.print("Book ID: ");
    String id = sc.nextLine();
    Book book = this.findBookById(id);

    if (book == null) {
      showBookNotFound();
      return;
    }

    if (!book.isAvailable()) {
      book.handBack();
      this.saveBookToFile();
      System.out.println("Book successfully returned.");
    } else {
      System.out.println("That book is already on loan.");
    }
  }

  public void searchBook(Scanner sc) {
    System.out.print("Search title: ");
    String text = sc.nextLine().trim().toLowerCase();
    boolean found = false;

    if (text.isEmpty()) {
      System.out.println("Type something to search for.");
      return;
    }

    for (Book book : books) {
      String title = book.getTitle().toLowerCase();

      if (title.contains(text)) {
        book.showInfo();
        found = true;
      }
    }

    if (!found)
      System.out.print("No books were found.");
  }

  public void searchByAuthor(Scanner sc) {
    System.out.print("Author: ");
    String text = sc.nextLine().trim().toLowerCase();
    boolean found = false;

    if (text.isEmpty()) {
      System.out.println("Type something to search for.");
      return;
    }

    for (Book book : books) {
      if (book.getAuthor().toLowerCase().contains(text)) {
        book.showInfo();
        found = true;
      }
    }

    if (!found)
      System.out.println("No books were found.");
  }

  public void saveBookToFile() {
    try {
      PrintWriter writer = new PrintWriter("books.txt");

      for (Book book : books) {
        writer.println(
            book.getId() + "," +
                book.getTitle() + "," +
                book.getAuthor() + "," +
                book.isAvailable());
      }

      writer.close();
      System.out.println("Books stored.");
    } catch (Exception e) {
      System.out.println("Error saving.");
    }
  }

  public void loadBooksFromFile() {
    try {
      File file = new File("books.txt");
      if (!file.exists())
        return;

      Scanner reader = new Scanner(file);

      while (reader.hasNextLine()) {
        String line = reader.nextLine();
        String[] parts = line.split(",");

        String id = parts[0];
        String title = parts[1];
        String author = parts[2];
        boolean available = Boolean.parseBoolean(parts[3]);

        books.add(new Book(id, title, author, available));
      }

      reader.close();
    } catch (Exception e) {
      System.out.println("Error loading books.");
    }
  }

  private Book findBookById(String id) {
    for (Book book : books) {
      if (book.getId().equalsIgnoreCase(id.trim())) {
        return book;
      }
    }
    return null;
  }

  private void showBookNotFound() {
    System.out.println("---------------------");
    System.out.println("The book was not found.");
    System.out.println("---------------------");
  }
}
