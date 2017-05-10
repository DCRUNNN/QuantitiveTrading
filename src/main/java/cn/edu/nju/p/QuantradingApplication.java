package cn.edu.nju.p;

import cn.edu.nju.p.utils.CalculateHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QuantradingApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuantradingApplication.class, args);
	}

	@Bean
	public CalculateHelper calculateHelper() {

		return new CalculateHelper(0.00023);
	}
}
