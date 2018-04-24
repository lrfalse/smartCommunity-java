package com.scentranceguard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
  * @Description(功能描述): 门禁服务系统
  * @author(作者): lrfalse<wangliyou>
  * @date (开发日期): 2018/4/24 9:18
  **/
@SpringBootApplication
public class EntranceguardApplication implements CommandLineRunner {
	private Logger logger = LoggerFactory.getLogger(EntranceguardApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EntranceguardApplication.class, args);
	}
	@Override
	public void run(String... args)  {
		logger.info("门禁服务系统启动完成!");
	}
}
