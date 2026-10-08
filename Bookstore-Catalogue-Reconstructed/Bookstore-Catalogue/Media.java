public abstract class Media {
    protected String title;
    protected double price;
    protected int numCopies;

    public Media(String title, double price, int numCopies) {
        this.title = title;
        this.price = price;
        this.numCopies = numCopies;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public int getNumCopies() {
        return numCopies;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setNumCopies(int numCopies) {
        this.numCopies = numCopies;
    }

    public abstract String toString();
}
