package org.health.dao.impl;

import org.health.dao.DoctorDao;
import org.health.entity.Role;
import org.health.entity.userdb.User;
import org.health.entity.userdb.extend.Doctor;
import org.health.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class DoctorDaoImpl extends BasicDaoImpl<Doctor> implements DoctorDao {
    private RoleService roleService;

    public DoctorDaoImpl(Class<Doctor> entityClass) {
        super(entityClass);
    }

    @Override
    public Doctor getUserByIdDoctor(long userId) {
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<Doctor> userLoginCriteria = builder.createQuery(Doctor.class);
        Root<Doctor> userLoginRoot = userLoginCriteria.from(Doctor.class);
        userLoginCriteria.select(userLoginRoot);
        userLoginCriteria.where(builder.equal(userLoginRoot.get("user"), userId));

        List<Doctor> resultList = sessionFactory.openSession().createQuery(userLoginCriteria).getResultList();
        return resultList.size() == 0
                ? null
                : resultList.get(0);
    }

    @Override
    public Role getMyRole() {
        return roleService.getRole(3);
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
}
