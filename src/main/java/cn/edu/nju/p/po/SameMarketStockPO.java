package cn.edu.nju.p.po;

/**
 * Created by dell- on 2017/6/8.
 */
public class SameMarketStockPO {

    private String name;
    private double newestPrice;
    private String third;

    public SameMarketStockPO(){

    }

    public SameMarketStockPO(String name, double newestPrice, String third) {
        this.name = name;
        this.newestPrice = newestPrice;
        this.third = third;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNewestPrice() {
        return newestPrice;
    }

    public void setNewestPrice(double newestPrice) {
        this.newestPrice = newestPrice;
    }

    public String getThird() {
        return third;
    }

    public void setThird(String third) {
        this.third = third;
    }

    @Override
    public String toString() {
        return "SameMarketStockPO{" +
                "name='" + name + '\'' +
                ", newestPrice=" + newestPrice +
                ", third='" + third + '\'' +
                '}';
    }
}
