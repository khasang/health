package org.health.dao.impl;

import org.health.dao.UserDao;
import org.health.entity.Role;
import org.health.entity.userdb.User;

import java.util.List;

public class UserDaoImpl extends BasicDaoImpl<User> implements UserDao {
    public UserDaoImpl(Class<User> entityClass) {
        super(entityClass);
    }


    @Override
    public List<Role> getRoleSetByUserId(long userId) {
        /*
         * Метод session.load() возвращает так называемый proxy-object. Proxy-object — это объект-посредник, через
         * который мы можем взаимодействовать с реальным объектом в БД. Он расширяет функционал объекта-сущности.
         * Взаимодействие с proxy-object полностью аналогично взаимодействию с объектом-сущностью. Proxy-object
         * отличается от объекта-сущности тем, что при создании proxy-object не выполняется ни одного запроса к БД, т.е.
         *  Hibernate просто верит нам, что объект с данным Id существует в БД. Однако первый вызванный get или set у
         * proxy-object сразу инициирует запрос select, и если объекта с данным Id нет в базе, то мы получим
         * ObjectNotFoundException. Основное предназначение proxy-object — реализация отложенной загрузки.
         */
        User user = getSession().load(User.class, userId);
        return user.getRoles();
    }


}
