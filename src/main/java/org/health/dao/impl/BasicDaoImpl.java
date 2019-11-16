package org.health.dao.impl;

import org.health.dao.BasicDao;
import org.health.dao.IGettingID;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class BasicDaoImpl<T extends IGettingID> implements BasicDao<T> {
    private final Class<T> entityClass;
    protected SessionFactory sessionFactory;

    public BasicDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T addEntity(T entity) {
        getSession().save(entity);
        return getSession().get(entityClass, entity.getId());
    }

    @Override
    public T updateEntity(T entity) {
        getSession().update(entity);
        return getSession().get(entityClass, entity.getId());
    }

    @Override
    public T getEntity(long id) {
        return getSession().get(entityClass, id);
    }

    @Override
    public List<T> getAllEntities() {
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root);
        return getSession().createQuery(criteriaQuery).list();
    }

    @Override
    public T deleteEntity(T entity) {
        getSession().delete(entity);
        return entity;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
