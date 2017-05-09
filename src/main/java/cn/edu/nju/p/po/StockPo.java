package cn.edu.nju.p.po;

import java.time.LocalDate;

/**
 * the po of stock
 */
public class StockPo {

    private String code;
    private String name;
    private LocalDate date;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
