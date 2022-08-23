package by.teachmeskills.eshop.dao;

import by.teachmeskills.eshop.domain.entities.Product;

import java.util.List;

public interface IProductRepository extends BaseRepository<Product> {
    List <Product> getProductsByCategoryId(int categoryId) throws Exception;
    Product getProductById(int productId) throws Exception;

}