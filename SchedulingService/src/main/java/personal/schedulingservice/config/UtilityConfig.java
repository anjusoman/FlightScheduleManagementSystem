package personal.schedulingservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;
import java.util.Random;

@Configuration
public class UtilityConfig {
    @Bean
    public Random random() {
        return new Random();
    }

    @Bean
    public Calendar calendar() {
        return Calendar.getInstance();
    }
}
