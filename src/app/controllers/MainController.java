package app.controllers;

import app.dao.ProductDAO;
import app.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController implements Initializable{


    private static int INITIAL_NUMBER_OF_PRODUCTS = 0;

    @FXML private AnchorPane rootPane;
    @FXML private ComboBox cbSearchBy;

    @FXML private TableView  tvProducts;
    @FXML private TableColumn<Product, String> codeCol;
    @FXML private TableColumn<Product, String> nameCol;
    @FXML private TableColumn<Product, String> descripCol;
    @FXML private TableColumn<Product, Double> priceCol;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillTableView(INITIAL_NUMBER_OF_PRODUCTS);
        fillComboBoxSearch();
    }


    private void fillComboBoxSearch(){
        ObservableList<String> list = FXCollections.observableArrayList("Código","Nome","Descrição","Preço");
        cbSearchBy.setItems(list);
    }

    private void fillTableView(int lastId){
        final ObservableList<Product> data = FXCollections.observableArrayList(ProductDAO.list(lastId));
        codeCol.setCellValueFactory(cellValue -> cellValue.getValue().getBarcode());
        nameCol.setCellValueFactory(cellValue -> cellValue.getValue().getName());
        descripCol.setCellValueFactory(cellValue -> cellValue.getValue().getDescription());
        priceCol.setCellValueFactory(cellValue -> cellValue.getValue().getPrice());
        tvProducts.setItems(data);
    }

    @FXML
    private void addMoreItemsTable(){
        ObservableList<Product> data = tvProducts.getItems();
        int lastId = 0;
        for (int i = 0; i < data.size(); i++) lastId = data.get(i).getId();
        final ObservableList<Product> res = FXCollections.observableArrayList(ProductDAO.list(lastId));
        if(res.size() > 0) tvProducts.getItems().addAll(res);
    }

    @FXML
    private void refreshTable(){
        fillTableView(INITIAL_NUMBER_OF_PRODUCTS);
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
