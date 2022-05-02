import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    //start javafx application
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fs3/gui/view/StudentPageView.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("Student Page");
        stage.setScene(scene);
        stage.show();
    }
}
