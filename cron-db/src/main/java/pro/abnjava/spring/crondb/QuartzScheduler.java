package pro.abnjava.spring.crondb;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@RequiredArgsConstructor
public class QuartzScheduler {

    private final Scheduler scheduler;

    @PostConstruct
    public void startScheduler() throws SchedulerException {
        if (!scheduler.isStarted()) {
            LOG.info("startScheduler(): Starting scheduler");
            scheduler.start();
        }
    }
}
