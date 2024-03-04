package ru.chepikov.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.chepikov.model.Customer;
import ru.chepikov.model.Order;
import ru.chepikov.model.Product;
import ru.chepikov.repository.CustomerRepository;
import ru.chepikov.repository.OrderRepository;
import ru.chepikov.repository.ProductRepository;

import javax.naming.InsufficientResourcesException;
import java.math.BigDecimal;
import java.util.List;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class OrderService {

    CustomerRepository customerRepository;
    OrderRepository orderRepository;
    ProductRepository productRepository;


    @Transactional
    public Order placeOrder(Long customerId, List<Long> productsId) throws Exception {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        List<Product> productList = productRepository.findAllById(productsId);
        BigDecimal totalAmount = productList.stream().
                map(Product::getPrice).
                reduce(BigDecimal.ZERO, BigDecimal::add);

        assert customer != null;
        if (customer.getBalance().compareTo(totalAmount) >= 0) {
            customer.setBalance(customer.getBalance().subtract(totalAmount));
        } else {
            throw new Exception("Customer does not have enough balance to place the order");
        }

        Order order = new Order();
        order.setCustomer(customer);
        order.setProducts(productList);
        order.setTotalAmount(totalAmount);
        order = orderRepository.save(order);

        return order;

    }
}
