import java.io.*;
import java.util.*;

public class Bookstore implements Catalogue {
    private final ArrayList<Book> books = new ArrayList<>();

    @Override
    public void populateCatalogue(String fileName) throws IOException {
        books.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                // Format:
                // title|author|price|isbn|numCopies
                String[] parts = line.split("\\|");

                if (parts.length != 5) {
                    System.out.println("Skipping malformed line: " + line);
                    continue;
                }

                String title = parts[0].trim();
                String author = parts[1].trim();
                double price = Double.parseDouble(parts[2].trim());
                String isbn = parts[3].trim();
                int numCopies = Integer.parseInt(parts[4].trim());

                books.add(new Book(title, author, price, isbn, numCopies));
            }
        }

        bubbleSort();
    }

    @Override
    public int binarySearch(String title) {
        int low = 0;
        int high = books.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int result = books.get(mid).getTitle().compareToIgnoreCase(title);

            if (result == 0) {
                return mid;
            } else if (result < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    @Override
    public void bubbleSort() {
        for (int i = 0; i < books.size() - 1; i++) {
            for (int j = 0; j < books.size() - i - 1; j++) {
                if (books.get(j).getTitle()
                        .compareToIgnoreCase(books.get(j + 1).getTitle()) > 0) {
                    Book temp = books.get(j);
                    books.set(j, books.get(j + 1));
                    books.set(j + 1, temp);
                }
            }
        }
    }

    @Override
    public void printMenu() {
        System.out.println("\n--- Twice Sold Tales ---");
        System.out.println("1. Search");
        System.out.println("2. Purchase");
        System.out.println("3. Sell");
        System.out.println("4. Add");
        System.out.println("5. Remove");
        System.out.println("6. Print Catalogue");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    @Override
    public StringBuilder stockToStringBuilder() {
        StringBuilder builder = new StringBuilder();

        for (Book book : books) {
            builder.append(book).append(System.lineSeparator());
        }

        return builder;
    }

    public Book search(String title) {
        int index = binarySearch(title);
        return index >= 0 ? books.get(index) : null;
    }

    public boolean purchase(String title) {
        Book book = search(title);

        if (book == null || book.getNumCopies() <= 0) {
            return false;
        }

        book.setNumCopies(book.getNumCopies() - 1);

        // Reconstructed from remembered assignment behavior:
        // purchasing increases the listed price by $0.25.
        book.setPrice(book.getPrice() + 0.25);

        return true;
    }

    public double sell(String title) {
        Book book = search(title);

        if (book == null) {
            return -1;
        }

        // Store pays 25% of current listed price.
        double payout = book.getPrice() * 0.25;
        book.setNumCopies(book.getNumCopies() + 1);
        return payout;
    }

    public boolean addBook(Book newBook) {
        if (search(newBook.getTitle()) != null) {
            return false;
        }

        books.add(newBook);
        bubbleSort();
        return true;
    }

    public boolean removeBook(String title) {
        int index = binarySearch(title);

        if (index < 0) {
            return false;
        }

        books.remove(index);
        return true;
    }
}
