import org.apache.log4j.BasicConfigurator;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by wangwang on 2016/9/6.
 */
public class MyScheduler {
    public static void main(String[] args) throws SchedulerException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        BasicConfigurator.configure();

        SchedulerFactory schedulerFactory = new StdSchedulerFactory("properties/quartz.properties");
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail job = newJob(HelloWorldJob.class).withIdentity("job90", "group233").build();

        Date runTime = DateBuilder.nextGivenSecondDate(null, 5);

        SimpleTrigger trigger = newTrigger().withIdentity("trigger90", "group233").startAt(runTime).withSchedule(simpleSchedule().withIntervalInSeconds(10).withRepeatCount(10)).build();

        //scheduler.scheduleJob(job, trigger);

        Logger log = LoggerFactory.getLogger(MyScheduler.class);

        log.info("------- Initializing ----------------------");
        scheduler.start();
    }
}
