package org.health.hibernate;

import org.health.config.jpa.HibernateConfig;
import org.health.entity.Specialty;
import org.hibernate.SessionFactory;
import org.junit.Test;

public class SessionTest {


    @Test
    public void SessionFactoryTest() {
        HibernateConfig hibernateConfig = new HibernateConfig();
        SessionFactory sessionFactory = (SessionFactory) hibernateConfig.sessionFactory();

        Specialty specialty = new Specialty();
        specialty.setTitle("TEST");
        sessionFactory.getCurrentSession().save(specialty);
    }
}
