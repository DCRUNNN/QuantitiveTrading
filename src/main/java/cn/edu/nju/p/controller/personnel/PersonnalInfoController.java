package cn.edu.nju.p.controller.personnel;

import cn.edu.nju.p.baseresult.BaseResult;
import cn.edu.nju.p.service.Personnel.PersonnalInfoService;
import cn.edu.nju.p.vo.ClientVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cyz on 2017/6/6.
 */
@RestController
@RequestMapping("/personnel")
public class PersonnalInfoController {

    @Autowired
    private PersonnalInfoService personnalInfoService;

    @GetMapping("/personnalinfo/{userId}")
    public BaseResult getClientVO(@PathVariable String userId){
        ClientVO clientVO=personnalInfoService.getClientVO(userId);
        return new BaseResult<>(0, clientVO);
    }
}
