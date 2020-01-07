package org.health.dao.impl;

import org.health.dao.BasicDao;

public class PersonDaoImpl extends BasicDaoImpl<Person> implements BasicDao<Person> {
    public PersonDaoImpl(Class<Person> entityClass) {
        super(entityClass);
    }
}
