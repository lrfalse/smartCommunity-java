package com.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ImportResource(value = "classpath:dubbo/dubbo-service.xml")
public class EstateAdminApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(EstateAdminApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EstateAdminApplication.class, args);
	}
	@Override
	public void run(String... args)  {
		logger.info("业务管理系统启动完成! 端口：18081");
	}
}