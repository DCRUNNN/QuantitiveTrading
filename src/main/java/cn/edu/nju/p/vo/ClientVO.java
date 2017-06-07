package cn.edu.nju.p.vo;

/**
 * Created by cyz on 2017/6/6.
 */

import cn.edu.nju.p.po.ClientPO;
import org.apache.ibatis.annotations.Select;

/**
 * userId 用户id
 * password 密码
 * email 电子邮箱
 * unit 工作单位
 * phone_number 联系方式
 * place 现居地
 *
 * @author cyz
 */
public class ClientVO {
    private String userId;
    private String password;
    private String email;
    private String unit;
    private String phone_number;
    private String place;

    public ClientVO(ClientPO po){
        this.userId=po.getUserId();
        this.password=po.getPassword();
        this.email=po.getEmail();
        this.unit=po.getUnit();
        this.phone_number=po.getPhone_number();
        this.place=po.getPlace();
    }

    public String getUserId(){
        return this.userId;
    }
    public void setUserId(String userId){
        this.userId=userId;
    }
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String  getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getUnit(){
        return this.unit;
    }
    public void setUnit(String unit){
        this.unit=unit;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "ClientVO{" +
                "userId='" + userId + '\'' +
                ", password=" + password + '\''+
                ", email=" + email + '\''+
                ", unit=" + unit + '\'' +
                ", phone_number=" + phone_number + '\''+
                ", place=" + place + '\'' +
                '}';
    }

}
