package net.idrok.shopping.repository;


import net.idrok.shopping.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    Page<Product> findByCategoryId(@RequestParam("id") Long id,Pageable pageable);

    Page<Product> findByDiscountPercent(@RequestParam("percent") String percent,Pageable pageable);

    Page<Product> findByNameContainingIgnoreCase(@RequestParam("name") String name, Pageable pageable);

    Page<Product> findByBrandId(@RequestParam("id") Long id, Pageable pageable);




}
