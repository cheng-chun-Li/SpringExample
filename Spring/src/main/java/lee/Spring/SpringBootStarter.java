package lee.Spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = { "lee" }) // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class SpringBootStarter 
{
    public static void main(String[] args) throws Exception {
        ApplicationContext ac = SpringApplication.run(SpringBootStarter.class, args);
    }
}
