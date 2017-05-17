package cn.edu.nju.p.vo;

/**
 * Created by dell- on 2017/5/17.
 */
public class CompanyAnnouncementVO {

    private String title;
    private String link;
    private String date;

    public CompanyAnnouncementVO(String title, String link, String date) {
        this.title = title;
        this.link = link;
        this.date = date;
    }

    public CompanyAnnouncementVO() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CompanyAnnouncementVO{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
