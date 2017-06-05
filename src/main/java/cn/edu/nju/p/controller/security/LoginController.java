package cn.edu.nju.p.controller.security;

import cn.edu.nju.p.baseresult.BaseResult;
import cn.edu.nju.p.exception.PasswordNotMatchException;
import cn.edu.nju.p.utils.EncryptHelper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * to login
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @PostMapping
    public BaseResult login(@RequestBody JSONObject user) {

        String phoneNumber = user.getString("phoneNumber");
        String password = user.getString("password");
        /**
         * to get a password from data base
         */
        String actualPassword = "";
        if (!EncryptHelper.checkPassword(password, actualPassword)) {
            throw new PasswordNotMatchException(phoneNumber + "login failed!Password not match!");
        }
        return new BaseResult(0, "Welcome Login In!" + phoneNumber);
    }
}
