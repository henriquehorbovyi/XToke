package app.controllers;

import app.model.Product;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable{


    @FXML private AnchorPane rootPane;
    @FXML private ChoiceBox  choiceBoxSearch;

    @FXML private TableView  tvProducts;
    @FXML private TableColumn<Product, String> codeCol;
    @FXML private TableColumn<Product, String> nameCol;
    @FXML private TableColumn<Product, String> descripCol;
    @FXML private TableColumn<Product, Double> priceCol;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableView();
    }

    private void setTableView(){
        //TODO: GET VALUES FROM DB...
        final ObservableList<Product> data = FXCollections.observableArrayList(
                new Product("PRODUTO 1", "LOREM 1", "qwedadfsgsgda", 200.3),
                new Product("PRODUTO 2", "LOREM 2", "wefsfbdgjtfsgh", 244.00),
                new Product("PRODUTO 3", "LOREM 3", "43546ywrgddfafa", 30.10),
                new Product("PRODUTO 4", "LOREM 4", "wfhhfgsgndgh", 20.00)
        );
        codeCol.setCellValueFactory(cellValue -> cellValue.getValue().getBarcode());
        nameCol.setCellValueFactory(cellValue -> cellValue.getValue().getName());
        descripCol.setCellValueFactory(cellValue -> cellValue.getValue().getDescription());
        priceCol.setCellValueFactory(cellValue -> cellValue.getValue().getPrice());

        tvProducts.setItems(data);
    }


    @FXML
    private void loadSecondScene(){
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/app/view/secondScene.fxml"));
            String id = rootPane.getChildren().remove(0).getId();
            rootPane.getChildren().addAll(pane);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
