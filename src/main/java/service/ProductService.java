package service;

import connect.Connect;
import model.Category;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService {
    Connect connect = new Connect();

    public void insertProduct(Product product) throws SQLException {
        String INSERT_PRODUCT_SQL = "INSERT INTO product" + "  (name, price, quantity,color,description,category_id) VALUES " +
                " (?, ?, ?,?, ?, ?);";
        try (Connection connection = connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6, product.getCategory().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public List<Product> selectAllProduct() {

        List<Product> products = new ArrayList<>();
        String SELECT_ALL_PRODUCTS = "select * from product";

        try (Connection connection = connect.getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                String color = rs.getString("color");
                String description = rs.getString("description");
                int category_id = rs.getInt("category_id");
                Category category = getCategoryById(category_id);
                products.add(new Product(id, name, price, quantity, color, description, category));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
    }


    public List<Product> seachByName(String name) {
        String seach = "select * from product where name like concat('%',?,'%');";
        List<Product> products = new ArrayList<>();
        try (Connection connection = connect.getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(seach);) {
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name2 = rs.getString("name");
                double price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                String color = rs.getString("color");
                String description = rs.getString("description");
                int category_id = rs.getInt("category_id");
                Category category = getCategoryById(category_id);
                products.add(new Product(id, name2, price, quantity, color, description, category));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
    }

    public boolean deleteProduct(int id) throws SQLException {
        String delete = "delete from product where id=?";
        boolean rowDeleted;
        try (Connection connection = connect.getConnection(); PreparedStatement statement = connection.prepareStatement(delete);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }


    public boolean updateProduct(Product product) throws SQLException {
        String UPDATE_USERS_SQL = "update product set name = ?,price= ?, quantity =?,color= ?,description= ?,category_id= ? where id = ?;";
        boolean rowUpdated;
        try (Connection connection = connect.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getQuantity());
            statement.setString(4, product.getColor());
            statement.setString(5, product.getDescription());
            statement.setInt(6, product.getCategory().getId());
            statement.setInt(7, product.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public Category getCategoryById(int id) {
        Category category = null;
        String query = "{CALL get_category_by_id(?)}";
        try (Connection connection = connect.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setInt(1, id);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                int id1 = rs.getInt("id");
                String name = rs.getString("name");
                category = new Category(id1, name);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return category;
    }

    public Product getProductById(int id) {
        Product product = null;
        String query = "{CALL get_product_by_id(?)}";
        try (Connection connection = connect.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setInt(1, id);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                int id1 = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String color = rs.getString("color");
                String description = rs.getString("description");
                int category_id = rs.getInt("category_id");
                Category category = getCategoryById(category_id);
                product = new Product(id1, name, price, quantity, color, description, category);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return product;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
