package com.mahendracandi.amqartemisexplorer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class AmqArtemisExplorerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmqArtemisExplorerApplication.class, args);
	}

}
