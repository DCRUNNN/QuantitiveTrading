package cn.edu.nju.p.vo;

import cn.edu.nju.p.po.ClientPO;
import cn.edu.nju.p.po.StockPO;

/**
 * Created by cyz on 2017/6/10.
 */
public class ClientVO {
    private String user_name;
    private String password;
    private String email;
    private String unit;
    private String phone_number;
    private String place;
    private String sex;

    public ClientVO(String phoneNumber,String user_name,String sex,String email,String unit,String place,String password){
        this.user_name = user_name;
        this.password = password;
        this.email = email;
        this.unit = unit;
        this.phone_number = phoneNumber;
        this.place = place;
        this.sex = sex;
    }
    public String getSex(){return sex;}

    public void setSex(String sex) {this.sex = sex;}

    public String getUserName() {
        return user_name;
    }

    public void setUserName(String userName) {
        this.user_name = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phone_number = phoneNumber;
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
                "phoneNumber='" + phone_number + '\'' +
                ", userName=" + user_name +
                ", password=" + password +
                ", sex=" + sex +
                ", email=" + email +
                ", unit=" + unit +
                ", place=" + place +
                '}';
    }
}
