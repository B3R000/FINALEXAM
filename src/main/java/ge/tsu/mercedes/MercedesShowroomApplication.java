package ge.tsu.mercedes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class MercedesShowroomApplication {

    public static void main(String[] args) {
        SpringApplication.run(MercedesShowroomApplication.class, args);
    }
}
