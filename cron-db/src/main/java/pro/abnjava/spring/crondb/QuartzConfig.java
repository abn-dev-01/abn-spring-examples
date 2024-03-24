package pro.abnjava.spring.crondb;

import javax.sql.DataSource;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerAccessorBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import pro.abnjava.spring.crondb.jobs.QuartzJobMyJob;
import pro.abnjava.spring.crondb.jobs.cronjob2.CronJob2Config;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class QuartzConfig {

    private final DataSource dataSource;
    // Quartz  Beans
    @Qualifier("autowiringSpringBeanJobFactory")
    private final JobFactory springBeanJobFactory;

    // Jobs Beans
    @Qualifier(QuartzJobMyJob.MY_JOB_DETAIL_NAME)
    @NonNull
    private final JobDetail myJobDetail;
    @Qualifier(QuartzJobMyJob.JOB_TRIGGER_MY_JOB)
    @NonNull
    private final Trigger myJobTrigger;

    @Qualifier(CronJob2Config.CRON_JOB_2)
    @NonNull
    private final JobDetail cronJob2JobDetail;
    @Qualifier(CronJob2Config.CRON_JOB2_TRIGGER)
    @NonNull
    private final Trigger cronJob2Trigger;


    @Bean
    public SchedulerAccessorBean schedulerAccessorBean() {
        SchedulerAccessorBean schedulerAccessorBean = new SchedulerAccessorBean();
        return schedulerAccessorBean;
    }

    @Bean(name = "jobSchedulerFactory")
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setDataSource(dataSource);

        schedulerFactoryBean.setJobDetails(myJobDetail, cronJob2JobDetail);
        schedulerFactoryBean.setTriggers(myJobTrigger, cronJob2Trigger);

        schedulerFactoryBean.setJobFactory(springBeanJobFactory);
        return schedulerFactoryBean;
    }
}
