package org.health.config;

import org.health.dao.EmployeeDao;
import org.health.dao.HorseDao;
import org.health.dao.InspectionDao;
import org.health.dao.ResultExaminationDao;
import org.health.dao.impl.EmployeeDaoImpl;
import org.health.dao.impl.HorseDaoImpl;
import org.health.dao.impl.InspectionDaoImpl;
import org.health.dao.impl.ResultExaminationDaoImpl;
import org.health.entity.Employee;
import org.health.entity.Horse;
import org.health.entity.Inspection;
import org.health.entity.ResultExamination;
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
    public InspectionDao inspectionDao() {
        return new InspectionDaoImpl(Inspection.class);
    }

    @Bean
    public ResultExaminationDao resultExaminationDao(){
        return new ResultExaminationDaoImpl(ResultExamination.class);
    }
}
