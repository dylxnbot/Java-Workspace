public class Book extends Media {
    private String author;
    private String isbn;

    public Book(String title, String author, double price, String isbn, int numCopies) {
        super(title, price, numCopies);
        this.author = author;
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        return String.format(
            "%s by %s | $%.2f | ISBN: %s | Copies: %d",
            title, author, price, isbn, numCopies
        );
    }
}
