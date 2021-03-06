package net.idrok.shopping.service.impl;

import net.idrok.shopping.entity.Customer;
import net.idrok.shopping.repository.CustomerRepository;
import net.idrok.shopping.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepo;

    public Page<Customer> getAll(Pageable pageable, String key) {
        return customerRepo.findAllByFirstNameOrLastNameContainingIgnoreCase(key,key,  pageable);
    }

    public Customer getById(Long id) {
        return customerRepo.findById(id).get();
    }

    public Customer create(Customer ch) {
        if (ch.getId() == null)
       return     customerRepo.save(ch);
        throw new RuntimeException("id null bolishi kerak");
    }

    public Customer update(Customer ch) {
        if (ch.getId() != null)
         return   customerRepo.save(ch);
        throw new RuntimeException("id null bolmasligi kerak");
    }

    public void delete(Customer ch) {
        customerRepo.delete(ch);
    }

    public void deleteById(Long chId) {
        customerRepo.deleteById(chId);
    }

}