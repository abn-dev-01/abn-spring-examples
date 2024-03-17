# CRON-RUNTIME / abn-spring-examples

## Spring-boot examples from ABN.

### Technical parameters

    Java JDK 17+
    Gradle

This example shows how to start Scheduling/CRON within Spring-Boot app,
The cron configuration has init parameters in 'application.yml' in `app.cron:`

You can update cron time and stop cron task execution via REST API.

Example:

    Start with gradle:
    gradlew bootRun

    Update cron: 
    http://127.0.0.1:8080/schedule/update?cron=*/7 * * * * *

    Stop cron:
    http://127.0.0.1:8080/schedule/stop
