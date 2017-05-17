package cn.edu.nju.p.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by soft on 2017/5/17.
 */
@Configuration
@ImportResource(locations = "classpath:spring-aop.xml")
public class AopConfig {

    //import the aop config written by xml
}
