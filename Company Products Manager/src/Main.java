import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        AllProductsPane allProductsPane = new AllProductsPane();
        
        Scene scene = new Scene(allProductsPane, 1070, 640);

        stage.setTitle("Company Products Manager");
        
        stage.setScene(scene);
        
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
