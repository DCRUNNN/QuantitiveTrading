package cn.edu.nju.p.po;

import java.time.LocalDate;

/**
 * Created by cyz on 2017/6/6.
 */
public class ClientPO {
    private String userId;
    private String password;
    private String email;
    private String unit;
    private String phone_number;
    private String place;


    public ClientPO(String userId, String password, String email, String unit, String phone_number,String place){
     this.userId=userId;
     this.password=password;
     this.email=email;
     this.unit=unit;
     this.phone_number=phone_number;
     this.place=place;
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
    public String getEmail(){
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
    public String getPhone_number(){
        return this.phone_number;
    }
    public void setPhone_number(String phone_number){
        this.phone_number=phone_number;
    }
    public String getPlace(){
        return this.place;
    }
    public void setPlace(String place){
        this.place=place;
    }
}

