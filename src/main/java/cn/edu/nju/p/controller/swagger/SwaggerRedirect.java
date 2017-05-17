package cn.edu.nju.p.controller.swagger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * to redirect the api request
 */
@Controller
public class SwaggerRedirect {

    @RequestMapping("/api")
    public String redirect() {
        return "redirect:swagger-ui.html";
    }
}
