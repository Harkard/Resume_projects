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

public class SellerPage {
    User curr;
    private final Color MAROON = Color.web("#8C1D40");
    private final Color GOLD = Color.web("#FFC627");
    private final Color WHITE = Color.web("#FFFFFF");
    private Label auto_price_label;
    private TextField priceInput;
    private TextArea book_title;

    public SellerPage(User u) {
        curr = u;
        String title, genre, condition, author = "";

        //Code starts for top banner

        //Top Banner Buttons
        Button myAccountButton = new Button("My Account");
        myAccountButton.setFont(Font.font("Arial", 12));
        myAccountButton.setStyle("-fx-background-color: #FFC627");
        myAccountButton.setTextFill(MAROON);
        myAccountButton.setOnAction(e ->new AccountOverview(curr, "Seller"));

        Button switchAccountsButton = new Button("Switch to Buyer Account");
        switchAccountsButton.setFont(Font.font("Arial", 12));
        switchAccountsButton.setStyle("-fx-background-color: #FFC627");
        switchAccountsButton.setTextFill(MAROON);
        switchAccountsButton.setOnAction(e->new BookSearch(curr));

        Button logoutButton = new Button("Logout");
        logoutButton.setFont(Font.font("Arial", 12));
        logoutButton.setStyle("-fx-background-color: #FFC627");
        logoutButton.setTextFill(MAROON);
        logoutButton.setOnAction(e -> {curr = null;new LoginPage();});

        //Top Banner layout
        HBox topBanner = new HBox(200);
        topBanner.setStyle("-fx-background-color: #CECECE;");
        topBanner.setPadding(new Insets(10));
        topBanner.setAlignment(Pos.CENTER);
        if(u.getUserType().equals("Buyer and Seller")) {topBanner.getChildren().addAll(myAccountButton, switchAccountsButton,logoutButton);}
        else {topBanner.getChildren().addAll(myAccountButton, logoutButton);}

        //Title of the page
        Label sellerPageLabel = new Label("Sell Books");
        sellerPageLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        sellerPageLabel.setTextFill(GOLD);

        HBox titleBox = new HBox(sellerPageLabel);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(10, 0, 20, 0));

        //Vbox to add title of the page and the top banner
        VBox top_page = new VBox(10, topBanner, titleBox);
        top_page.setPrefWidth(800);

        //Code ends for top banner

        //Body of the seller page starts below

        //Genre label, check boxes for genre and list my book button

        //Genre label
        Label genreLabel = new Label("Genre");
        genreLabel.setTextFill(GOLD);
        genreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        genreLabel.setAlignment(Pos.CENTER);

        //Genres
        ToggleGroup genreToggle = new ToggleGroup();
        RadioButton scifiToggle = new RadioButton("Sci-Fi");
        scifiToggle.setTextFill(WHITE);
        scifiToggle.setToggleGroup(genreToggle);
        scifiToggle.setSelected(true);
        RadioButton fantasyToggle = new RadioButton("Fantasy");
        fantasyToggle.setTextFill(WHITE);
        fantasyToggle.setToggleGroup(genreToggle);
        RadioButton comedyToggle = new RadioButton("Comedy");
        comedyToggle.setTextFill(WHITE);
        comedyToggle.setToggleGroup(genreToggle);
        RadioButton dramaToggle = new RadioButton("Drama");
        dramaToggle.setTextFill(WHITE);
        dramaToggle.setToggleGroup(genreToggle);
        RadioButton thrillerToggle = new RadioButton("Thriller");
        thrillerToggle.setTextFill(WHITE);
        thrillerToggle.setToggleGroup(genreToggle);
        RadioButton adventureToggle = new RadioButton("Adventure");
        adventureToggle.setTextFill(WHITE);
        adventureToggle.setToggleGroup(genreToggle);
        RadioButton mysteryToggle = new RadioButton("Mystery");
        mysteryToggle.setTextFill(WHITE);
        mysteryToggle.setToggleGroup(genreToggle);

