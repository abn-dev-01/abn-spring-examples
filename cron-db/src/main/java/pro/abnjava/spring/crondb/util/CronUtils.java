package pro.abnjava.spring.crondb.util;

import org.quartz.CronScheduleBuilder;

public class CronUtils {

    public static CronScheduleBuilder getSchedBuilder(String cronExpression) {
        return CronScheduleBuilder.cronSchedule(cronExpression);
    }
}
