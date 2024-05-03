package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	 @Override
	    public void start(Stage primaryStage) throws Exception {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddBook.fxml"));
	        Parent root = loader.load();
	        AddBookControlor controller = loader.getController();
	        controller.initialize(getClass().getResource("AddBook.fxml"), null);
	        primaryStage.setTitle("Book Application");
	        Scene scene = new Scene(root, 800, 500);
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }
	    public static void main(String[] args) {
	        launch(args);
	    }
}