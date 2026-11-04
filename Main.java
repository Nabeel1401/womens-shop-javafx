package womenshop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/womenshop/view/MainView.fxml")
        );

        Scene scene = new Scene(fxmlLoader.load(), 900, 600);

        stage.getIcons().clear();
        stage.setTitle("Women's Shop Management");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
