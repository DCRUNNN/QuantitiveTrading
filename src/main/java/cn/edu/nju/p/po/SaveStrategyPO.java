package cn.edu.nju.p.po;

/**
 * Created by dell- on 2017/6/15.
 */
public class SaveStrategyPO {

    private String phone_number;
    private String strategy_name;
    private String strategy_url;


    public SaveStrategyPO(String phone_number, String strategy_name, String strategy_url) {
        this.phone_number = phone_number;
        this.strategy_name = strategy_name;
        this.strategy_url = strategy_url;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getStrategy_name() {
        return strategy_name;
    }

    public void setStrategy_name(String strategy_name) {
        this.strategy_name = strategy_name;
    }

    public String getStrategy_url() {
        return strategy_url;
    }

    public void setStrategy_url(String strategy_url) {
        this.strategy_url = strategy_url;
    }

    @Override
    public String toString() {
        return "SaveStrategyPO{" +
                "phone_number='" + phone_number + '\'' +
                ", strategy_name='" + strategy_name + '\'' +
                ", strategy_url='" + strategy_url + '\'' +
                '}';
    }
}
