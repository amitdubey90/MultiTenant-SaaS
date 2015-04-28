package app;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@ComponentScan(basePackages = { "app","app.controllers","app.services","app.dao" })
@EnableAutoConfiguration
@EnableAspectJAutoProxy
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
