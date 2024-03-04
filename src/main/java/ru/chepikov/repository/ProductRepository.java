package ru.chepikov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chepikov.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
