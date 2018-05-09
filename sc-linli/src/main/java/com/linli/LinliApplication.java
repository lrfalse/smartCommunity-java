package com.linli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;


/**
  * @Description(功能描述): 邻里聊天室
  * @author(作者): lrfalse<wangliyou>
  * @date (开发日期): 2018/4/24 10:20
  **/
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ImportResource(locations={"classpath:dubbo/dubbo-service.xml"})
public class LinliApplication implements CommandLineRunner {
	private Logger logger = LoggerFactory.getLogger(LinliApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(LinliApplication.class, args);
	}

	@Override
	public void run(String... args)  {
		logger.info("邻里聊天室服务启动完成!");
	}

}
