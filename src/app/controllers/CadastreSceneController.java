package app.controllers;

import app.dao.ProductDAO;
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
        monetaryField(tfPrice);
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




    /**
     * Monta a mascara para Moeda.
     *
     * @param textField TextField
     */
    public static void monetaryField(final TextField textField) {
        textField.setAlignment(Pos.CENTER_RIGHT);
        textField.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String value = textField.getText();
                value = value.replaceAll("[^0-9]", "");
                value = value.replaceAll("([0-9]{1})([0-9]{14})$", "$1.$2");
                value = value.replaceAll("([0-9]{1})([0-9]{11})$", "$1.$2");
                value = value.replaceAll("([0-9]{1})([0-9]{8})$", "$1.$2");
                value = value.replaceAll("([0-9]{1})([0-9]{5})$", "$1.$2");
                value = value.replaceAll("([0-9]{1})([0-9]{2})$", "$1,$2");
                textField.setText(value);
                positionCaret(textField);

                textField.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                        if (newValue.length() > 17)
                            textField.setText(oldValue);
                    }
                });
            }
        });

        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean fieldChange) {
                if (!fieldChange) {
                    final int length = textField.getText().length();
                    if (length > 0 && length < 3) {
                        textField.setText(textField.getText() + "00");
                    }
                }
            }
        });
    }

    private static void positionCaret(final TextField textField) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // Posiciona o cursor sempre a direita.
                textField.positionCaret(textField.getText().length());
            }
        });
    }


}
