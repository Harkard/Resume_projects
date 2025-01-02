import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AccountOverview {
    User curr;
    public AccountOverview(User u, String prev) {
        curr = u;
        final Color MAROON = Color.web("#8C1D40");
        final Color GOLD = Color.web("FFC627");

        Button backButton = new Button("Back");
        backButton.setFont(Font.font("Arial", 12));
        backButton.setStyle("-fx-background-color: #FFC627");
        backButton.setTextFill(MAROON);
        backButton.setPrefHeight(20);
        backButton.setOnAction(e-> {
            if(prev.equals("Buyer")) {new BookSearch(curr);}
            else if (prev.equals("Seller")) {new SellerPage(curr);}
            else if (prev.equals("Cart")) {new cart(curr);}
            else {curr = null; new LoginPage();}
        });

        Button logoutButton = new Button("Logout");
        logoutButton.setFont(Font.font("Arial", 12));
        logoutButton.setStyle("-fx-background-color: #FFC627");
        logoutButton.setTextFill(MAROON);
        logoutButton.setPrefHeight(20);
        logoutButton.setOnAction(e->{curr = null; new LoginPage();});

        HBox topBanner = new HBox(350, backButton, logoutButton);
        topBanner.setStyle("-fx-background-color: #CECECE");
        topBanner.setPrefSize(500, 30);
        topBanner.setPadding(new Insets(10));
        topBanner.setAlignment(Pos.CENTER);

        Label accountOverviewLabel = new Label("Account Overview");
        accountOverviewLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        accountOverviewLabel.setTextFill(GOLD);
        accountOverviewLabel.setUnderline(true);
        HBox accountOverviewBox = new HBox(accountOverviewLabel);
        accountOverviewBox.setAlignment(Pos.CENTER);

        Label firstName = new Label("First Name: " + u.getName().substring(0, u.getName().indexOf(" ")));
        firstName.setFont(Font.font("Arial", 15));
        firstName.setTextFill(MAROON);
        firstName.setStyle("-fx-background-color: #CECECE");
        firstName.setAlignment(Pos.CENTER);
        firstName.setPrefSize(175, 40);

        Label username = new Label("Username: " + u.getUname());
        username.setFont(Font.font("Arial", 15));
        username.setTextFill(MAROON);
        username.setStyle("-fx-background-color: #CECECE");
        username.setAlignment(Pos.CENTER);
        username.setPrefSize(175, 40);

        Label lastName = new Label("Last Name: " + u.getName().substring(u.getName().indexOf(" "), u.getName().length()));
        lastName.setFont(Font.font("Arial", 15));
        lastName.setTextFill(MAROON);
        lastName.setStyle("-fx-background-color: #CECECE");
        lastName.setAlignment(Pos.CENTER);
        lastName.setPrefSize(175, 40);

        Label password = new Label("Password: ******");
        password.setFont(Font.font("Arial", 15));
        password.setTextFill(MAROON);
        password.setStyle("-fx-background-color: #CECECE");
        password.setAlignment(Pos.CENTER);
        password.setPrefSize(175, 40);

        Button changePasswordButton = new Button("Change Password");
        changePasswordButton.setFont(Font.font("Arial", 15));
        changePasswordButton.setOnAction(e->new PasswordChange());
        changePasswordButton.setTextFill(MAROON);
        changePasswordButton.setStyle("-fx-background-color: #FFC627");
        changePasswordButton.setMinSize(60, 40);

        VBox firstColumn = new VBox(40, firstName, username);
        firstColumn.setPadding(new Insets(20, 0, 0, 10));

        VBox secondColumn = new VBox(40, lastName, password);
        secondColumn.setPadding(new Insets(20, 10, 0, 0));

        HBox columnHolder = new HBox(40, firstColumn, secondColumn);
        columnHolder.setAlignment(Pos.CENTER);

        VBox myAccount = new VBox(50, topBanner, accountOverviewBox, columnHolder, changePasswordButton);
        myAccount.setStyle("-fx-background-color: #8C1D40");
        myAccount.setAlignment(Pos.CENTER);
        myAccount.setPadding(new Insets(0, 0, 20, 0));

        Scene sc = new Scene(myAccount,500,420);
        PageHandler.updateStage("Account Overview", sc);
    }

    // Utility to Show Alerts
    private void showAlert(String message, String title, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
