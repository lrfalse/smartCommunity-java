package com.linliAdmin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ImportResource(value = "classpath:dubbo/dubbo-service.xml")
public class LinliadminApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(LinliadminApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LinliadminApplication.class, args);
	}
	@Override
	public void run(String... args)  {
		logger.info("平台管理系统启动完成! 端口：18080");
	}
}