package cn.edu.nju.p;

import cn.edu.nju.p.utils.CalculateHelper;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@SpringBootApplication
public class QuantradingApplication {

	public static void main(String[] args) {

		SpringApplication.run(QuantradingApplication.class, args);
	}


	@Bean
	public CalculateHelper calculateHelper() {

		return new CalculateHelper(0.00023);
	}

	@Bean
	public DataSource dataSource(){
		DataSource dataSource = new DataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/quantitivetrading");
		dataSource.setUsername("root");
		dataSource.setPassword("mysql");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setMaxActive(50);
		return dataSource;
	}

}
