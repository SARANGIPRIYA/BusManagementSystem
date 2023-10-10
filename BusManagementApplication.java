package SpringProject.BusManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/* @SpringBootApplication It is a combination of three Spring Annotations 1.@EnableAutoConfiguration 2.@Configuration 3.@ComponentScan 
 * The @SpringBootApplication annotation heps developer to mark configuration classes.*/
@SpringBootApplication
public class BusManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusManagementApplication.class, args);
    }

}
