package tech.aspi.sims.backend.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sims_user")
public class User {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "passwd")
    private String passwd;

    @Column(name = "user_level")
    private int userLevel;     // 用户权限等级

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }
}
