package gameserver;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

@SpringBootApplication
@ComponentScan({"gameserver.controller", " gameserver.websocket"})
public class Main extends SpringBootServletInitializer implements CommandLineRunner  {
    public static void main(String[] args) {
       // SpringApplication.run(BxdCheckPointServiceApplication.class, args);
        SpringApplication.run(Main.class, args);
    }
    @Override
    public void run(String... args)  {
        System.out.println("\nhey\n");
    }


}
