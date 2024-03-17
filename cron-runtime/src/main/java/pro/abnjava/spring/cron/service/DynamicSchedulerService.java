package pro.abnjava.spring.cron.service;

import java.util.concurrent.ScheduledFuture;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import pro.abnjava.spring.cron.ApplicationProperties;

@Log4j2
@Service
@RequiredArgsConstructor
public class DynamicSchedulerService {
    private final ScheduledTaskService scheduledTaskService;
    private final ApplicationProperties appProps;

    private ScheduledFuture<?> scheduledFuture;
    private TaskScheduler taskScheduler;

    @PostConstruct
    public void postContruct() {
        this.taskScheduler = new ThreadPoolTaskScheduler();
        ((ThreadPoolTaskScheduler) taskScheduler).initialize();
        LOG.info("Init CRON service - {}", appProps.getCron());
        this.scheduleTask(appProps.getCron());
    }

    public void scheduleTask(String cronExpression) {
        stopTask(); // Stop existing task if running
        Runnable task = () -> scheduledTaskService.executeTask();
        scheduledFuture = taskScheduler.schedule(task, new CronTrigger(cronExpression));
    }

    public void stopTask() {
        if (scheduledFuture != null && !scheduledFuture.isCancelled()) {
            scheduledFuture.cancel(true);
        }
    }


//
}
