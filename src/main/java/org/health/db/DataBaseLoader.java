package org.health.db;

import org.health.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.Date;

public class DataBaseLoader {
    //
}
/*
@Component
public class DataBaseLoader implements CommandLineRunner {
    private final ScheduleRepository repository;

    @Autowired
    public DataBaseLoader(ScheduleRepository repository) {
        this.repository = repository;
    }
*/

/*
    @Override
    public void run(String... strings) throws Exception {
        //Date d = new java.util.Date();
        //Date t1 = new java.sql.Date(d.getTime());
        this.repository.save(new Schedule(1, null, null,null, null, null, null,null, null, null, null, null, null, null, null));
    }


}
*/