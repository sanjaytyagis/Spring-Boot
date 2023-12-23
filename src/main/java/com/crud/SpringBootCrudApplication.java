package com.crud;
/*
The Repositories have to be in the same packaege or a sub-package of the main(Application) class. 
This is the default behavior in spring boot.
 To keep things clean you can also put the repos in a sub package, like com.example.rest.repo
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class SpringBootCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCrudApplication.class, args);
	}

}
