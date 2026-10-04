package dev.FelipeBarboz.crm_teste.customer.domain;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Customer {
    private UUID customerId;
    private String name;
    private String phone;
    private String customerEmail;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Customer(UUID customerId, String name, String phone, String customerEmail, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.customerId = customerId;
        this.name = parseName(name);
        this.phone = parsePhone(phone);
        this.customerEmail = parseCustomerEmail(customerEmail);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Customer create(String name, String phone, String email, LocalDateTime createdAt, LocalDateTime updatedAt){
        return new Customer(
                UUID.randomUUID(),
                name,
                phone,
                email,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    public Customer reconstitute(UUID customerId, String name, String phone, String email, LocalDateTime createdAt, LocalDateTime updatedAt){
        return new Customer(
                customerId,
                name,
                phone,
                email,
                createdAt,
                updatedAt
        );
    }

    private String parseName(String name) {
        if (name == null || name.isBlank()) {
            throw new RuntimeException("O nome não pode ser vazio");
        }

        if (!name.matches("^(?=.*[A-Za-zÀ-ÿ].*[A-Za-zÀ-ÿ].*[A-Za-zÀ-ÿ])[A-Za-zÀ-ÿ ]+$")) {
            throw new RuntimeException("O nome deve conter pelo menos 3 letras e não pode conter números");
        }

        return name;
    }

    private String parsePhone(String phone) {
        if (phone == null || phone.isBlank()) {
            throw new RuntimeException("O telefone não pode ser vazio");
        }

        if (!phone.matches("^\\d{11}$")) {
            throw new RuntimeException("O telefone deve conter 11 dígitos");
        }

        return phone;
    }

    private String parseCustomerEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new RuntimeException("O customerEmail não pode ser vazio");
        }

        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        if (!email.matches(regex)) {
            throw new RuntimeException("Email inválido");
        }

        return email;
    }

    public void changeName(String name) {
        this.name = parseName(name);
    }

    public void changePhone(String phone) {
        this.phone = parsePhone(phone);
    }

    public void changeEmail(String email) {
        this.customerEmail = parseCustomerEmail(email);
    }
}
