package com.it.dao.impl;

import com.it.dao.HotelsDAO;
import com.it.model.Hotels;

public class HotelsDAOImpl extends GenericDAOImpl<Hotels, Long> implements HotelsDAO {
    private static HotelsDAOImpl instance;

    private HotelsDAOImpl() {
        super(Hotels.class);
    }

    public static synchronized HotelsDAOImpl getInstance() {
        if (instance == null) {
            instance = new HotelsDAOImpl();
        }
        return instance;
    }
}
