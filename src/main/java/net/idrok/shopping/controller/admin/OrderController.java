package net.idrok.shopping.controller.admin;
import net.idrok.shopping.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.idrok.shopping.entity.Order;
import net.idrok.shopping.service.OrderService;



@RestController
@RequestMapping("/api/order")
@CrossOrigin(maxAge = 3600)

public class OrderController {

    private final
    OrderService orderSvc;

    public OrderController(OrderService orderSvc) {
        this.orderSvc = orderSvc;
    }

    @GetMapping()
    public ResponseEntity<Page<Order>> getAll(@RequestParam(name="key", required = false) String key, Pageable pageable){
        if(key==null) key = "";
        return ResponseEntity.ok(orderSvc.getAll(pageable, key));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable Long id){
        return ResponseEntity.ok(orderSvc.getById(id));
    }

    @PostMapping()
    public ResponseEntity<Order> create(@RequestBody Order bm){
        return ResponseEntity.ok( orderSvc.create(bm));
    }

    @PutMapping()
    public ResponseEntity<Order> update(@RequestBody Order bm){
        return ResponseEntity.ok(orderSvc.update(bm));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        orderSvc.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/findByCustomerEmailOrderByDateCreatedDesc")
    public ResponseEntity<Page<Order>> findByCustomerEmailOrderByDateCreatedDesc(@RequestParam(name="email", required = false)   String email, Pageable pageable){
        return ResponseEntity.ok(orderSvc.findByCustomerEmailOrderByDateCreatedDesc(email,  pageable));
    }

    
}
