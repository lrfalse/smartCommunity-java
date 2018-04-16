package com.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import tk.mybatis.spring.annotation.MapperScan;

/**
  * @Description(功能描述):新增工具包
  * @author(作者): lrfalse<wangliyou>
  * @date (开发日期): 2018/4/16 11:24
  **/
@Controller
@SpringBootApplication
@MapperScan(basePackages = "com.tools.mapper")
public class ToolsApplication  implements CommandLineRunner {
	private Logger logger = LoggerFactory.getLogger(ToolsApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ToolsApplication.class, args);
	}

	@Override
	public void run(String... args)  {
		logger.info("服务启动完成!");
	}
}
