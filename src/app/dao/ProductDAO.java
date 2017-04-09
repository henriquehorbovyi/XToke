package app.dao;

import app.model.Product;
import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by henry on 04/04/17.
 */
public class ProductDAO {

    private static final int DATA_LISTING_REASON = 20;

    public static void save(){
        //TODO: O METODO INTEIRO
    }

    public static List<Product> list(int lastId){
        String query = "select * from products where id > ? and id <= ?";
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement statement = (PreparedStatement) new MyConnection().connect().prepareStatement(query);
            statement.setInt(1,lastId);
            statement.setInt(2,lastId + DATA_LISTING_REASON);
            ResultSet result            = statement.executeQuery();
            while(result.next()){
                Product p = new Product(result.getString("name"),result.getString("description"),
                        result.getString("barcode"),result.getDouble("price"));
                p.setId(result.getInt("id"));
                products.add(p);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return products;
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
