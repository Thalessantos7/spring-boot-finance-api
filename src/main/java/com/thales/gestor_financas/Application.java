package com.thales.gestor_financas;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplicationBuilder bd = new SpringApplicationBuilder(Application.class);

		bd.bannerMode(Banner.Mode.OFF);

		bd.run(args);
	}
}