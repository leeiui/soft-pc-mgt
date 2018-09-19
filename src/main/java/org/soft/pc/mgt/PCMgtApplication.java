package org.soft.pc.mgt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class PCMgtApplication {

	public static void main(String[] args) {

		SpringApplication.run(PCMgtApplication.class, args);
	}

}
