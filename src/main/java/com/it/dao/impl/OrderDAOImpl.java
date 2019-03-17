package com.it.dao.impl;

import com.it.dao.OrderDAO;
import com.it.model.Order;

public class OrderDAOImpl extends GenericDAOImpl<Order, Long> implements OrderDAO {
    private static OrderDAOImpl instance;

    private OrderDAOImpl() {
        super(Order.class);
    }

    public static synchronized OrderDAOImpl getInstance() {
        if (instance == null) {
            instance = new OrderDAOImpl();
        }
        return instance;
    }
}
