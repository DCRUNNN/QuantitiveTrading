package cn.edu.nju.p.vo;

import cn.edu.nju.p.po.StockPO;

/**
 * Created by dell- on 2017/6/12.
 */
public class StockVOWithSector {

    private String name;
    private String code;
    private String sector;

    public StockVOWithSector(String name, String code, String sector) {
        this.name = name;
        this.code = code;
        this.sector = sector;
    }

    public StockVOWithSector(StockPO po) {
        this.name = po.getName();
        this.code = po.getCode();
        this.sector = setSector(this.code);
    }

    private String setSector(String code) {
        String sector = "";
        if(code.startsWith("000")||code.startsWith("001")
                ||code.startsWith("600")||code.startsWith("601")){
            sector="主板";
        }
        else if(code.startsWith("002")){
            sector="中小板";
        }else if(code.startsWith("300")){
            sector="创业板";
        }else{
            sector="没有找到对应板块";
        }
        return sector;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSector() {
        return sector;
    }

    @Override
    public String toString() {
        return "StockVOWithSector{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", sector='" + sector + '\'' +
                '}';
    }
}
