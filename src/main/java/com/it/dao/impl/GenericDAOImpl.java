package com.it.dao.impl;

import com.it.dao.GenericDAO;
import com.it.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;

public abstract class GenericDAOImpl<T, U> implements GenericDAO<T, U> {
    private final Class<T> type;

    GenericDAOImpl(Class<T> type) {
        this.type = type;
    }

    @Override
    public T getOne(U id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(type, (Serializable) id);
        }
    }

    @Override
    public void delete(U id) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            T entity = session.get(type, (Serializable) id);
            if (entity != null) {
                session.delete(entity);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void save(T entity) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void update(T entity) {
        Transaction transaction = null;
        Session session = null;
        try {
            transaction = session.beginTransaction();
            session.update(entity);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }
}
