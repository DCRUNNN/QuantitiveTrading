package cn.edu.nju.p.config;

import cn.edu.nju.p.filter.CorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by soft on 2017/5/18.
 */
@Configuration
public class FilterConfig {

   /* @Bean
    public FilterRegistrationBean corsFilter(){

        FilterRegistrationBean corsFilterReg = new FilterRegistrationBean();
        corsFilterReg.setFilter(new CorsFilter());
        corsFilterReg.addUrlPatterns("*//*");
        corsFilterReg.setName("corsFilter");
        return corsFilterReg;
    }*/
}
