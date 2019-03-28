package com.it.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Roles", schema = "projectdb")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roles_id")
    private Long id;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "roles")
    private Set<Operators> operator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Operators> getOperator() {
        return operator;
    }

    public void setOperator(Set<Operators> operator) {
        this.operator = operator;
    }
}
