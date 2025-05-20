package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.tuc.ds2020.entities.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {



    Product findByName(String name);


    Product findByNameAndBrand(String name, String brand);


    List<Product> findByCategory(String category);
}
