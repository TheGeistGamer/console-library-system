import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Library library = new Library();

    // Cargar datos
    library.loadBooksFromFile();

    int option;

    do {
      System.out.println("1. Add book");
      System.out.println("2. Borrow book");
      System.out.println("3. Return book");
      System.out.println("4. Search book");
      System.out.println("5. Show all books");
      System.out.println("9. Exit");

      option = sc.nextInt(); // Select a option
      sc.nextLine();

      switch (option) {
        case 1:
          library.addBook(sc);
          break;

        case 2:
          library.borrowBook(sc);
          break;

        case 3:
          library.returnBook(sc);
          break;

        case 4:
          library.searchBook(sc);
          break;

        case 5:
          library.showBook();
          break;

        case 9:
          option = 9;
          sc.close();

        default:
          System.out.print("You can only choose from these options 1, 2, 3");
          break;
      }
    } while (option != 9);

  }
}
