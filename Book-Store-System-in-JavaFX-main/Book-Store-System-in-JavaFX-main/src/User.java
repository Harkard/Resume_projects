import java.util.ArrayList;

public class User {
    private String name, uname, password, email, userType;
    private ArrayList<Book> cart;

    public User(String name, String uname, String password, String email, String userType) {
        this.name = name;
        this.uname = uname;
        this.password = password;
        this.email = email;
        this.userType = userType;
        cart = new ArrayList<>();
    }

    public void addToCart(Book b) {cart.add(b);}

    public ArrayList<Book> getCart() {return cart;}

    public String getPassword() {return password;}

    public String getUname() {return uname;}

    public String getName() {return name;}

    public String getEmail() {return email;}

    public String getUserType() {return userType;}

    public boolean validateUser(String uname, String password) {return (this.uname.equals(uname) && this.password.equals(password));}
}
