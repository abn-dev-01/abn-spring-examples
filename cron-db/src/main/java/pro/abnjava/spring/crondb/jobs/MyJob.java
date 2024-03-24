package pro.abnjava.spring.crondb.jobs;

import java.util.concurrent.atomic.AtomicLong;

import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@Log4j2
public class MyJob implements Job {
    private final AtomicLong counter = new AtomicLong();

    @PostConstruct
    public void init() {
        LOG.info("Initializing `MyJob` ");
        this.counter.set(System.currentTimeMillis());
    }

    @Override
    public void execute(JobExecutionContext context) {
        // Your job logic here
        final var cc = this.counter.get();
        LOG.info("cc: {}", cc);
        final long newValue = System.currentTimeMillis();
        this.counter.set(newValue);

        LOG.info("Executing `MyJob` Period: {}", newValue - cc);
    }
}
