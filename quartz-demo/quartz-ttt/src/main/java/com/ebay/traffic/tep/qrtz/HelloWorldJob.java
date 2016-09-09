package com.ebay.traffic.tep.qrtz;
import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by wangwang on 2016/9/6.
 */
public class HelloWorldJob implements Job {
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Hello, Quartz! - executing its JOB at "+ new Date());
    }
}
