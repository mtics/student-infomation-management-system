package tech.aspi.sims.bulletin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Bulletin {

    @Id
    @GeneratedValue
    @Column(name = "bull_id")
    private int bulletinId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "bull_title")
    private String bulletinTitle;

    @Column(name = "bull_published_date")
    private Date publishedDate;

    @Column(name = "bull_context")
    private String bulletinContext;

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
