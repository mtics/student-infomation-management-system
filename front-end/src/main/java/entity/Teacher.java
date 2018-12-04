package entity;

import java.util.Date;

public class Teacher {

    private String teacherId;

    private String teacherName;

    private int collegeId;

    private String genger;

    private Date birthday;

    private String phone;

    private String portrait;        //教师照片

    public Teacher(String teacherId, String teacherName, String genger, Date birthday, String phone, String portrait, int collegeId) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.collegeId = collegeId;
        this.genger = genger;
        this.birthday = birthday;
        this.phone = phone;
        this.portrait = portrait;
    }

    public Teacher() {
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(int collegeId) {
        this.collegeId = collegeId;
    }

    public String getGenger() {
        return genger;
    }

    public void setGenger(String genger) {
        this.genger = genger;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }
}
