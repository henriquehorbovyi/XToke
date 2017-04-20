package app.controllers;

import app.dao.ProductDAO;
import app.utils.MySceneManager;
import app.utils.PushNotifications;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by henry on 03/04/17.
 */
public class CadastreSceneController implements Initializable {

    @FXML private AnchorPane parent;
    @FXML public TextField  tfCode;
    @FXML public TextField  tfName;
    @FXML public TextField  tfPrice;
    @FXML public TextArea   taDescription;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MySceneManager.monetaryField(tfPrice);
    }

    @FXML
    private void save(){
        String code     = tfCode.getText();
        if(!ProductDAO.checkIfExist(code)){
            String name         = tfName.getText();
            String price        = tfPrice.getText();
            String description  = taDescription.getText();
            if(ProductDAO.save(code,name,price,description) == 1){
                 new PushNotifications().notify("Sistema de Estoque",
                        "Produto cadastrado com sucesso!",
                        "assets/success.png").show();
                clean();
            }else{
                System.out.println("não salvou");
            }


        }else{
            new PushNotifications().notify("Sistema de Estoque",
                    "Produto já existe!",
                    "assets/alert.png").show();
        }

    }
    @FXML
    private void clean(){
        tfCode.setText("");
        tfName.setText("");
        tfPrice.setText("");
        taDescription.setText("");
    }
    @FXML
    private void cancel(Event event){
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }


}
