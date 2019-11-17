package org.health.config;

import org.health.dao.*;
import org.health.dao.impl.*;
import org.health.entity.*;
import org.health.model.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

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
    public EmployeeDao employeeDao(){
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
    public MedicalCareDao medicalCareDao() {
        return new MedicalCareDaoImpl(MedicalCare.class);
    }
}
