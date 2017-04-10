package app.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Created by henry on 09/04/17.
 */
public class MySceneManager {



    public void changeScene(AnchorPane yourRootPane,String nextSceneUrl){
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(nextSceneUrl));
            String id       = yourRootPane.getChildren().remove(0).getId();
            yourRootPane.getChildren().addAll(pane);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void createStage(String xmlURL, String title, boolean isResizeble){
        try {
            FXMLLoader loader   = new FXMLLoader(getClass().getResource(xmlURL));
            Parent  root        = (Parent) loader.load();
            Stage   stage       = new Stage();
            stage.setTitle("");
            stage.setScene(new Scene(root));
            stage.setResizable(isResizeble);
            stage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


}
