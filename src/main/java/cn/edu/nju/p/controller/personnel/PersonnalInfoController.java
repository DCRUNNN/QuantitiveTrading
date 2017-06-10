package cn.edu.nju.p.controller.personnel;

import cn.edu.nju.p.baseresult.BaseResult;
import cn.edu.nju.p.dao.ClientDao;
import cn.edu.nju.p.po.ClientPO;
import cn.edu.nju.p.service.Personnel.PersonnalInfoService;
import cn.edu.nju.p.vo.ClientVO;
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
    public BaseResult updateClientInfo(@RequestParam String phone_number,@RequestParam String user_name,@RequestParam String sex,@RequestParam String email,@RequestParam String unit,@RequestParam String place) {
        personnalInfoService.updateClient(phone_number,user_name,sex,email,unit,place);
        return new BaseResult(0, "update client info successfully!");
    }

    @PostMapping("/addClient")
    public BaseResult addClient(@RequestParam String phoneNumber, @RequestParam String userName ,@RequestParam String password){
       personnalInfoService.addClient(userName,phoneNumber,password);
        return new BaseResult(0, "add client info successfully!");
    }

    @PostMapping("/updatePass")
    public BaseResult updatePass(@RequestParam String phone_number,@RequestParam String password){
        personnalInfoService.updatePass(phone_number,password);
        return new BaseResult(0,"update password successfully!");
    }
}
