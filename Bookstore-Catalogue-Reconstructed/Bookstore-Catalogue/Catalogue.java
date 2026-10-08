import java.io.IOException;

public interface Catalogue {
    void populateCatalogue(String fileName) throws IOException;
    int binarySearch(String title);
    void bubbleSort();
    void printMenu();
    StringBuilder stockToStringBuilder();
}
