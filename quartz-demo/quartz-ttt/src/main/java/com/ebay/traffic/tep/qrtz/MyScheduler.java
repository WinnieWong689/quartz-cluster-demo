package com.ebay.traffic.tep.qrtz;
//import org.apache.log4j.BasicConfigurator;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;

import org.apache.log4j.BasicConfigurator;
import org.quartz.DateBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wangwang on 2016/9/6.
 */
public class MyScheduler {
    public static void main(String[] args) throws SchedulerException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        BasicConfigurator.configure();

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail job = newJob(HelloWorldJob.class).withIdentity("Job1", "JobGroup1").build();

        Date runTime = DateBuilder.nextGivenSecondDate(null, 37);

        SimpleTrigger trigger = newTrigger().withIdentity("Trigger1", "TriggerGroup1").startAt(runTime).withSchedule(simpleSchedule().withIntervalInSeconds(10).withRepeatCount(10)).build();

        //scheduler.scheduleJob(job, trigger);

        Logger log = LoggerFactory.getLogger(MyScheduler.class);

        log.info("------- Initializing ----------------------");
        scheduler.start();
    }
}
