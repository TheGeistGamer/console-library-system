import java.util.Random;

public class Book {
  private static int nextId = 1;

  String id;
  String title;
  String author;

  boolean available = true;

  Random rand = new Random();

  // Constructor
  public Book(String title, String author) {
    this.id = "BK" + String.format("%03d", nextId++);
    this.title = title;
    this.author = author;
    this.available = true;
  }

  // CARGAR DESDE ARCHIVO
  public Book(String id, String title, String author, boolean available) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.available = available;
  }

  public void showInfo() {
    System.out.println(
        this.id + " - " +
            this.title +
            " - " +
            this.author + " || " +
            (this.available ? " ✅ Disponible" : " ❌ Prestado"));
  }

  public String getId() {
    return this.id;
  }

  public String getAuthor() {
    return this.author;
  }

  public boolean isAvailable() {
    return this.available;
  }

  public void handBack() {
    this.available = true;
  }

  public String getTitle() {
    return this.title;
  }

  public void borrow() {
    this.available = false;
  }
}