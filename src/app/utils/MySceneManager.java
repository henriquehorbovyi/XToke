package app.utils;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
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
        Platform.runLater(() -> { textField.positionCaret(textField.getText().length()); });
    }



}
