package cn.edu.nju.p.po;

/**
 * Created by dell- on 2017/5/16.
 */
public class CompanyNewsPO {

    private String date; //新闻日期
    private String link; //新闻的URL链接
    private String title; //新闻标题

    public CompanyNewsPO(String title, String link, String date) {
        this.title = title;
        this.link = link;
        this.date = date;
    }

    public CompanyNewsPO() {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "CompanyNewsPO{" +
                "date='" + date + '\'' +
                ", link='" + link + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
