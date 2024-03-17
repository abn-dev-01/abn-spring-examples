package pro.abnjava.spring.cron;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;


//@Configuration
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
@Data
@NoArgsConstructor
public class ApplicationProperties {
    private String cron;
    private String name;
}
