import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Alert.AlertType;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPage {
    private final Color MAROON = Color.web("#8C1D40");
    private TextField usernameField;
    private PasswordField passwordField;

    public LoginPage() {
        //Title
        Label titleLabel = new Label("Sparky Book Service");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleLabel.setTextFill(MAROON);

        //Sets up user name text field
        usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setPadding(new Insets(5,20,5,20));
        usernameField.setStyle("-fx-background-radius: 20;");
        usernameField.textProperty().addListener(((o, olduName, newuName) -> usernameField.setText(newuName.trim())));


        //sets up password field
        passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setPadding(new Insets(5,20,5,20));
        passwordField.setStyle("-fx-background-radius: 20;");
        passwordField.textProperty().addListener(((o, oldPass, newPass) -> passwordField.setText(newPass.trim())));

        //Sets up login button
        Button loginButton = new Button("Login");
        loginButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        loginButton.setStyle("-fx-background-color: #001239; -fx-background-radius: 20;");
        loginButton.setTextFill(MAROON);
        loginButton.setPrefWidth(70);
        loginButton.setOnAction(e->onLoginAttempt());

        Hyperlink forgotPasswordLink = new Hyperlink("Forgot Password?");
        forgotPasswordLink.setOnAction(e->new PasswordChange());
        forgotPasswordLink.setTextFill(MAROON);

        //Sets up register link
        Hyperlink registerLink = new Hyperlink("Register");
        registerLink.setOnAction(e->new RegisterPage());
        registerLink.setTextFill(MAROON);

        //Sets up the vbox that holds the ui elements
        VBox loginBox = new VBox(10, usernameField, passwordField, loginButton, forgotPasswordLink, registerLink);
        loginBox.setAlignment(Pos.CENTER);
        loginBox.setPadding(new Insets(20));
        loginBox.setStyle("-fx-background-color: #CECECE; -fx-background-radius: 20;");

        //Sets the stage to the login page
        VBox loginPage = new VBox(titleLabel, loginBox);
        loginPage.setAlignment(Pos.CENTER);
        loginPage.setPadding(new Insets(20));
        loginPage.setStyle("-fx-background-color: #FFC627;");
        Scene sc = new Scene(loginPage, 500, 400);
        PageHandler.updateStage("Login Page", sc);
    }

    //Handles login logic from database
    private void onLoginAttempt() {
        if(usernameField.getText().isBlank() || passwordField.getText().isBlank()){showAlert("Missing Fields"); return;}
        User u;
        String query = "SELECT * FROM users WHERE username = '" + usernameField.getText().trim() + "' LIMIT 1";
        ResultSet rs = DataManipulator.query(query);
        try {
            rs.next();
            u = new User(rs.getString("fullName"), rs.getString("username"), rs.getString("pass"), rs.getString("email"), rs.getString("userType"));
            if(u.validateUser(usernameField.getText().trim(), passwordField.getText().trim())) {
                if(u.getUserType().equals("Seller")){new SellerPage(u);}
                else{new BookSearch(u);}
            }
            else {showAlert("Wrong Username or Password");}
            rs.close();
        } catch (SQLException e) {showAlert("Wrong Username or Password");}
    }

    //Handles error messages
    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR, message);
        alert.showAndWait();
    }
}
