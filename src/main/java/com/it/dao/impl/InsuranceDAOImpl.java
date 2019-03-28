package com.it.dao.impl;

import com.it.dao.InsuranceDAO;
import com.it.model.Insurance;

public class InsuranceDAOImpl extends GenericDAOImpl<Insurance, Long> implements InsuranceDAO {
    private static InsuranceDAOImpl instance;

    private InsuranceDAOImpl() {
        super(Insurance.class);
    }

    public static synchronized InsuranceDAOImpl getInstance() {
        if (instance == null) {
            instance = new InsuranceDAOImpl();
        }
        return instance;
    }
}
