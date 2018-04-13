package com.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import tk.mybatis.spring.annotation.MapperScan;

@Controller
@EnableWebMvc
@SpringBootApplication
@MapperScan(basePackages = "com.tools.mapper")
public class ToolsApplication  implements CommandLineRunner {
	private Logger logger = LoggerFactory.getLogger(ToolsApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ToolsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("服务启动完成!");
	}
}
