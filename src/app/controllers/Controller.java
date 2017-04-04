package app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{


    @FXML
    private AnchorPane rootPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void loadSecondScene(){
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("secondScene.fxml"));
            String id = rootPane.getChildren().remove(0).getId();
            System.out.println(id);
            rootPane.getChildren().addAll(pane);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
}
