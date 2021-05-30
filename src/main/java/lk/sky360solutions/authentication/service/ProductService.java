package lk.sky360solutions.authentication.service;

import lk.sky360solutions.authentication.exception.ProductNotFoundException;
import lk.sky360solutions.authentication.model.Product;

import java.util.List;

public interface ProductService {

  List<Product> listAll();

  Product save(Product product);

  Product update(Product product, Long id) throws ProductNotFoundException;

  Product get(Long id) throws ProductNotFoundException;

  void delete(Long id) throws ProductNotFoundException;
}
