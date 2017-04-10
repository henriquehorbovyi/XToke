package app.model;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by henry on 03/04/17.
 */
public class Product {
    private int id;
    private StringProperty name;
    private StringProperty description;
    private StringProperty barcode;
    private StringProperty price;

    public Product(){}
    public Product(String name, String description,String barcode, String price){
        this.name           = new SimpleStringProperty(name);
        this.description    = new SimpleStringProperty(description);
        this.barcode        = new SimpleStringProperty(barcode);
        this.price          = new SimpleStringProperty(price);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StringProperty getName() {
        return name;
    }
    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public StringProperty getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = new SimpleStringProperty(description);
    }

    public StringProperty getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = new SimpleStringProperty(barcode);
    }

    public StringProperty getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = new SimpleStringProperty(price);
    }
}
