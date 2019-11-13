package org.health.config;

import org.health.dao.EmployeeDao;
import org.health.dao.ExaminationDao;
import org.health.dao.HorseDao;
import org.health.dao.InspectionDao;
import org.health.dao.ResultExaminationDao;
import org.health.dao.RoleDao;
import org.health.dao.UserDao;
import org.health.dao.impl.EmployeeDaoImpl;
import org.health.dao.impl.ExaminationDaoImpl;
import org.health.dao.impl.HorseDaoImpl;
import org.health.dao.impl.InspectionDaoImpl;
import org.health.dao.impl.ResultExaminationDaoImpl;
import org.health.dao.impl.RoleDaoImpl;
import org.health.dao.impl.UserDaoImpl;
import org.health.entity.Employee;
import org.health.entity.Examination;
import org.health.entity.Horse;
import org.health.entity.Inspection;
import org.health.entity.ResultExamination;
import org.health.entity.Role;
import org.health.entity.User;
import org.health.model.Dog;
import org.health.model.PasswordEncoderData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableAspectJAutoProxy
public class AppConfig {

    @Bean
    public Dog dog() {
        // any logic
        return new Dog("Sharik", "sweet");
    }

    @Bean
    public HorseDao horseDao() {
        return new HorseDaoImpl(Horse.class);
    }

    @Bean
    public EmployeeDao employeeDao() {
        return new EmployeeDaoImpl(Employee.class);
    }

    @Bean
    public UserDao userDao() {
        return new UserDaoImpl(User.class);
    }

    @Bean
    public RoleDao roleDao() {
        return new RoleDaoImpl(Role.class);
    }

    @Bean
    public InspectionDao inspectionDao() {
        return new InspectionDaoImpl(Inspection.class);
    }

    @Bean
    public ResultExaminationDao resultExaminationDao() {
        return new ResultExaminationDaoImpl(ResultExamination.class);
    }

    @Bean
    public ExaminationDao examinationDaoDao() {
        return new ExaminationDaoImpl(Examination.class);
    }

    @Bean
    public PasswordEncoderData passwordEncoderData() {
        PasswordEncoderData passwordEncoderData = new PasswordEncoderData();
        passwordEncoderData.setPasswordEncoder(new BCryptPasswordEncoder());

        return passwordEncoderData;
    }
}
