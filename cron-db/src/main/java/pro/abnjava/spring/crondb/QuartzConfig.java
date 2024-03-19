package pro.abnjava.spring.crondb;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerAccessorBean;

@Configuration
@RequiredArgsConstructor
@EnableScheduling
public class QuartzConfig {

    @Bean
    public SchedulerAccessorBean schedulerAccessorBean() {
        SchedulerAccessorBean schedulerAccessorBean = new SchedulerAccessorBean();
        return schedulerAccessorBean;
    }
}
