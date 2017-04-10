package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    static Stage mStage;


    @Override
    public void start(Stage primaryStage) throws Exception{
        this.mStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("view/main_scene.fxml"));
        primaryStage.setTitle("Sistema de Estoque");
        primaryStage.setScene(new Scene(root, 1000, 650));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
