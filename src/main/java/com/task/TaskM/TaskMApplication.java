package com.task.TaskM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
//@EnableOpenApi
public class TaskMApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskMApplication.class, args);
		
		// Prevent the main thread from exiting
	    try {
	        Thread.sleep(Long.MAX_VALUE); // Sleep indefinitely
	    } catch (InterruptedException e) {
	        // Handle interruption if needed
	    }
	}

}
