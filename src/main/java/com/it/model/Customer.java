package com.it.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Customer", schema = "projectdb")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customers_id")
    private Long id;

    @Column(name = "customers_first_name")
    private String customersFirstName;

    @Column(name = "customers_last_name")
    private String customersLastName;

    @Column(name = "customers_passport_number")
    private String customersPassportNumber;

    @Column(name = "customers_contract_number")
    private String customersContractNumber;

    @Column(name = "customers_contract_date_of_signing")
    private LocalDate customersContractDateOfSigning;

    @OneToOne(mappedBy = "customer")
    private Order order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomersFirstName() {
        return customersFirstName;
    }

    public void setCustomersFirstName(String customersFirstName) {
        this.customersFirstName = customersFirstName;
    }

    public String getCustomersLastName() {
        return customersLastName;
    }

    public void setCustomersLastName(String customersLastName) {
        this.customersLastName = customersLastName;
    }

    public String getCustomersPassportNumber() {
        return customersPassportNumber;
    }

    public void setCustomersPassportNumber(String customersPassportNumber) {
        this.customersPassportNumber = customersPassportNumber;
    }

    public String getCustomersContractNumber() {
        return customersContractNumber;
    }

    public void setCustomersContractNumber(String customersContractNumber) {
        this.customersContractNumber = customersContractNumber;
    }

    public LocalDate getCustomersContractDateOfSigning() {
        return customersContractDateOfSigning;
    }

    public void setCustomersContractDateOfSigning(LocalDate customersContractDateOfSigning) {
        this.customersContractDateOfSigning = customersContractDateOfSigning;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
