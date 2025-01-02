import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.ArrayList;

public class cart {
    User curr;
    Label bill_label;
    ListView<String> cartListView;
    double total;

    private ArrayList<Book> cart;
    private final Color MAROON = Color.web("#8C1D40");
    private final Color GOLD = Color.web("#FFC627");
    private final Color WHITE = Color.web("#FFFFFF");

    public cart(User u){
        total = 0.0;
        curr = u;

        //Top Banner Buttons
        Button myAccountButton = new Button("My Account");
        myAccountButton.setFont(Font.font("Arial", 12));
        myAccountButton.setStyle("-fx-background-color: #FFC627");
        myAccountButton.setTextFill(MAROON);
        myAccountButton.setOnAction(e ->new AccountOverview(curr, "Cart"));

        Button logoutButton = new Button("Logout");
        logoutButton.setFont(Font.font("Arial", 12));
        logoutButton.setStyle("-fx-background-color: #FFC627");
        logoutButton.setTextFill(MAROON);
        logoutButton.setOnAction(e -> {curr = null;new LoginPage();});

        //Top Banner layout and Hbox for adding elements
        HBox topBanner = new HBox(200);
        topBanner.setStyle("-fx-background-color: #CECECE;");
        topBanner.setPadding(new Insets(10));
        topBanner.setAlignment(Pos.CENTER);
        topBanner.getChildren().addAll(myAccountButton, logoutButton);

        //Cart title
        Label cart_label = new Label("Cart");
        cart_label.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        cart_label.setTextFill(GOLD);
        cart_label.setPadding(new Insets(0, 0, 0, 300));

        //Back button
        Button back_button = new Button("Back");
        back_button.setTextFill(MAROON);
        back_button.setPadding(new Insets(10));
        back_button.setStyle("-fx-background-color: #FFC627; -fx-font-size: 12;");
        back_button.setOnAction(e->{new BookSearch(curr);});

        //Hbox to add cart title and back button
        HBox top_vbox = new HBox(300, cart_label, back_button);
        top_vbox.setAlignment(Pos.TOP_CENTER);
        top_vbox.setStyle("-fx-background-color: #8C1D40;");

        //list of books in the cart
        cartListView = new ListView<>();
        cartListView.setMaxSize(450, 310);
        cart = curr.getCart();
        setCartListView();

        //delete button
        Button delete_button = new Button("Delete");
        delete_button.setTextFill(MAROON);
        delete_button.setPadding(new Insets(10));
        delete_button.setStyle("-fx-background-color: #FFC627; -fx-font-size: 12;");
        delete_button.setOnAction(e -> {
            int selectedIndex = cartListView.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {
                cart.remove(selectedIndex);
                setCartListView();
                updateBill();
            }
        });

        //Bill label which shows the final bill
        bill_label = new Label("Total Bill: $0.00");
        display_bill();

        //Confirm Order button
        Button confirm_order = new Button("Confirm Order");
        confirm_order.setTextFill(MAROON);
        confirm_order.setPadding(new Insets(10));
        confirm_order.setStyle("-fx-background-color: #FFC627; -fx-font-size: 12;");
        confirm_order.setOnAction(e->confirmOrder());

        //Hbox for adding delete button, total bill and confirm_order
        HBox bottom_vbox = new HBox(200);
        bottom_vbox.setAlignment(Pos.TOP_CENTER);
        bottom_vbox.setStyle("-fx-background-color: #8C1D40;");
        bottom_vbox.getChildren().addAll(delete_button, bill_label, confirm_order);


        VBox cart_elements = new VBox(50, topBanner, top_vbox, cartListView, bottom_vbox);
        cart_elements.setStyle("-fx-background-color: #8C1D40;");
        cart_elements.setAlignment(Pos.TOP_CENTER);

        Scene sc = new Scene(cart_elements, 800, 600);
        PageHandler.updateStage("Cart", sc);

    }

    private void setCartListView() {
        cartListView.getItems().clear();

        if (cart != null) {for (Book book : cart) {cartListView.getItems().add(book.toString());}}
    }


    private void display_bill(){
        updateBill();
        bill_label.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        bill_label.setTextFill(GOLD);
    }

    private void updateBill() {
        total = 0.0;

        if(cart != null) {for (Book book : cart) {total += book.getPrice();}}

        bill_label.setText(String.format("Total Bill: $%.2f", total));
    }

    private void confirmOrder() {
        String query = "";
        for(Book b : cart) {
            query = "DELETE FROM books WHERE id = " + b.getBookID();
            DataManipulator.update(query);
        }
        Inform("You Confirmed Your Order for $" + total);
        cart.clear();
        setCartListView();
        updateBill();
    }

    private void Inform(String s) {
        Alert a = new Alert(Alert.AlertType.INFORMATION, s);
        a.setTitle("Success");
        a.setHeaderText("Successfully Added");
        a.showAndWait();
    }
}
