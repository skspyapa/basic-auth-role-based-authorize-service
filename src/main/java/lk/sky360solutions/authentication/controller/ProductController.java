package lk.sky360solutions.authentication.controller;

import lk.sky360solutions.authentication.exception.ProductNotFoundException;
import lk.sky360solutions.authentication.model.Product;
import lk.sky360solutions.authentication.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "products/")
public class ProductController {

	private final ProductService productService;
/*
* role name should start with ROLE_ prefix
* */
	@Secured({"ROLE_USER", "ROLE_CREATOR", "ROLE_EDITOR", "ROLE_ADMIN"})
	@ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "findAll")
	public List<Product> viewAllProduct() {
		return productService.listAll();
	}

	@Secured({"ROLE_ADMIN", "ROLE_CREATOR"})
  @ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "save")
	public Product saveProduct(@RequestBody Product product) {
		return productService.save(product);
	}

	@Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
  @ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "edit/{id}")
	public Product editProduct(@PathVariable(name = "id") Long id,
                             @RequestBody Product product) throws ProductNotFoundException {
		return productService.update(product, id);
	}

	@Secured("ROLE_ADMIN")
  @ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = "delete/{id}")
	public void deleteProduct(@PathVariable(name = "id") Long id) throws ProductNotFoundException {
    productService.delete(id);
	}
}
