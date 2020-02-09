package org.health.service.impl;

import org.health.dao.AdminDao;
import org.health.entity.userdb.User;
import org.health.entity.userdb.extend.Admin;
import org.health.model.PasswordEncoderData;
import org.health.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adminService")
public class AdminServiceImpl implements AdminService {
    private PasswordEncoderData passwordEncoderData;
    private AdminDao adminDao;

    @Override
    public Admin addAdmin(Admin admin) {
        User userAddEncodePassword = passwordEncoderData.encodeUserPassword(admin.getUser());
        Admin adminUserAddEncodePassword = new Admin(admin);
        adminUserAddEncodePassword.setUser(userAddEncodePassword);

        return adminDao.addEntity(adminUserAddEncodePassword);
    }

    @Autowired
    public void setUserService(PasswordEncoderData passwordEncoderData) {
        this.passwordEncoderData = passwordEncoderData;
    }

    @Autowired
    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }
}
