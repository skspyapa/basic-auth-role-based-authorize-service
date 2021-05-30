package lk.sky360solutions.authentication.controller;

import lk.sky360solutions.authentication.exception.ProductNotFoundException;
import lk.sky360solutions.authentication.model.Product;
import lk.sky360solutions.authentication.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "findAll")
	public List<Product> viewAllProduct() {
		return productService.listAll();
	}

  @ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "save")
	public Product saveProduct(@RequestBody Product product) {
		return productService.save(product);
	}

  @ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "edit/{id}")
	public Product editProduct(@PathVariable(name = "id") Long id,
                             @RequestBody Product product) throws ProductNotFoundException {
		return productService.update(product, id);
	}

  @ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = "delete/{id}")
	public void deleteProduct(@PathVariable(name = "id") Long id) throws ProductNotFoundException {
    productService.delete(id);
	}
}
