package com.sadrax.avtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(scanBasePackages = {
		"com.sadrax.avtask"
})
@EntityScan(basePackages = { "com.sadrax.avtask.infrastructure.entity.generated" })
@ConfigurationPropertiesScan(basePackages = {"com.sadrax.avtask"})
public class AvtaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvtaskApplication.class, args);
	}

}
