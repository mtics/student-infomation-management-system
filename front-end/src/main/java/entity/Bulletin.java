package entity;

import java.util.Date;

public class Bulletin {

    private int bulletinId;

    private String userId;

    private String bulletinTitle;

    private Date publishedDate;

    private String bulletinContext;

    public Bulletin() {
    }

    public Bulletin(int bulletinId, String userId, String bulletinTitle, Date publishedDate, String bulletinContext) {
        this.bulletinId = bulletinId;
        this.userId = userId;
        this.bulletinTitle = bulletinTitle;
        this.publishedDate = publishedDate;
        this.bulletinContext = bulletinContext;
    }

    public int getBulletinId() {
        return bulletinId;
    }

    public void setBulletinId(int bulletinId) {
        this.bulletinId = bulletinId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBulletinTitle() {
        return bulletinTitle;
    }

    public void setBulletinTitle(String bulletinTitle) {
        this.bulletinTitle = bulletinTitle;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getBulletinContext() {
        return bulletinContext;
    }

    public void setBulletinContext(String bulletinContext) {
        this.bulletinContext = bulletinContext;
    }
}
