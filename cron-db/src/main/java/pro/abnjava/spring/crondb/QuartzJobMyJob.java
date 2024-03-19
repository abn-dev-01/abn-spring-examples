package pro.abnjava.spring.crondb;

import javax.sql.DataSource;

import lombok.RequiredArgsConstructor;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import pro.abnjava.spring.crondb.jobs.AutowiringSpringBeanJobFactory;
import pro.abnjava.spring.crondb.jobs.MyJob;

@Configuration
@RequiredArgsConstructor
public class QuartzJobMyJob {
    private final DataSource dataSource;

    @Bean
    public JobDetail myJobDetail() {
        return JobBuilder.newJob(MyJob.class)
                         .withIdentity("myJob", "myGroup")
                         .storeDurably()
                         .build();
    }

    @Bean
    public Trigger myJobTrigger(JobDetail myJobDetail) {
        final SimpleScheduleBuilder schedBuilder = getSimpleScheduleBuilder();

        return TriggerBuilder
            .newTrigger()
            .startNow()
            .forJob(myJobDetail)
            .withIdentity("myJobTrigger", "myGroup")
            .withSchedule(schedBuilder)
            .build();
    }

    private static SimpleScheduleBuilder getSimpleScheduleBuilder() {
        final SimpleScheduleBuilder schedBuilder = SimpleScheduleBuilder
            .simpleSchedule()
            .withIntervalInSeconds(10)
            .repeatForever();
        return schedBuilder;
    }

    @Bean
    public SimpleTrigger trigger() {
        final SimpleScheduleBuilder schedBuilder = getSimpleScheduleBuilder();
        return TriggerBuilder
            .newTrigger()
            .forJob(myJobDetail())
            .withIdentity("myJobTrigger", "myGroup")
            .startNow()
            .withSchedule(schedBuilder)
            .build();
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setDataSource(dataSource);
        schedulerFactoryBean.setTriggers(trigger());
        schedulerFactoryBean.setJobDetails(myJobDetail());
        schedulerFactoryBean.setJobFactory(springBeanJobFactory());
        return schedulerFactoryBean;
    }

    @Bean
    public SpringBeanJobFactory springBeanJobFactory() {
        return new AutowiringSpringBeanJobFactory();
    }

}
