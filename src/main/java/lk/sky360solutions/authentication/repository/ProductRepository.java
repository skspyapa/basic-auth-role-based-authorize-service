package lk.sky360solutions.authentication.repository;

import lk.sky360solutions.authentication.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
