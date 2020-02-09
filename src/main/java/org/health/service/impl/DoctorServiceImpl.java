package org.health.service.impl;

import org.health.dao.DoctorDao;
import org.health.entity.Role;
import org.health.entity.Specialty;
import org.health.entity.userdb.User;
import org.health.entity.userdb.extend.Doctor;
import org.health.service.DoctorService;
import org.health.service.RoleService;
import org.health.service.UserService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("doctorService")
public class DoctorServiceImpl implements DoctorService {
    private SessionFactory sessionFactory;
    private DoctorDao doctorDao;
    private UserService userService;
    private RoleService roleService;

    @Override
    public Doctor add(Doctor doctor) {
//        sessionFactory.getCurrentSession().beginTransaction();
        // 1. Проверяем есть ли в записях о докторе такой-же доктор
        // доктор в записях есть -> 2 (ели нет -> 3)

        // блок 1.1: проверяем по логину, есть ли записи о пользователе с данным логином
        User userCheckLoginFree = userService.checkLoginFree(doctor.getUser().getLogin());
        if (userCheckLoginFree == null) {
            // пользователя с данным логином нет, соответственно doctor создается вперые по указанному логину
            User userByDoctor = doctor.getUser();
            userByDoctor.addRole(doctorDao.getMyRole());
            User userAdd = userService.add(userByDoctor);



//            Doctor doctorAdd = doctorDao.addEntity(doctor);

            return null;
        }

        // блок 1.2: при наличии пользователя в записях, данный пользователь может и не быть доктором
        Doctor userByIdDoctor = doctorDao.getUserByIdDoctor(userCheckLoginFree.getId());
        if (userByIdDoctor == null) {

        }

        Role roleDoctor = doctorDao.getMyRole();

//        if (!userCheckLoginFree.getRoles().contains(roleDoctor)) { // проверка наличия указанной роли у пользователя
//            userCheckLoginFree.
//        }


        // Запись пользователя в базу
        User userAddEntity = userService.add(doctor.getUser());
        // Внесение в данные пользователя, о том что этот пользователь может быть ДОКТОРОМ


        boolean flagRoleFree = true;
        for (Role role : userAddEntity.getRoles()) {
            if (role.getName().equals(roleDoctor.getName()))
                flagRoleFree = false;
        }

        if (flagRoleFree) {
            List<Role> roleList = userAddEntity.getRoles();
            roleList.add(roleDoctor);
            userAddEntity.setRoles(roleList);
            userAddEntity = userService.updateUser(userAddEntity);
            // создание клона доктора
            Doctor doctorClone = new Doctor(doctor);
            // установка в клона доктора пользователя с id записью в базе
            doctorClone.setUser(userAddEntity);

            return doctorDao.addEntity(doctorClone);
        } else {
            Doctor userByIdDoctor1 = doctorDao.getUserByIdDoctor(userAddEntity.getId());
            List<Specialty> specialties = userByIdDoctor1.getSpecialties();
            specialties.add(doctor.getSpecialties().get(0));

            for (Specialty specialty : specialties) {
                specialty.setDoctors(new ArrayList<>());
            }

            userByIdDoctor1.setSpecialties(specialties);

            return doctorDao.updateEntity(userByIdDoctor1);
        }
    }

    @Override
    public Doctor update(Doctor doctor) {
        return doctorDao.updateEntity(doctor);
    }

    /**
     * При удалении доктора тебуется сперва удалить запись из "users_roles" ссылающаяся на то, что этот пользователь может
     * принимать роль доктора
     *
     * @param id
     * @return
     */
    @Override
    public Doctor delete(long id) {
        // Получение данных о докторе
        Doctor doctor = doctorDao.getEntity(id);
        // Получение данных о пользователе
        User user = doctor.getUser();
        // удаление данных из пользователя о том что этот пользователь может быть ДОКТОРОМ
        user.getRoles().remove(roleService.getRole(3));
        // обновление данных о пользователе
        userService.updateUser(user);
        // удаление доктора и возврат значения удаленно объекта
        return doctorDao.deleteEntity(doctor);
    }

    @Override
    public Doctor getById(long id) {
        return doctorDao.getEntity(id);
    }

    @Override
    public List<Doctor> getAll() {
        return new ArrayList<>();
    }

    @Autowired
    public void setDoctorDao(DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
