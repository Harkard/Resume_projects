import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegisterPage {
    private final Color MAROON = Color.web("#8C1D40");
    private ArrayList<TextField> list;
    private PasswordField passwordField;
    CheckBox buyerBox, sellerBox;
    public RegisterPage() {
        list = new ArrayList<TextField>();
        //Sets the title for the page
        Text title = new Text("Sparky Book Service Account Registration");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        title.setFill(MAROON);

        //Sets up buyer Box
        buyerBox = new CheckBox("Buyer");
        buyerBox.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        buyerBox.setTextFill(Color.BLACK);

        //Sets up seller Box
        sellerBox = new CheckBox("Seller");
        sellerBox.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        sellerBox.setTextFill(Color.BLACK);

        //Holds the Boxs
        HBox header = new HBox(20, buyerBox, sellerBox);
        header.setAlignment(Pos.CENTER);

        //Everything that sets up the email ui part
        Label emailLabel = new Label("Email");
        emailLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField emailField = new TextField();
        emailField.setPromptText("Enter Email");
        emailField.setPadding(new Insets(5,10,5,10));
        emailField.setStyle("-fx-background-radius: 10;");
        VBox emailHolder = new VBox(emailLabel,emailField);
        emailField.textProperty().addListener(((o, __, newEmail) -> emailField.setText(newEmail.trim())));

        //Everything that sets up the first name ui part
        Label fNameLabel = new Label("First Name");
        fNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField fNameField = new TextField();
        fNameField.setPromptText("Enter First Name");
        fNameField.setPadding(new Insets(5,10,5,10));
        fNameField.setStyle("-fx-background-radius: 10;");
        VBox fNameHolder = new VBox(fNameLabel, fNameField);
        fNameField.textProperty().addListener(((o, oldfName, newfName) -> fNameField.setText(newfName.trim())));

        //Everything that sets up the last name ui part
        Label lNameLabel = new Label("Last Name");
        lNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField lNameField = new TextField();
        lNameField.setPromptText("Enter Last Name");
        lNameField.setPadding(new Insets(5,10,5,10));
        lNameField.setStyle("-fx-background-radius: 10;");
        VBox lNameHolder = new VBox(lNameLabel, lNameField);
        lNameField.textProperty().addListener(((o, oldlName, newlName) -> lNameField.setText(newlName.trim())));

        //Everything that sets up the username ui part
        Label usernameLabel = new Label("Username");
        usernameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter Username");
        usernameField.setPadding(new Insets(5,10,5,10));
        usernameField.setStyle("-fx-background-radius: 10;");
        VBox usernameHolder = new VBox(usernameLabel, usernameField);
        usernameField.textProperty().addListener(((o, olduName, newuName) -> usernameField.setText(newuName.trim())));

        //Everything that sets up the password ui part
        Label passwordLabel = new Label("Password");
        passwordLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");
        passwordField.setPadding(new Insets(5,10,5,10));
        passwordField.setStyle("-fx-background-radius: 10;");
        VBox passwordHolder = new VBox(passwordLabel, passwordField);
        passwordField.textProperty().addListener(((o, __, newPass) -> passwordField.setText(newPass.trim())));

        //Everything that sets up the login ui part
        Hyperlink loginLink = new Hyperlink("Already have an account? Login");
        loginLink.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        loginLink.setTextFill(MAROON);
        loginLink.setOnAction(e->new LoginPage());

        //Holds the text fields and related ui elements
        VBox formContainer = new VBox(15, emailHolder, fNameHolder, lNameHolder, usernameHolder, passwordHolder, loginLink);
        formContainer.setAlignment(Pos.CENTER);
        formContainer.setPadding(new Insets(20));
        formContainer.setStyle("-fx-background-color: #CECECE; -fx-background-radius: 20;");

        //Everything that sets up the register button ui part
        Button registerButton = new Button("Register");
        registerButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        registerButton.setStyle("-fx-background-color: #001239; -fx-background-radius: 20;");
        registerButton.setTextFill(MAROON);
        registerButton.setOnAction(e->RegisterAccount());

        list.add(emailField);
        list.add(fNameField);
        list.add(lNameField);
        list.add(usernameField);

        //Sets the stage
        VBox holder = new VBox(20, title, header, formContainer, registerButton);
        holder.setAlignment(Pos.CENTER);
        holder.setPadding(new Insets(20));
        holder.setStyle("-fx-background-color: #FFC627;");
        Scene sc = new Scene(holder, 500, 550);
        PageHandler.updateStage("Register Page", sc);
    }

    private void RegisterAccount() {
        if((!buyerBox.isSelected() && !sellerBox.isSelected()) || passwordField.getText().isBlank()) {showAlert("Missing Fields"); return;}

        String type = (buyerBox.isSelected() && sellerBox.isSelected()) ? "Buyer and Seller" : (buyerBox.isSelected()) ? "Buyer" : "Seller";
        boolean empty = false;
        for(TextField t : list) {
            empty = t.getText().isBlank();
            if(empty) {showAlert("Missing Fields"); return;}
        }
        String email = list.get(0).getText().trim();
        String fName = list.get(1).getText().trim();
        String lName = list.get(2).getText().trim();
        String username = list.get(3).getText().trim();
        String password = passwordField.getText().trim();

        String query = "SELECT * FROM users WHERE username = '" + username + "' LIMIT 1";
        ResultSet rs = DataManipulator.query(query);
        try {
            rs.next();
            if(rs.getString("username").equals(username)) {showAlert("Duplicate Username");rs.close();return;}
            rs.close();
        } catch (SQLException e) {}

        String query2 = "SELECT * FROM users WHERE email = '" + email + "' LIMIT 1";
        ResultSet rs2 = DataManipulator.query(query2);
        try {
            rs2.next();
            if(rs2.getString("email").equals(email)) {showAlert("User With That Email Already Exists");rs2.close();return;}
            rs2.close();
        } catch (SQLException e) {}

        String s = "INSERT INTO users (email, fullName, username, pass, userType) " +  "VALUES  ('" + email + "', '" + fName + " " + lName + "', '" + username + "', '" + password + "', '" + type + "')";
        DataManipulator.update(s);
        Inform("Your Account Was Successfully Created");
    }

    //Creates Alert
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait();
    }

    //Creates alert for successful save
    private void Inform(String s) {
        Alert a = new Alert(Alert.AlertType.INFORMATION, s);
        a.setTitle("Success");
        a.setHeaderText("Successfully Registered");
        a.setOnHidden(e->new LoginPage());
        a.showAndWait();
    }
}
