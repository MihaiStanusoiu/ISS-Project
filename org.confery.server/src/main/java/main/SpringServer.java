package main;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.ResourceBundle;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@SpringBootApplication
@SuppressWarnings("all")
public class SpringServer {

    @Bean
    public ResourceBundle resourceBundle() {
        return ResourceBundle.getBundle("application");
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = getContext();
        System.out.println("Waiting for Request from Client ...");
    }


    private static ConfigurableApplicationContext getContext() {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(SpringServer.class);
        return builder.run();
    }
}
