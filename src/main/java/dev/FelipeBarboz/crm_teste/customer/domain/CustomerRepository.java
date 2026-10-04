package dev.FelipeBarboz.crm_teste.customer.domain;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {
    Customer save(Customer client);
    Optional<Customer> findByCustomerId(UUID customerId);
    boolean existsByCustomerId(UUID customerId);
    boolean existsByCustomerEmail(String email);
}
