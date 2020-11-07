package org.ludo.bibliotheque;

import org.ludo.bibliotheque.exceptions.CustomErrorDecoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootApplication
@EnableSwagger2
@EnableFeignClients("org.ludo.bibliotheque")
@EnableScheduling
@EnableDiscoveryClient
@EnableEurekaClient
public class BibliothequeApplication {

	private static final Logger logger = LogManager.getLogger(BibliothequeApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BibliothequeApplication.class, args);
		logger.debug("Microservice Bibliothèque démarré");
			}

	@Bean
	public CustomErrorDecoder CustomErrorDecoder() {
		return new CustomErrorDecoder();
	}

}
