package hana;

import org.springframework.boot.SpringApplication;
//import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan({"hana.jdbc","hana.controller"}) 
public class HanaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HanaApplication.class, args);
	}
}
