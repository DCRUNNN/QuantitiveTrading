package cn.edu.nju.p.controller.swagger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * to redirect the api request
 */
@Controller
@ApiIgnore
public class SwaggerRedirect {

    @RequestMapping("/api")
    public String redirect() {
        return "redirect:swagger-ui.html";
    }
}