        //Button List My Book
        Button listMyBook = new Button("List My Book");
        listMyBook.setStyle("-fx-background-color: #FFC627; -fx-font-size: 12;");
        listMyBook.setTextFill(MAROON);

        //Vbox for genre checkboxes and setting margin for each checkbox
        VBox genre_set = new VBox(15);
        genre_set.setStyle("-fx-background-color: #8C1D40;");
        VBox.setMargin(scifiToggle, new Insets(0, 0, 0, 80));
        VBox.setMargin(fantasyToggle, new Insets(0, 0, 0, 80));
        VBox.setMargin(comedyToggle, new Insets(0, 0, 0, 80));
        VBox.setMargin(dramaToggle, new Insets(0, 0, 0, 80));
        VBox.setMargin(thrillerToggle, new Insets(0, 0, 0, 80));
        VBox.setMargin(adventureToggle, new Insets(0, 0, 0, 80));
        VBox.setMargin(mysteryToggle, new Insets(0, 0, 0, 80));

        genre_set.getChildren().addAll(scifiToggle, fantasyToggle, comedyToggle, dramaToggle, thrillerToggle, adventureToggle, mysteryToggle);

        //Vbox for label, checkbox and button
        VBox left_page = new VBox(50, genreLabel, genre_set, listMyBook);
        left_page.setStyle("-fx-background-color: #8C1D40;");
        left_page.setPrefWidth(250);
        left_page.setAlignment(Pos.TOP_CENTER);

        //Genre label, check boxes for genre and list my book button done

        //Quality, price and generate price button

        //Quality label
        Label quality_label = new Label("Quality");
        quality_label.setTextFill(GOLD);
        quality_label.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        quality_label.setAlignment(Pos.CENTER);

        //quality radio buttons
        ToggleGroup qualityGroup = new ToggleGroup();
        RadioButton newToggle = new RadioButton("Used Like New");
        newToggle.setToggleGroup(qualityGroup);
        newToggle.setSelected(true);
        newToggle.setTextFill(Color.WHITE);
        RadioButton moderately_used = new RadioButton("Moderately Used");
        moderately_used.setToggleGroup(qualityGroup);
        moderately_used.setTextFill(Color.WHITE);
        RadioButton heavily_used = new RadioButton("Heavily Used");
        heavily_used.setToggleGroup(qualityGroup);
        heavily_used.setTextFill(Color.WHITE);


        //Price label
        Label price_label = new Label("Price ($)");
        price_label.setTextFill(GOLD);
        price_label.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        price_label.setAlignment(Pos.CENTER);

        //generate price
        priceInput = new TextField();
        priceInput.setPromptText("Enter price in $");
        priceInput.setStyle("-fx-font-size: 14;");
        priceInput.setMaxWidth(120);
        priceInput.textProperty().addListener((o, oldPrice, newPrice) ->{
            if (!newPrice.isEmpty() && !newPrice.matches("\\d*\\.?\\d*")) {priceInput.setText(oldPrice);}
        });

        Button generateButton = new Button("Generate Price");
        generateButton.setTextFill(MAROON);
        generateButton.setStyle("-fx-background-color: #FFC627; -fx-font-size: 12;");
        double[] display_price = new double[1];
        display_price[0] = 0;
        generateButton.setOnAction(e -> {
            if(priceInput.getText().isEmpty()){showAlert("Please Enter a Price");}
            else {
                display_price[0] = generate_price(priceInput.getText().trim(), ((RadioButton)qualityGroup.getSelectedToggle()).getText());
                auto_price_label.setText(String.format("Price: $%.2f ", display_price[0]) + "(" + getAdminCut(((RadioButton) qualityGroup.getSelectedToggle()).getText()) + "% goes to the admin)");
            }
        });

        //Add radio buttons to a vbox
        VBox radio_buttons = new VBox(15, newToggle, moderately_used, heavily_used);
        radio_buttons.setStyle("-fx-background-color: #8C1D40;");

        //Add Quality, price and generate price button to a vbox
        VBox center_page = new VBox(50,quality_label, radio_buttons, price_label, priceInput, generateButton);
        center_page.setStyle("-fx-background-color: #8C1D40;");
        center_page.setPrefWidth(200);
        center_page.setAlignment(Pos.TOP_LEFT);

