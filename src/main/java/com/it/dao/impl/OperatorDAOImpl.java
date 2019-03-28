package com.it.dao.impl;

import com.it.dao.OperatorDAO;
import com.it.model.Operators;

public class OperatorDAOImpl extends GenericDAOImpl<Operators, Long> implements OperatorDAO {
    private static OperatorDAOImpl instance;

    private OperatorDAOImpl() {
        super(Operators.class);
    }

    public static synchronized OperatorDAOImpl getInstance() {
        if (instance == null) {
            instance = new OperatorDAOImpl();
        }
        return instance;
    }
}
