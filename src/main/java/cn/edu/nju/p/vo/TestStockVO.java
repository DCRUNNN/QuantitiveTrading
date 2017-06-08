package cn.edu.nju.p.vo;

import cn.edu.nju.p.po.TestStockPO;

/**
 * Created by dell- on 2017/6/8.
 */
public class TestStockVO {
    private double point;
    private String overview;
    private String beat;
    private String shortTerm_trend;
    private String midTerm_trend;
    private String longTerm_trend;
    private String conclude;
    private String testDate;
    private String technical_test;
    private String fund_test;
    private String info_test;
    private String industry_test;
    private String basic_test;

    public TestStockVO() {

    }

    public TestStockVO(double point, String overview, String beat, String shortTerm_trend, String midTerm_trend, String longTerm_trend, String conclude, String testDate, String technical_test, String fund_test, String info_test, String industry_test, String basic_test) {
        this.point = point;
        this.overview = overview;
        this.beat = beat;
        this.shortTerm_trend = shortTerm_trend;
        this.midTerm_trend = midTerm_trend;
        this.longTerm_trend = longTerm_trend;
        this.conclude = conclude;
        this.testDate = testDate;
        this.technical_test = technical_test;
        this.fund_test = fund_test;
        this.info_test = info_test;
        this.industry_test = industry_test;
        this.basic_test = basic_test;
    }

    public TestStockVO(TestStockPO po) {
        this.point = po.getPoint();
        this.overview = po.getOverview();
        this.beat = po.getBeat();
        this.shortTerm_trend = po.getShortTerm_trend();
        this.midTerm_trend = po.getMidTerm_trend();
        this.longTerm_trend = po.getLongTerm_trend();
        this.conclude = po.getConclude();
        this.testDate = po.getTestDate();
        this.technical_test = po.getTechnical_test();
        this.fund_test = po.getFund_test();
        this.info_test = po.getInfo_test();
        this.industry_test = po.getIndustry_test();
        this.basic_test = po.getBasic_test();
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getBeat() {
        return beat;
    }

    public void setBeat(String beat) {
        this.beat = beat;
    }

    public String getShortTerm_trend() {
        return shortTerm_trend;
    }

    public void setShortTerm_trend(String shortTerm_trend) {
        this.shortTerm_trend = shortTerm_trend;
    }

    public String getMidTerm_trend() {
        return midTerm_trend;
    }

    public void setMidTerm_trend(String midTerm_trend) {
        this.midTerm_trend = midTerm_trend;
    }

    public String getLongTerm_trend() {
        return longTerm_trend;
    }

    public void setLongTerm_trend(String longTerm_trend) {
        this.longTerm_trend = longTerm_trend;
    }

    public String getConclude() {
        return conclude;
    }

    public void setConclude(String conclude) {
        this.conclude = conclude;
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }

    public String getTechnical_test() {
        return technical_test;
    }

    public void setTechnical_test(String technical_test) {
        this.technical_test = technical_test;
    }

    public String getFund_test() {
        return fund_test;
    }

    public void setFund_test(String fund_test) {
        this.fund_test = fund_test;
    }

    public String getInfo_test() {
        return info_test;
    }

    public void setInfo_test(String info_test) {
        this.info_test = info_test;
    }

    public String getIndustry_test() {
        return industry_test;
    }

    public void setIndustry_test(String industry_test) {
        this.industry_test = industry_test;
    }

    public String getBasic_test() {
        return basic_test;
    }

    public void setBasic_test(String basic_test) {
        this.basic_test = basic_test;
    }

    @Override
    public String toString() {
        return "TestStockVO{" +
                "point=" + point +
                ", overview='" + overview + '\'' +
                ", beat='" + beat + '\'' +
                ", shortTerm_trend='" + shortTerm_trend + '\'' +
                ", midTerm_trend='" + midTerm_trend + '\'' +
                ", longTerm_trend='" + longTerm_trend + '\'' +
                ", conclude='" + conclude + '\'' +
                ", testDate='" + testDate + '\'' +
                ", technical_test='" + technical_test + '\'' +
                ", fund_test='" + fund_test + '\'' +
                ", info_test='" + info_test + '\'' +
                ", industry_test='" + industry_test + '\'' +
                ", basic_test='" + basic_test + '\'' +
                '}';
    }
}
