package cn.edu.nju.p;

import cn.edu.nju.p.dao.StockDao;
import cn.edu.nju.p.dao.daoutils.InsertTodayStockRun;
import cn.edu.nju.p.dao.daoutils.UpdateTodayStockRun;
import cn.edu.nju.p.utils.CalculateHelper;
import cn.edu.nju.p.utils.beans.ToolSpring;
import org.apache.ibatis.session.Configuration;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.time.LocalDate;

@SpringBootApplication
@EnableCaching
public class QuantradingApplication {

	public static void main(String[] args) {

		SpringApplication.run(QuantradingApplication.class, args);
		InsertTodayStockRun insertRun = new InsertTodayStockRun();
		UpdateTodayStockRun updateRun = new UpdateTodayStockRun();
		try {
			insertRun.insertDaily();
			updateRun.Run();
		} catch (Exception e) {
			e.printStackTrace();
		}

//		/*StockDao stockDao = ToolSpring.getBeans(StockDao.class);
//		System.out.println(stockDao.getStockClose("000001", LocalDate.of(2012,2,3)));*/
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
