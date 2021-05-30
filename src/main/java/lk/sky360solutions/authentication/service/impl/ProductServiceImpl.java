package lk.sky360solutions.authentication.service.impl;

import lk.sky360solutions.authentication.exception.ProductNotFoundException;
import lk.sky360solutions.authentication.model.Product;
import lk.sky360solutions.authentication.repository.ProductRepository;
import lk.sky360solutions.authentication.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public List<Product> listAll() {
    return productRepository.findAll();
  }

  @Override
  public Product save(Product product) {
    return productRepository.save(product);
  }

  @Override
  public Product update(Product product, Long id) throws ProductNotFoundException {

    Product savedProduct = get(id);
    savedProduct.setName(product.getName());
    savedProduct.setBrand(product.getBrand());
    savedProduct.setMadein(product.getMadein());
    savedProduct.setPrice(product.getPrice());

    return productRepository.save(savedProduct);
  }

  @Override
  public Product get(Long id) throws ProductNotFoundException {
    return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(
      "No product found under id [{"+ id + "}]"));
  }

  @Override
  public void delete(Long id) throws ProductNotFoundException {
    Product product = get(id);
    productRepository.deleteById(product.getId());
  }
}
