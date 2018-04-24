package com.linliHouse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import tk.mybatis.spring.annotation.MapperScan;

/**
  * @Description(功能描述): 服务性项目:如维修家政
  * @author(作者): lrfalse<wangliyou>
  * @date (开发日期): 2018/4/24 10:14
  **/
@SpringBootApplication
@ImportResource(locations={"classpath:dubbo/dubbo-service.xml"})
public class LinlihouseApplication  implements CommandLineRunner {
	private Logger logger = LoggerFactory.getLogger(LinlihouseApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(LinlihouseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("服务启动完成!");
	}
}
