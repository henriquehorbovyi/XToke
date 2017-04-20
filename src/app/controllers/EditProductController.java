package app.controllers;

import app.dao.ProductDAO;
import app.model.Product;
import app.utils.MySceneManager;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by henry on 10/04/17.
 */
public class EditProductController implements Initializable{


    private Product product;

    @FXML private TextField tfCode;
    @FXML private TextField tfName;
    @FXML private TextField tfPrice;
    @FXML private TextArea  taDescription;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MySceneManager.monetaryField(tfPrice);
        this.product = MainController.product;
        fillFields();
    }

    @FXML
    private void cancel(Event e) {((Node)e.getSource()).getScene().getWindow().hide();}

    @FXML
    private void update(ActionEvent actionEvent) {
    }

    private void fillFields(){
        System.out.println("ID > "+product.getId().get());
        this.tfCode         .setText(product.getBarcode().get());
        this.tfName         .setText(product.getName().get());
        this.tfPrice        .setText(product.getPrice().get());
        this.taDescription  .setText(product.getDescription().get());
    }

}
