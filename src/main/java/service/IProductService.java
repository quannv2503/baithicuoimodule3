package service;

import model.Category;
import model.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductService {
    public void insertProduct(Product product) throws SQLException;

    public List<Product> selectAllProduct();

    public boolean updateProduct(Product product) throws SQLException;

    public Category getCategoryById(int id);

    public List<Product> seachByName(String name);
}
