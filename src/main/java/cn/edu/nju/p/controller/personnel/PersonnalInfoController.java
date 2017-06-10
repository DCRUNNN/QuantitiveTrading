package cn.edu.nju.p.controller.personnel;

import cn.edu.nju.p.baseresult.BaseResult;
import cn.edu.nju.p.dao.ClientDao;
import cn.edu.nju.p.po.ClientPO;
import cn.edu.nju.p.service.Personnel.PersonnalInfoService;
import cn.edu.nju.p.vo.ClientVO;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by cyz on 2017/6/6.
 */
@RestController
@RequestMapping("/personnel")
public class PersonnalInfoController {

    @Autowired
    private PersonnalInfoService personnalInfoService;

    @GetMapping("/{phoneNumber}")
    public BaseResult getClientInfo(@PathVariable String phoneNumber) {
        return new BaseResult<>(0,personnalInfoService.getClientInfo(phoneNumber));
    }

    @PostMapping("/update")
    public BaseResult updateClientInfo(JSONObject object) {
        String phone_number = object.getString("phone_number");
        String user_name= object.getString("user_name");
        String sex = object.getString("sex");
        String email = object.getString("email");
        String unit = object.getString("unit");
        String place = object.getString("place");
        personnalInfoService.updateClient(phone_number, user_name, sex, email, unit, place);
        return new BaseResult(0, "update client info successfully!");
    }


    @PostMapping("/updatePass")
    public BaseResult updatePass(JSONObject jsonObject){
        String phone_number = jsonObject.getString("phone_number");
        String password = jsonObject.getString("password");
        personnalInfoService.updatePass(phone_number,password);
        return new BaseResult(0,"update password successfully!");
    }
}
