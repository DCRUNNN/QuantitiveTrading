package cn.edu.nju.p;

import cn.edu.nju.p.utils.CalculateHelper;
import org.apache.ibatis.session.Configuration;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
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
		dataSource.setUrl("jdbc:mysql://106.14.224.189:3306/quantitivetrading");
//		dataSource.
		dataSource.setUsername("root");
		dataSource.setPassword("mysql");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setMaxActive(50);
		return dataSource;
	}

	/*@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean(){
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setVfs(SpringBootVFS.class);
		sqlSessionFactoryBean.setDataSource(dataSource());
		return sqlSessionFactoryBean;
	}*/
}
