package tech.aspi.sims.backend.bulletin.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Bulletin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bull_id")
    private int bulletinId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "bull_title")
    private String bulletinTitle;

    @CreatedDate
    @GeneratedValue(strategy = GenerationType.AUTO)
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
