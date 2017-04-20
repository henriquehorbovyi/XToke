package app.dao;

import app.model.Product;
import com.mysql.jdbc.PreparedStatement;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by henry on 04/04/17.
 */
public class ProductDAO {

    private static final int DATA_LISTING_REASON = 20;

    public static int save(String code,String name,String price,String description){
        String query = "insert into products (codigo,nome,preco,descricao) values(?,?,?,?)";
        int check = 0;
        try {
            PreparedStatement statement = (PreparedStatement) new MyConnection().connect().prepareStatement(query);
            statement.setString(1,code);
            statement.setString(2,name);
            statement.setString(3,price);
            statement.setString(4,description);
            check = statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return check;
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
                Product p = new Product(result.getString("nome"),result.getString("descricao"),
                        result.getString("codigo"),result.getString("preco"));
                p.setId(String.valueOf(result.getInt("id")));
                products.add(p);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return products;
    }

    //SEARCH
    public static boolean checkIfExist(String code){
        boolean exist = false;
        String query = "select codigo from products where codigo = ?";
        try{
            PreparedStatement statement = (PreparedStatement)  new MyConnection().connect().prepareStatement(query);
            statement.setString(1,code);
            ResultSet res               = statement.executeQuery();
            exist                       = res.next();
        }catch (Exception e){
            e.printStackTrace();
        }
        return exist;
    }


    public static Product search(int id){
        String query        = "select * from products where id = ?";
        Product product     =  null;
        try {
            PreparedStatement statement = (PreparedStatement) new MyConnection().connect().prepareStatement(query);
            ResultSet result                      = statement.executeQuery();
            while (result.next()){
                Product p = new Product(result.getString("nome"),result.getString("descricao")
                        ,result.getString("codigo"),result.getString("preco"));
                p.setId(String.valueOf(id));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return product;
    }
    public static List<Product> search(String col, String value){
        List<Product> list   = new ArrayList<>();
        String query = "select * from products where "+col+" like '"+value+"%' ";
        try {
            PreparedStatement statement = (PreparedStatement) new MyConnection().connect().prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                Product p = new Product(result.getString("nome"),result.getString("descricao")
                        ,result.getString("codigo"),result.getString("preco"));
                p.setId(String.valueOf(result.getInt("id")));
                list.add(p);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    //SEARCH
}
