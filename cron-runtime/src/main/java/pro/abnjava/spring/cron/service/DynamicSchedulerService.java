package pro.abnjava.spring.cron.service;

import java.util.concurrent.ScheduledFuture;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class DynamicSchedulerService {

    private TaskScheduler taskScheduler;
    private ScheduledFuture<?> scheduledFuture;
    private final Runnable task;

    public DynamicSchedulerService() {
        this.taskScheduler = new ThreadPoolTaskScheduler();
        ((ThreadPoolTaskScheduler) taskScheduler).initialize();

        // Define your task here
        this.task = () -> System.out.println("Executing scheduled task at " + System.currentTimeMillis());
    }

    public void scheduleTask(String cronExpression) {
        stopTask(); // Stop existing task if running
        scheduledFuture = taskScheduler.schedule(task, new CronTrigger(cronExpression));
    }

    public void stopTask() {
        if (scheduledFuture != null && !scheduledFuture.isCancelled()) {
            scheduledFuture.cancel(true);
        }
    }

//
}
