package com.tradingArea;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
  * @Description(功能描述): 超级商圈项目
  * @author(作者): lrfalse<wangliyou>
  * @date (开发日期): 2018/4/24 10:18
  **/
@SpringBootApplication
@ImportResource(locations={"classpath:dubbo/dubbo-service.xml"})
public class TradingAreaApplication implements CommandLineRunner {
	private Logger logger = LoggerFactory.getLogger(TradingAreaApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(TradingAreaApplication.class, args);
	}

	@Override
	public void run(String... args)  {
		logger.info("超级商圈服务启动完成!");
	}

}
