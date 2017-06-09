package cn.edu.nju.p.controller.security;

import cn.edu.nju.p.baseresult.BaseResult;
import cn.edu.nju.p.dao.AccountDao;
import cn.edu.nju.p.dao.ClientDao;
import cn.edu.nju.p.po.ClientPO;
import cn.edu.nju.p.po.UserPO;
import cn.edu.nju.p.utils.EncryptHelper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ClientDao clientDao;

    @PostMapping
    public BaseResult signUp(@RequestBody @Valid ClientPO userPO) {

        String password = userPO.getPassword();
        password = EncryptHelper.getShaEncryption(password);
        String userName = userPO.getUserName();

        return new BaseResult(0, "successfully add a user!");
    }


}
