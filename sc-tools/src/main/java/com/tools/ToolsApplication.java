package com.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;


/**
  * @Description(功能描述):工具包 9090
  * @author(作者): lrfalse<wangliyou>
  * @date (开发日期): 2018/4/16 11:24
  **/
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ImportResource(value = "classpath:dubbo/dubbo-service.xml")
public class ToolsApplication  implements CommandLineRunner {
	private Logger logger = LoggerFactory.getLogger(ToolsApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ToolsApplication.class, args);
	}

	@Override
	public void run(String... args)  {
		logger.info("工具包服务启动完成!");
	}
}
