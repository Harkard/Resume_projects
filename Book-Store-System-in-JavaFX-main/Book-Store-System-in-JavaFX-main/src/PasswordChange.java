import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PasswordChange {
    private final Color MAROON = Color.web("#8C1D40");
    private TextField usernameField;
    private PasswordField passwordField;

    public PasswordChange() {
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

        //Sets up changePassword button
        Button changePasswordButton = new Button("Change Password");
        changePasswordButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        changePasswordButton.setStyle("-fx-background-color: #001239; -fx-background-radius: 20;");
        changePasswordButton.setTextFill(MAROON);
        changePasswordButton.setPrefWidth(150);
        changePasswordButton.setOnAction(e->onChangePasswordAttempt());

        //Sets up register link
        Hyperlink registerLink = new Hyperlink("Register");
        registerLink.setOnAction(e->new RegisterPage());
        registerLink.setTextFill(MAROON);

        //Sets up login link
        Hyperlink loginLink = new Hyperlink("Back to Login");
        loginLink.setOnAction(e->new LoginPage());
        loginLink.setTextFill(MAROON);

        //Sets up the vbox that holds the ui elements
        VBox changePasswordBox = new VBox(10, usernameField, passwordField, changePasswordButton, loginLink, registerLink);
        changePasswordBox.setAlignment(Pos.CENTER);
        changePasswordBox.setPadding(new Insets(20));
        changePasswordBox.setStyle("-fx-background-color: #CECECE; -fx-background-radius: 20;");

        //Sets the stage to the changePassword page
        VBox changePasswordPage = new VBox(titleLabel, changePasswordBox);
        changePasswordPage.setAlignment(Pos.CENTER);
        changePasswordPage.setPadding(new Insets(20));
        changePasswordPage.setStyle("-fx-background-color: #FFC627;");
        Scene sc = new Scene(changePasswordPage, 500, 400);
        PageHandler.updateStage("Change Password", sc);
    }

    //Handles changePassword logic from database
    private void onChangePasswordAttempt() {
        if(usernameField.getText().isBlank() || passwordField.getText().isBlank()){showAlert("Missing Fields"); return;}
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        String query = "SELECT * FROM users WHERE username = '" + username + "' LIMIT 1";
        ResultSet rs = DataManipulator.query(query);
        try {
            rs.next();
            if(!rs.getString("username").equals(username)) {showAlert("No User With That Username");rs.close();return;}
            rs.close();
        } catch (SQLException e) {}

        String query2 = "INSERT INTO users (username) VALUES ('" + username + "') ON DUPLICATE KEY UPDATE pass = '" + password + "'";
        DataManipulator.update(query2);
        Inform("Your Password Was Successfully Changed");
    }

    //Handles error messages
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait();
    }

    //Creates alert for successful save
    private void Inform(String s) {
        Alert a = new Alert(Alert.AlertType.INFORMATION, s);
        a.setTitle("Success");
        a.setHeaderText("Successfully Changed");
        a.setOnHidden(e->new LoginPage());
        a.showAndWait();
    }
}
