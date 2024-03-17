package pro.abnjava.spring.cron.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
public class SchedulerController {

    private final DynamicSchedulerService dynamicSchedulerService;

//    @GetMapping("/update-cron")
//    public String updateCron(@RequestParam String cron) {
//        log.info("Cron expression: " + cron);
//        dynamicSchedulerService.scheduleTask(cron);
//        return "Cron expression updated to " + cron;
//    }

    @GetMapping("/schedule/update")
    public ResponseEntity<String> updateSchedule(@RequestParam String cron) {
        dynamicSchedulerService.scheduleTask(cron);
        return ResponseEntity.ok("Scheduled task updated with cron expression: " + cron);
    }

    @GetMapping("/schedule/stop")
    public ResponseEntity<String> stopSchedule() {
        dynamicSchedulerService.stopTask();
        return ResponseEntity.ok("Scheduled task stopped");
    }


//
}
