package cn.edu.nju.p.controller.security;

import cn.edu.nju.p.baseresult.BaseResult;
import cn.edu.nju.p.cache.TokenManager;
import cn.edu.nju.p.dao.AccountDao;
import cn.edu.nju.p.exception.PasswordNotMatchException;
import cn.edu.nju.p.utils.EncryptHelper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * to login
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private TokenManager tokenManager;

    @PostMapping
    public BaseResult login(@RequestBody JSONObject user,HttpServletResponse response) {

        String phoneNumber = user.getString("phoneNumber");
        String password = user.getString("password");
        String actualPassword = accountDao.getPassword(phoneNumber);
        if (!EncryptHelper.checkPassword(password, actualPassword)) {
            throw new PasswordNotMatchException(phoneNumber + " login failed!Password not match!");
        }
        String token = tokenManager.createToken(phoneNumber);
        /*
        * 登录成功之后响应头会带有一个x-token的值，前端写进cookie,每次访问后端时都要带上x-token即可,
        * 使用aop拦截请求判断是否存在x-token完成验证
        * */
        response.addHeader("x-token",token);
        return new BaseResult(0, "Welcome Login In!" + phoneNumber);
    }
}
