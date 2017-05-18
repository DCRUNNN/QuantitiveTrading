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

    @Bean
    public FilterRegistrationBean corsFilter(CorsFilter corsFilter){

        FilterRegistrationBean corsFilterReg = new FilterRegistrationBean();
        corsFilterReg.setFilter(corsFilter);
        corsFilterReg.addUrlPatterns("/*");
        corsFilterReg.setName("corsFilter");
        corsFilterReg.addInitParameter("allowOrigin","*");
        corsFilterReg.addInitParameter("allowMethods","GET,POST,PUT,DELETE,OPTIONS");
        corsFilterReg.addInitParameter("allowCredentials", "true");
        corsFilterReg.addInitParameter("allowHeaders","Content-Type");
        return corsFilterReg;
    }
}
