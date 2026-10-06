package dev.FelipeBarboz.crm_teste.customer.infrastructure.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "customers")
public class CustomerEntity {
    @Column(name = "id", nullable = false, unique = true)
    private UUID customerId;

    @Column(name = "name")
    private String name;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Column(name = "email")
    private String customerEmail;

    @Column(name = "updated_at")
    private LocalDateTime updated_at;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at;
}
