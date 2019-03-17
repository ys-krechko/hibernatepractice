package com.it.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Personal account", schema = "projectdb")
public class PersonalAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personal_account_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "operator_id", referencedColumnName = "operator_id", nullable = false)
    private Operators operator;

    @OneToMany(mappedBy = "personalAccount")
    private Set<Order> orders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Operators getOperator() {
        return operator;
    }

    public void setOperator(Operators operator) {
        this.operator = operator;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
