package pro.abnjava.spring.crondb.jobs.cronjob2;

import static pro.abnjava.spring.crondb.util.CronUtils.getSchedBuilder;

import lombok.RequiredArgsConstructor;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@RequiredArgsConstructor
public class CronJob2Config {

    public static final String GROUP_DEFAULT = "default";
    public static final String CRON_JOB_2 = "сronJob2";
    public static final String CRON_JOB2_TRIGGER = "сronJobTrigger";
    public static final String CRON_EXPRESSION = "0/3 * * * * ?";


    @Bean(name = CRON_JOB_2)
    @Primary
    public JobDetail newJobDetail() {
        return JobBuilder.newJob(CronJob2.class)
                         .withIdentity(CRON_JOB_2, GROUP_DEFAULT)
                         .storeDurably()
                         .build();
    }

    @Bean(name = CRON_JOB2_TRIGGER)
    @Primary
    public Trigger newJobTrigger(@Qualifier(CRON_JOB_2) JobDetail cronJob2Detail) {
        return TriggerBuilder.newTrigger()
                             .forJob(cronJob2Detail)
                             .withIdentity(CRON_JOB2_TRIGGER, GROUP_DEFAULT)
                             .withSchedule(getSchedBuilder(CRON_EXPRESSION)) // Run every 30 seconds
                             .build();
    }
}
