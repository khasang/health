package org.health.dao.impl;

import org.health.dao.BasicDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

/**
 * <p>Объект-сущность может находиться в одном из 3-х состояний (статусов):</p>
 * <p>1. <b>transient object</b> - это заполненные экземпляры классов-сущностей. Могут быть сохранены в БД. Не присоединены
 * к сессии. Поле Id не должно быть заполнено, иначе объект имеет статус detached;</p>
 * <p>2. <b>persistent object</b> — это называемая хранимая сущность, которая присоединена к конкретной
 * сессии. Только в этом статусе объект взаимодействует с базой данных. При работе с объектом данного типа в рамках
 * транзакции все изменения объекта записываются в базу;</p>
 * <p>3. <b>detached object</b> — это объект, отсоединённый от сессии, может существовать или не
 * существовать в БД.</p>
 * <br>
 * <p>Любой объект-сущность можно переводить из одного статуса в другой. Для этого в интерфейсе Session существуют
 * следующие методы:</p>
 * <p>1. <b>persist(Object)</b> — преобразует объект из transient в persistent, то есть присоединяет к сессии и сохраняет в БД.
 * Однако, если мы присвоим значение полю Id объекта, то получим PersistentObjectException — Hibernate посчитает,
 * что объект detached, т. е. существует в БД. При сохранении метод persist() сразу выполняет insert, не делая select.</p>
 * <p>2. <b>merge(Object)</b> — преобразует объект из transient или detached в persistent. Если из transient, то работает
 * аналогично persist() (генерирует для объекта новый Id, даже если он задан), если из detached — загружает объект из
 * БД, присоединяет к сессии, а при сохранении выполняет запрос update</p>
 * <p>3. <b>replicate(Object, ReplicationMode)</b> — преобразует объект из detached в persistent, при этом у объекта обязательно
 * должен быть заранее установлен Id. Данный метод предназначен для сохранения в БД объекта с заданным Id, чего не
 * позволяют сделать persist() и merge(). Если объект с данным Id уже существует в БД, то поведение определяется
 * согласно правилу из перечисления org.hibernate.ReplicationMode:</p>
 * <p><b>ReplicationMode.IGNORE</b> — ничего не меняется в базе.</p>
 * <p><b>ReplicationMode.OVERWRITE</b> — объект сохраняется в базу вместо существующего.</p>
 * <p><b>ReplicationMode.LATEST_VERSION</b> — в базе сохраняется объект с последней версией.</p>
 * <p><b>ReplicationMode.EXCEPTION</b> — генерирует исключение.</p>
 * <p>4. <b> delete(Object) </b>— удаляет объект из БД, иными словами, преобразует persistent в transient. Object может быть в
 * любом статусе, главное, чтобы был установлен Id.</p>
 * <p>5. <b>save(Object)</b> — сохраняет объект в БД, генерируя новый Id, даже если он установлен. Object может быть в
 * статусе transient или detached</p>
 * <p>6. <b>update(Object)</b> — обновляет объект в БД, преобразуя его в persistent (Object в статусе detached)</p>
 * <p>7. <b>saveOrUpdate(Object)</b> — вызывает save() или update()</p>
 * <p>8. <b>refresh(Object)</b> — обновляет detached-объект, выполнив select к БД, и преобразует его в persistent</p>
 * <p>9. <b>get(Object.class, id)</b> — получает из БД объект класса-сущности с определённым Id в статусе persistent</p>
 */
@Transactional
public class BasicDaoImpl<T> implements BasicDao<T> {
    private final Class<T> entityClass;
    protected SessionFactory sessionFactory;

    public BasicDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T addEntity(T entity) {
        getSession().save(entity);
        return entity;
    }

    @Override
    public T updateEntity(T entity) {
        getSession().update(entity);
        return entity;
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
