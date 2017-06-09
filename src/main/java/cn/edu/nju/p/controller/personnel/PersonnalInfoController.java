package cn.edu.nju.p.controller.personnel;

import cn.edu.nju.p.baseresult.BaseResult;
import cn.edu.nju.p.dao.ClientDao;
import cn.edu.nju.p.po.ClientPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by cyz on 2017/6/6.
 */
@RestController
@RequestMapping("/personnel")
public class PersonnalInfoController {

    @Autowired
    private ClientDao clientDao;

    @GetMapping("/{phoneNumber}")
    public BaseResult getClientInfo(@PathVariable String phoneNumber) {
        return new BaseResult<>(0,clientDao.selectClient(phoneNumber));
    }

    @PostMapping
    public BaseResult updateClientInfo(@RequestBody ClientPO clientPO) {
        clientDao.updateClient(clientPO);
        return new BaseResult(0, "update client info successfully!");
    }

    /*@Autowired
    private PersonnalInfoService personnalInfoService;

    @GetMapping("/personnalinfo/{userId}")
    public BaseResult getClientVO(@PathVariable String userId){
        ClientVO clientVO=personnalInfoService.getClientVO(userId);
        return new BaseResult<>(0, clientVO);
    }*/
}
