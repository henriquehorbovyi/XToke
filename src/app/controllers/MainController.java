package app.controllers;
import app.dao.ProductDAO;
import app.model.Product;
import app.utils.MySceneManager;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
public class MainController implements Initializable{

    protected static Product product;
    private static int INITIAL_NUMBER_OF_PRODUCTS = 0;

    @FXML private ComboBox      cbSearchBy;
    @FXML private TextField     searchField;
    @FXML private AnchorPane    rootPane;
    @FXML private TableView     tvProducts;

    @FXML private TableColumn<Product, String> id;
    @FXML private TableColumn<Product, String> codeCol;
    @FXML private TableColumn<Product, String> nameCol;
    @FXML private TableColumn<Product, String> descripCol;
    @FXML private TableColumn<Product, String> priceCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillTableView(INITIAL_NUMBER_OF_PRODUCTS);
        fillComboBoxSearch();
    }


    private void fillComboBoxSearch(){
        ObservableList<String> list = FXCollections.observableArrayList("Código","Nome","Descrição","Preço");
        cbSearchBy.setItems(list);
        cbSearchBy.getSelectionModel().select(0);
    }

    private void fillTableView(int lastId){
        final ObservableList<Product> data = FXCollections.observableArrayList(ProductDAO.list(lastId));

        id      .setCellValueFactory(cellValue -> cellValue.getValue().getId() );
        codeCol .setCellValueFactory(cellValue -> cellValue.getValue().getBarcode());
        nameCol .setCellValueFactory(cellValue -> cellValue.getValue().getName());
        descripCol.setCellValueFactory(cellValue -> cellValue.getValue().getDescription());
        priceCol.setCellValueFactory(cellValue -> cellValue.getValue().getPrice());
        tvProducts.setItems(data);

        tvProducts.setRowFactory(tv -> {
            TableRow<Product> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Product product = row.getItem();
                    this.product = product;
                    loadEditItemScene();
                }
            });
            return row;
        });

    }

    @FXML
    private void search(){
        String col = cbSearchBy.getSelectionModel().getSelectedItem()
                .toString()
                .toLowerCase().replaceAll("ç","c").replaceAll("ã","a")
                .replaceAll("ó","o");
        String value = searchField.getText();
        tvProducts.setItems(FXCollections.observableArrayList(ProductDAO.search(col,value)));
    }

    @FXML
    private void addMoreItemsTable(){
        ObservableList<Product> data = tvProducts.getItems();
        int lastId = 0;
        for (int i = 0; i < data.size(); i++) lastId = Integer.parseInt(data.get(i).getId().get());
        final ObservableList<Product> res = FXCollections.observableArrayList(ProductDAO.list(lastId));
        if(res.size() > 0) tvProducts.getItems().addAll(res);
    }
    @FXML
    private void refreshTable(){
        fillTableView(INITIAL_NUMBER_OF_PRODUCTS);
        cbSearchBy.getSelectionModel().select(0);
        searchField.setText("");
    }




    @FXML
    private void loadCadastreScene(){
        MySceneManager sceneManager = new MySceneManager();
        sceneManager.createStage("/app/view/cadastre_scene.fxml","Novo Produto",false);
    }
    private void loadEditItemScene(){
        MySceneManager sceneManager = new MySceneManager();
        sceneManager.createStage("/app/view/edit_product_scene.fxml","Editar Produto",false);
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
