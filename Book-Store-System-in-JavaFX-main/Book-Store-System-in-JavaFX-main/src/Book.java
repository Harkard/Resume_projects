public class Book {
    private int bookID;
    private String title, condition, genres;
    private double price;

    public Book(String title, String condition, String genres,  double price, int bookID) {
        this.title = title;
        this.condition = condition;
        this.genres = genres;
        this.price = price;
        this.bookID = bookID;
    }

    public int getBookID() {return bookID;}

    public String getTitle() {return title;}

    public double getPrice() {return price;}

    public String toString() {return title + " - $" + String.format("%.2f",price) + " - " + condition + " - " + genres + " - Book ID:" + bookID;}
}
