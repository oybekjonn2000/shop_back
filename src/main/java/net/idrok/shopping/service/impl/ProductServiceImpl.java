package net.idrok.shopping.service.impl;

import net.idrok.shopping.entity.Product;
import net.idrok.shopping.entity.User;
import net.idrok.shopping.repository.ProductRepository;
import net.idrok.shopping.repository.ProvinceRepository;
import net.idrok.shopping.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;


@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProvinceRepository provinceRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              ProvinceRepository provinceRepository) {
        this.productRepository = productRepository;
        this.provinceRepository = provinceRepository;
    }

    @Override
    public Page<Product> getAll(Pageable pageable, String key) {
        return productRepository.findByNameContainingIgnoreCase(key, pageable);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Product create(Product product) {
        if (product.getId() == null) {
            product.setDateCreated(LocalDateTime.now());
            product.setLastUpdated(LocalDateTime.now());
            product.setActive(true);
            product.setRasm(product.getRasm());
            return productRepository.save(product);

        }
        throw new RuntimeException("id null bolishi kerak");
    }

    @Override
    public Product update(Product product) {
        product.setRasm(product.getRasm());
        if (product.getId() != null)
            return productRepository.save(product);
        throw new RuntimeException("id must not be null ");
    }

    @Override
    public void delete(Product entity) {
        productRepository.delete(entity);
    }

    @Override
    public void deleteById(Long entityId) {
        productRepository.deleteById(entityId);
    }


    @Override
    public Page<Product> getByCategoryId(Long id, Pageable pageable) {
        return productRepository.findByCategoryId(id, pageable);
    }

    @Override
    public Page<Product> getByDiscountPercent(String percent, Pageable pageable) {
        return productRepository.findByDiscountPercent(percent, pageable);
    }

    @Override
    public Page<Product> getByBrandId(Long id, Pageable pageable) {
        return productRepository.findByBrandId(id, pageable);
    }


}
