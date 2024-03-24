package pro.abnjava.spring.crondb.jobs.cronjob2;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


import lombok.extern.log4j.Log4j2;

@Log4j2
public class CronJob2 implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // Your job logic here
        LOG.info("-- Job #2 Cron-job ");
    }
}
