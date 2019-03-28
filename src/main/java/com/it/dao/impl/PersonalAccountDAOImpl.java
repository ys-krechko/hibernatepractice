package com.it.dao.impl;

import com.it.dao.PersonalAccountDAO;
import com.it.model.PersonalAccount;

public class PersonalAccountDAOImpl extends GenericDAOImpl<PersonalAccount, Long> implements PersonalAccountDAO {
    private static PersonalAccountDAOImpl instance;

    public PersonalAccountDAOImpl() {
        super(PersonalAccount.class);
    }

    public static synchronized PersonalAccountDAOImpl getInstance() {
        if (instance == null) {
            instance = new PersonalAccountDAOImpl();
        }
        return instance;
    }
}
