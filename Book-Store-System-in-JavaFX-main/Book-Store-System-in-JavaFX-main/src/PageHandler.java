import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PageHandler extends Application {
    private static Stage primaryStage;

    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        new LoginPage();
        primaryStage.show();
    }

    public static void updateStage(String s, Scene sc) {
        primaryStage.setTitle(s);
        primaryStage.setScene(sc);
    }
}
