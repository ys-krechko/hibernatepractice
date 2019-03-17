package com.it.dao.impl;

import com.it.dao.CustomerDAO;
import com.it.model.Customer;

public class CustomerDAOImpl extends GenericDAOImpl<Customer, Long> implements CustomerDAO {
    private static CustomerDAOImpl instance;

    private CustomerDAOImpl() {
        super(Customer.class);
    }

    public static synchronized CustomerDAOImpl getInstance() {
        if (instance == null) {
            instance = new CustomerDAOImpl();
        }
        return instance;
    }
}
