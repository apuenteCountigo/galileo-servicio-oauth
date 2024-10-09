package com.formacionbdi.springboot.app.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@EntityScan({ "com.galileo.cu.commons.models" })
@EnableAuthorizationServer
public class SpringbootServicioOauthApplication implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder passwordEncode;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioOauthApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String password = "12345";

		for (int i = 0; i < 4; i++) {
			String passwordBCrypt = passwordEncode.encode(password);
			System.out.println(passwordBCrypt);
		}
		System.out.println("**************************************");
		System.out.println("OAUTH V-24-10-09 09:35");

	}

}
