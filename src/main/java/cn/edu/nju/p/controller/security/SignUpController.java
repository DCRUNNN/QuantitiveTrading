package cn.edu.nju.p.controller.security;

import cn.edu.nju.p.baseresult.BaseResult;
import cn.edu.nju.p.po.UserPO;
import cn.edu.nju.p.utils.EncryptHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by soft on 2017/5/17.
 */
@RestController
@RequestMapping("/signup")
public class SignUpController {

    @PostMapping
    public BaseResult signUp(@RequestBody @Valid UserPO userPO) {

        String password = userPO.getPassword();
        EncryptHelper.getShaEncryption(password);
        /**
         * to save the user info
         */
        return new BaseResult(0, "successfully add a user!");
    }
}
