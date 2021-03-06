package com.personalCenter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

/**
 * @Description(功能描述): 个人体系
 * @author(作者): lrfalse<wangliyou>
 * @date (开发日期): 2018/4/8 10:20
 **/
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
 @ImportResource(value = "classpath:dubbo/dubbo-service.xml")
public class PersonalCenterApplication implements CommandLineRunner {
	private Logger logger = LoggerFactory.getLogger(PersonalCenterApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PersonalCenterApplication.class, args);
	}
	@Override
	public void run(String... args)  {
		logger.info("个人体系服务启动完成!");
	}
}
