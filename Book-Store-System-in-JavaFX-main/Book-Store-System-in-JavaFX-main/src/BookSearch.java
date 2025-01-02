import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookSearch {
    User curr;
    ListView<String> bookListView;
    ArrayList<CheckBox> conditionCheckboxList;
    ArrayList<CheckBox> genreCheckboxList;
    TextField search;
    private final Color MAROON = Color.web("#8C1D40");
    private final Color GOLD = Color.web("#FFC627");
    private final Color WHITE = Color.web("#FFFFFF");

    public BookSearch(User u) {
        curr = u;
        conditionCheckboxList = new ArrayList<>();
        genreCheckboxList = new ArrayList<>();

        //Top banner buttons
        Button myAccountButton = new Button("My Account");
        myAccountButton.setFont(Font.font("Arial", 12));
        myAccountButton.setStyle("-fx-background-color: #FFC627");
        myAccountButton.setTextFill(MAROON);
        myAccountButton.setOnAction(e->new AccountOverview(curr, "Buyer"));

        Button switchAccountsButton = new Button("Switch to Seller Account");
        switchAccountsButton.setFont(Font.font("Arial", 12));
        switchAccountsButton.setStyle("-fx-background-color: #FFC627");
        switchAccountsButton.setTextFill(MAROON);
        switchAccountsButton.setOnAction(e->new SellerPage(curr));

        Button logoutButton = new Button("Logout");
        logoutButton.setFont(Font.font("Arial", 12));
        logoutButton.setStyle("-fx-background-color: #FFC627");
        logoutButton.setTextFill(MAROON);
        logoutButton.setOnAction(e->{curr = null; new LoginPage();});

        //The Banner on the top
        HBox topBanner = new HBox(200);
        topBanner.setStyle("-fx-background-color: #CECECE;");
        topBanner.setPadding(new Insets(10));
        topBanner.setAlignment(Pos.CENTER);
        if(u.getUserType().equals("Buyer and Seller")) {topBanner.getChildren().addAll(myAccountButton, switchAccountsButton,logoutButton);}
        else {topBanner.getChildren().addAll(myAccountButton, logoutButton);}

        Label buyersPageLabel = new Label("Book Search");
        buyersPageLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        buyersPageLabel.setTextFill(GOLD);

        HBox titleBox = new HBox(buyersPageLabel);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(10, 0, 20, 0));

        Label conditionLabel = new Label("Condition");
        conditionLabel.setTextFill(GOLD);
        conditionLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        conditionLabel.setAlignment(Pos.CENTER);

        CheckBox newCheckBox = new CheckBox("Used Like New");
        newCheckBox.setTextFill(WHITE);
        newCheckBox.setOnAction(e->updateBookListView());
        CheckBox moderatelyUsedCheckBox = new CheckBox("Moderately Used");
        moderatelyUsedCheckBox.setTextFill(WHITE);
        moderatelyUsedCheckBox.setOnAction(e->updateBookListView());
        CheckBox heavilyUsedCheckBox = new CheckBox("Heavily Used");
        heavilyUsedCheckBox.setTextFill(WHITE);
        heavilyUsedCheckBox.setOnAction(e->updateBookListView());
        conditionCheckboxList.add(newCheckBox);
        conditionCheckboxList.add(moderatelyUsedCheckBox);
        conditionCheckboxList.add(heavilyUsedCheckBox);

        Label genreLabel = new Label("Genre");
        genreLabel.setTextFill(GOLD);
        genreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        genreLabel.setAlignment(Pos.CENTER);

        CheckBox scifiCheckBox = new CheckBox("Sci-Fi");
        scifiCheckBox.setTextFill(WHITE);
        scifiCheckBox.setOnAction(e->updateBookListView());
        CheckBox fantasyCheckBox = new CheckBox("Fantasy");
        fantasyCheckBox.setTextFill(WHITE);
        fantasyCheckBox.setOnAction(e->updateBookListView());
        CheckBox comedyCheckBox = new CheckBox("Comedy");
        comedyCheckBox.setTextFill(WHITE);
        comedyCheckBox.setOnAction(e->updateBookListView());
        CheckBox dramaCheckBox = new CheckBox("Drama");
        dramaCheckBox.setTextFill(WHITE);
        dramaCheckBox.setOnAction(e->updateBookListView());
        CheckBox thrillerCheckBox = new CheckBox("Thriller");
        thrillerCheckBox.setTextFill(WHITE);
        thrillerCheckBox.setOnAction(e->updateBookListView());
        CheckBox adventureCheckBox = new CheckBox("Adventure");
        adventureCheckBox.setTextFill(WHITE);
        adventureCheckBox.setOnAction(e->updateBookListView());
        CheckBox mysteryCheckBox = new CheckBox("Mystery");
        mysteryCheckBox.setTextFill(WHITE);
        mysteryCheckBox.setOnAction(e->updateBookListView());

        genreCheckboxList.add(scifiCheckBox);
        genreCheckboxList.add(fantasyCheckBox);
        genreCheckboxList.add(comedyCheckBox);
        genreCheckboxList.add(dramaCheckBox);
        genreCheckboxList.add(thrillerCheckBox);
        genreCheckboxList.add(adventureCheckBox);
        genreCheckboxList.add(mysteryCheckBox);

        VBox filtersVBox = new VBox(15, conditionLabel, newCheckBox, moderatelyUsedCheckBox, heavilyUsedCheckBox, genreLabel, scifiCheckBox, fantasyCheckBox, comedyCheckBox, dramaCheckBox, thrillerCheckBox, adventureCheckBox, mysteryCheckBox);
        filtersVBox.setPadding(new Insets(20, 0, 0, 20));
        filtersVBox.setStyle("-fx-background-color: #8C1D40;");
        filtersVBox.setPrefWidth(200);

        Label availableBooksLabel = new Label("Available Books");
        availableBooksLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        availableBooksLabel.setTextFill(GOLD);

        search = new TextField();
        search.setPromptText("Search for Books");
        search.setPrefWidth(280);
        search.textProperty().addListener((o) -> updateBookListView());

        bookListView = new ListView<>();
        bookListView.setPrefSize(280, 400);
        setBookListView();

        VBox availableBooksVBox = new VBox(10, availableBooksLabel, search, bookListView);
        availableBooksVBox.setPadding(new Insets(10));

        // Action Buttons at the bottom
        Button addToCartButton = new Button("Add to Cart");
        addToCartButton.setTextFill(MAROON);
        addToCartButton.setOnAction(e->AddToUserCart(bookListView.getSelectionModel().getSelectedItem()));
        Button viewCartButton = new Button("View Cart");
        viewCartButton.setTextFill(MAROON);

        addToCartButton.setStyle("-fx-background-color: #FFC627; -fx-font-size: 12;");
        viewCartButton.setStyle("-fx-background-color: #FFC627; -fx-font-size: 12;");

        viewCartButton.setOnAction(e->{new cart(curr);});

        HBox buttonBox = new HBox(100, addToCartButton, viewCartButton);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setAlignment(Pos.CENTER);

        BorderPane mainLayout = new BorderPane();
        mainLayout.setStyle("-fx-background-color: #8C1D40;");
        mainLayout.setTop(topBanner);
        mainLayout.setLeft(filtersVBox);
        mainLayout.setBottom(buttonBox);

        BorderPane.setAlignment(topBanner, Pos.CENTER);
        BorderPane.setMargin(topBanner, new Insets(0, 0, 10, 0));
        BorderPane.setMargin(buttonBox, new Insets(10, 0, 0, 0));

        VBox centerContent = new VBox(10, titleBox, availableBooksVBox);
        centerContent.setAlignment(Pos.TOP_CENTER);

        mainLayout.setCenter(centerContent);

        Scene sc = new Scene(mainLayout, 800, 600);
        PageHandler.updateStage("Book Search", sc);
    }

    private void updateBookListView() {
        ArrayList<String> condition = new ArrayList<>();
        ArrayList<String> genre = new ArrayList<>();
        Book b;
        for(CheckBox c : conditionCheckboxList) {if(c.isSelected()) {condition.add(c.getText());}}
        for(CheckBox c : genreCheckboxList) {if(c.isSelected()) {genre.add(c.getText());}}

        if(condition.isEmpty() && genre.isEmpty()) {setBookListView(); return;}

        bookListView.getItems().clear();

        String query = "SELECT * from books";
        if(!condition.isEmpty()) {
            query += " WHERE bookCondition IN (";
            for(int i = 0; i < condition.size(); i++) {
                query += ("'" + condition.get(i) + "'");
                if(i < condition.size() - 1) {query += ", ";}
            }
            query += ")";
        }
        if(!genre.isEmpty()) {
            query += (!condition.isEmpty()) ? " AND genre IN (" : " WHERE genre IN (";
            for(int i = 0; i < genre.size(); i++) {
                query += ("'" + genre.get(i) + "'");
                if(i < genre.size() - 1) {query += ", ";}
            }
            query += ")";
        }

        ResultSet rs = DataManipulator.query(query);
        try {
            while(rs.next()) {
                b = new Book(rs.getString("title"), rs.getString("bookCondition"), rs.getString("genre"), rs.getDouble("price"), rs.getInt("id"));
                if(b.getTitle().contains(search.getText().trim())){bookListView.getItems().add(b.toString());}
            }
        } catch (SQLException e) {}
    }

    private void setBookListView() {
        Book b;
        String query = "SELECT * FROM books";
        ResultSet rs = DataManipulator.query(query);
        try {
            bookListView.getItems().clear();
            while(rs.next()) {
                b = new Book(rs.getString("title"), rs.getString("bookCondition"), rs.getString("genre"), rs.getDouble("price"), rs.getInt("id"));
                if(b.getTitle().contains(search.getText().trim())){bookListView.getItems().add(b.toString());}
            }
        } catch (SQLException e) {}
    }

    private void AddToUserCart(String s) {
        if(s == null) {showAlert("Please Select a Book From the List");return;}
        String i = s.substring(s.lastIndexOf(":") + 1);
        int id = Integer.parseInt(i);
        String query = "SELECT * from books WHERE id = " + id;
        ResultSet rs = DataManipulator.query(query);
        Book b;
        try {
            bookListView.getItems().clear();
            while(rs.next()) {
                b = new Book(rs.getString("title"), rs.getString("bookCondition"), rs.getString("genre"), rs.getDouble("price"), rs.getInt("id"));
                curr.addToCart(b);
                Inform("Successfully Added to Cart");
                setBookListView();
            }
        } catch (SQLException e) {}
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait();
    }

    private void Inform(String s) {
        Alert a = new Alert(Alert.AlertType.INFORMATION, s);
        a.setTitle("Success");
        a.setHeaderText("Successfully Added");
        a.showAndWait();
    }
}
