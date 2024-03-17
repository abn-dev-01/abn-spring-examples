package pro.abnjava.spring.cron.service;

import java.util.concurrent.atomic.AtomicLong;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class ScheduledTaskService {
    private final AtomicLong counter = new AtomicLong();

    //    @Scheduled(cron = "${app.cron}")
    @Scheduled(cron = "*/15 * * * * * ")
    public void executeTask() {
        var last = counter.get();
        final long newValue = System.currentTimeMillis() / 1000;
        var ll = newValue - last;
        counter.set(newValue);
        log.info("Task execution timeout - {}", ll);
        System.out.println("Executing Task - " + counter.get());
    }
}
