package app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{


    @FXML
    private FlowPane rootPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void loadSecondScene(){
        try {
            FlowPane pane = FXMLLoader.load(getClass().getResource("secondScene.fxml"));
            rootPane.getChildren().addAll(pane);
            //Main.mStage.setTitle();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
}
