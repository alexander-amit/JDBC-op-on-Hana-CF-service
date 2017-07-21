package hana.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DefaultMessage {
	
	@RequestMapping("/home")
	public String get() {
        return "Application, for testing crud operation on Hana db(Service of CF), Successfully deployed --> We are going to use JPA and JDBC operations";
    }
	
}


