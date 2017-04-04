package app.dao;

import app.model.Product;
import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by henry on 04/04/17.
 */
public class ProductDAO {

    public static void save(){

    }
    public static List<Product> list(){
        return null;
    }
    //SEARCH
    public static List<Product> search(int id){
        String query        = "SELECT * FROM products WHERE id = ?";
        List<Product> list  =  null;
        try {
            PreparedStatement statement = (PreparedStatement) new MyConnection().connect().prepareStatement(query);
            ResultSet result                      = statement.executeQuery();
            while (result.next()){
                Product p = new Product(result.getString("name"),result.getString("description")
                        ,result.getString("barcode"),result.getDouble("price"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static List<Product> search(String nameOrDescription){
        return null;
    }
    public static List<Product> search(Double price){
        return null;
    }


    //SEARCH


}
