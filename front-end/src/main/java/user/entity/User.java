package user.entity;

public class User {

    private String userId;

    private String passwd;

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

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", passwd='" + passwd + '\'' +
                ", userLevel=" + userLevel +
                '}';
    }
}
