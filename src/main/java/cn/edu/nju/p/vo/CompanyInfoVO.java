package cn.edu.nju.p.vo;


/**
 * Created by dc on 2017/5/16.
 */
public class CompanyInfoVO {

    private String name;//公司名称
    private String area;//所属地域
    private String concept;//涉及概念
    private String business;//主营业务
    private String businessDetail;//详细业务
    private String listingDate;//上市日期
    private String netAssetPerShare; //每股净资产
    private String incomePerShare;//每股收益
    private String profit;//每股净利润
    private String growthRate_profit;//净利润增长率
    private String incomeAll;//营业收入
    private String cashFlowPerShare;//每股现金流
    private String fundPerShare;//每股公积金
    private String undistributedProfitPerShare;//每股未分配利润
    private String totalShareCapital;//总股本
    private String outstandingShares;//流通股

    public CompanyInfoVO() {

    }


    public void setName(String name) {
        this.name = name;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public void setBusinessDetail(String businessDetail) {
        this.businessDetail = businessDetail;
    }

    public void setListingDate(String listingDate) {
        this.listingDate = listingDate;
    }

    public void setNetAssetPerShare(String netAssetPerShare) {
        this.netAssetPerShare = netAssetPerShare;
    }

    public void setIncomePerShare(String incomePerShare) {
        this.incomePerShare = incomePerShare;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public void setGrowthRate_profit(String growthRate_profit) {
        this.growthRate_profit = growthRate_profit;
    }

    public void setIncomeAll(String incomeAll) {
        this.incomeAll = incomeAll;
    }

    public void setCashFlowPerShare(String cashFlowPerShare) {
        this.cashFlowPerShare = cashFlowPerShare;
    }

    public void setFundPerShare(String fundPerShare) {
        this.fundPerShare = fundPerShare;
    }

    public void setUndistributedProfitPerShare(String undistributedProfitPerShare) {
        this.undistributedProfitPerShare = undistributedProfitPerShare;
    }

    public void setTotalShareCapital(String totalShareCapital) {
        this.totalShareCapital = totalShareCapital;
    }

    public void setOutstandingShares(String outstandingShares) {
        this.outstandingShares = outstandingShares;
    }

    public String getName() {
        return name;
    }

    public String getArea() {
        return area;
    }

    public String getConcept() {
        return concept;
    }

    public String getBusiness() {
        return business;
    }

    public String getBusinessDetail() {
        return businessDetail;
    }

    public String getListingDate() {
        return listingDate;
    }

    public String getNetAssetPerShare() {
        return netAssetPerShare;
    }

    public String getIncomePerShare() {
        return incomePerShare;
    }

    public String getProfit() {
        return profit;
    }

    public String getGrowthRate_profit() {
        return growthRate_profit;
    }

    public String getIncomeAll() {
        return incomeAll;
    }

    public String getCashFlowPerShare() {
        return cashFlowPerShare;
    }

    public String getFundPerShare() {
        return fundPerShare;
    }

    public String getUndistributedProfitPerShare() {
        return undistributedProfitPerShare;
    }

    public String getTotalShareCapital() {
        return totalShareCapital;
    }

    public String getOutstandingShares() {
        return outstandingShares;
    }

    @Override
    public String toString() {
        return "CompanyInfoVO{" +
                "name='" + name + '\'' +
                ", area='" + area + '\'' +
                ", concept='" + concept + '\'' +
                ", business='" + business + '\'' +
                ", businessDetail='" + businessDetail + '\'' +
                ", listingDate='" + listingDate + '\'' +
                ", netAssetPerShare='" + netAssetPerShare + '\'' +
                ", incomePerShare='" + incomePerShare + '\'' +
                ", profit='" + profit + '\'' +
                ", growthRate_profit='" + growthRate_profit + '\'' +
                ", incomeAll='" + incomeAll + '\'' +
                ", cashFlowPerShare='" + cashFlowPerShare + '\'' +
                ", fundPerShare='" + fundPerShare + '\'' +
                ", undistributedProfitPerShare='" + undistributedProfitPerShare + '\'' +
                ", totalShareCapital='" + totalShareCapital + '\'' +
                ", outstandingShares='" + outstandingShares + '\'' +
                '}';
    }
}
