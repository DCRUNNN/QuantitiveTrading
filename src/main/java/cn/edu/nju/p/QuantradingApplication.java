package cn.edu.nju.p;

import cn.edu.nju.p.dao.daoutils.InsertTodayStockRun;
import cn.edu.nju.p.utils.CalculateHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableCaching
public class QuantradingApplication {

	public static void main(String[] args) {

		SpringApplication.run(QuantradingApplication.class, args);
//		InsertTodayStockRun test = new InsertTodayStockRun();
//		try {
//			test.insertDaily();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}


	@Bean
	public CalculateHelper calculateHelper() {

		return new CalculateHelper(0.0023);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}

}
