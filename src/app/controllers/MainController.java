package app.controllers;

import app.dao.MyConnection;
import app.dao.ProductDAO;
import app.model.Product;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import javax.swing.*;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController implements Initializable{


    @FXML private AnchorPane rootPane;
    @FXML private ComboBox cbSearchBy;

    @FXML private TableView  tvProducts;
    @FXML private TableColumn<Product, String> codeCol;
    @FXML private TableColumn<Product, String> nameCol;
    @FXML private TableColumn<Product, String> descripCol;
    @FXML private TableColumn<Product, Double> priceCol;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillTableView();
        fillComboBoxSearch();
    }


    private void fillComboBoxSearch(){
        ObservableList<String> list = FXCollections.observableArrayList("Código","Nome","Descrição","Preço");
        cbSearchBy.setItems(list);
    }

    private void fillTableView(){
        final ObservableList<Product> data = FXCollections.observableArrayList(ProductDAO.list());
        codeCol.setCellValueFactory(cellValue -> cellValue.getValue().getBarcode());
        nameCol.setCellValueFactory(cellValue -> cellValue.getValue().getName());
        descripCol.setCellValueFactory(cellValue -> cellValue.getValue().getDescription());
        priceCol.setCellValueFactory(cellValue -> cellValue.getValue().getPrice());
        tvProducts.setItems(data);
    }

    @FXML
    private void refreshTable(){
        fillTableView();
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


    @FXML
    private void exit(){
        Alert alertExit = new Alert(Alert.AlertType.CONFIRMATION);
        alertExit.setTitle(null);
        alertExit.setHeaderText(null);
        alertExit.setContentText("Deseja realmente sair?");


        Optional<ButtonType> result = alertExit.showAndWait();
        if (result.get() == ButtonType.OK) System.exit(0);

    }


}