        //Quality, price and generate price button done

        //Title and Selling price

        //title and text field to enter the title
        Label title_label = new Label("Title");
        title_label.setTextFill(GOLD);
        title_label.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        title_label.setAlignment(Pos.CENTER);

        book_title = new TextArea();
        book_title.setPromptText("Enter the name of the book");
        book_title.setStyle("-fx-font-size: 18;");
        book_title.setWrapText(true);
        book_title.setMaxWidth(250);
        book_title.setPrefHeight(120);

        //Selling price label and text
        Label selling_price_label = new Label("Selling Price");
        selling_price_label.setTextFill(GOLD);
        selling_price_label.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        selling_price_label.setAlignment(Pos.CENTER);

        auto_price_label = new Label(String.format("Price: $%.2f", display_price[0]));
        auto_price_label.setTextFill(WHITE);
        auto_price_label.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        auto_price_label.setAlignment(Pos.CENTER);

        //Vbox to add everything
        VBox right_page = new VBox(40, title_label, book_title, selling_price_label, auto_price_label);
        right_page.setStyle("-fx-background-color: #8C1D40;");
        right_page.setPrefWidth(300);
        right_page.setAlignment(Pos.TOP_LEFT);

        listMyBook.setOnAction(e->{
            listBook(book_title.getText().trim(), ((RadioButton)genreToggle.getSelectedToggle()).getText().trim(), ((RadioButton)qualityGroup.getSelectedToggle()).getText().trim(), curr.getUname(), generate_price(priceInput.getText().trim(), ((RadioButton)qualityGroup.getSelectedToggle()).getText()));
            auto_price_label.setText(String.format("Price: $%.2f ", generate_price(priceInput.getText().trim(), ((RadioButton)qualityGroup.getSelectedToggle()).getText())));
        });

        BorderPane mainLayout = new BorderPane();
        mainLayout.setStyle("-fx-background-color: #8C1D40;");
        mainLayout.setTop(top_page);
        mainLayout.setLeft(left_page);
        mainLayout.setCenter(center_page);
        mainLayout.setRight(right_page);

        BorderPane.setAlignment(top_page, Pos.CENTER);
        BorderPane.setMargin(top_page, new Insets(0, 0, 10, 0));

        Scene sc = new Scene(mainLayout, 800, 600);
        PageHandler.updateStage("Book Sell", sc);
    }

    private void listBook(String title, String genre, String condition, String seller, double price) {
        if(title.isBlank()) {showAlert("Please Enter a Title For Your Book"); return;}
        String query = "INSERT INTO books (title, genre, bookCondition, seller, price) VALUES ('" + title + "', '" + genre + "', '" + condition + "', '" + seller + "', " + price + ")";
        DataManipulator.update(query);
        Inform("Your book was listed for $" + price);
        book_title.setText("");
        auto_price_label.setText(String.format("Price: $%.2f", 0.0));
        priceInput.setText("");
    }

    private double generate_price(String price, String condition) {
        double generatedPrice = 0;
        if(price == null || price.isEmpty()) {return generatedPrice;}
        generatedPrice = Double.parseDouble(price);
        double multiplier = 1;
        if(condition.equals("Used Like New")) {multiplier = 0.9;}
        else if (condition.equals("Moderately Used")) {multiplier = 0.92;}
        else if (condition.equals("Heavily Used")) {multiplier = 0.94;}

        return Math.round(generatedPrice * multiplier * 100) / 100.0;
    }

    private String getAdminCut(String s) {
        String cut = "0";
        if(s.equals("Used Like New")) {cut = "10";}
        else if (s.equals("Moderately Used")) {cut = "8";}
        else if (s.equals("Heavily Used")) {cut = "6";}
        return cut;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait();
    }

    //Creates alert for successful save
    private void Inform(String s) {
        Alert a = new Alert(Alert.AlertType.INFORMATION, s);
        a.setTitle("Success");
        a.setHeaderText("Successfully Listed");
        a.showAndWait();
    }
}
