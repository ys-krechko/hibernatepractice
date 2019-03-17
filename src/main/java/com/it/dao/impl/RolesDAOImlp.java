package com.it.dao.impl;

import com.it.dao.RolesDAO;
import com.it.model.Roles;

public class RolesDAOImlp extends GenericDAOImpl<Roles, Long> implements RolesDAO {
    private static RolesDAOImlp instance;

    private RolesDAOImlp() {
        super(Roles.class);
    }

    public static synchronized RolesDAOImlp getInstance() {
        if (instance == null) {
            instance = new RolesDAOImlp();
        }
        return instance;
    }
}
