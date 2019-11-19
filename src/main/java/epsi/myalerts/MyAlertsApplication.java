package epsi.myalerts;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("epsi.myalerts")
public class MyAlertsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyAlertsApplication.class, args);
	}

}
