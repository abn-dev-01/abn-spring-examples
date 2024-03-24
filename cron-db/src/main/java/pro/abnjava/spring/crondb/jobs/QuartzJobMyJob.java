package pro.abnjava.spring.crondb.jobs;

import lombok.RequiredArgsConstructor;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class QuartzJobMyJob {

    public static final String JOB_TRIGGER_MY_JOB = "myJobTrigger";
    public static final String MY_GROUP = "myGroup";
    public static final String MY_JOB_DETAIL_NAME = "myJob";

    @Bean(name = MY_JOB_DETAIL_NAME)
    public JobDetail myJobDetail() {
        return JobBuilder.newJob(MyJob.class)
                         .withIdentity(MY_JOB_DETAIL_NAME, MY_GROUP)
                         .storeDurably()
                         .build();
    }

    @Bean(name = JOB_TRIGGER_MY_JOB)
    public Trigger trigger(@Qualifier(MY_JOB_DETAIL_NAME) JobDetail myJobDetail) {
        final SimpleScheduleBuilder simpleScheduleBuilder = getSimpleScheduleBuilder(10);
        return TriggerBuilder
            .newTrigger()
            .startNow()
            .forJob(myJobDetail)
            .withIdentity(JOB_TRIGGER_MY_JOB, MY_GROUP)
            .withSchedule(simpleScheduleBuilder)
            .build();
    }


    private static SimpleScheduleBuilder getSimpleScheduleBuilder(int interval) {
        final SimpleScheduleBuilder schedBuilder = SimpleScheduleBuilder
            .simpleSchedule()
            .withIntervalInSeconds(interval)
            .repeatForever();
        return schedBuilder;
    }

}
