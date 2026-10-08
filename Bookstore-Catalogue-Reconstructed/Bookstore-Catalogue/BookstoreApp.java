import java.io.IOException;
import java.util.Scanner;

public class BookstoreApp {
    public static void main(String[] args) {
        Bookstore store = new Bookstore();
        Scanner input = new Scanner(System.in);

        try {
            store.populateCatalogue("books.txt");
        } catch (IOException e) {
            System.out.println("Could not load books.txt: " + e.getMessage());
            return;
        }

        int choice;

        do {
            store.printMenu();

            while (!input.hasNextInt()) {
                System.out.print("Enter a number: ");
                input.next();
            }

            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter title to search: ");
                    String searchTitle = input.nextLine();
                    Book found = store.search(searchTitle);

                    if (found != null) {
                        System.out.println(found);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;

                case 2:
                    System.out.print("Enter title to purchase: ");
                    String purchaseTitle = input.nextLine();

                    if (store.purchase(purchaseTitle)) {
                        System.out.println("Purchase completed.");
                    } else {
                        System.out.println("Book unavailable.");
                    }
                    break;

                case 3:
                    System.out.print("Enter title to sell: ");
                    String sellTitle = input.nextLine();
                    double payout = store.sell(sellTitle);

                    if (payout >= 0) {
                        System.out.printf("Store pays you $%.2f%n", payout);
                    } else {
                        System.out.println("Book not found in catalogue.");
                    }
                    break;

                case 4:
                    System.out.print("Title: ");
                    String title = input.nextLine();
                    System.out.print("Author: ");
                    String author = input.nextLine();
                    System.out.print("Price: ");
                    double price = input.nextDouble();
                    input.nextLine();
                    System.out.print("ISBN: ");
                    String isbn = input.nextLine();
                    System.out.print("Copies: ");
                    int copies = input.nextInt();
                    input.nextLine();

                    if (store.addBook(new Book(title, author, price, isbn, copies))) {
                        System.out.println("Book added.");
                    } else {
                        System.out.println("A book with that title already exists.");
                    }
                    break;

                case 5:
                    System.out.print("Enter title to remove: ");
                    String removeTitle = input.nextLine();

                    if (store.removeBook(removeTitle)) {
                        System.out.println("Book removed.");
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;

                case 6:
                    System.out.print(store.stockToStringBuilder());
                    break;

                case 0:
                    System.out.println("Goodbye.");
                    break;

                default:
                    System.out.println("Invalid option.");
            }

        } while (choice != 0);

        input.close();
    }
}
