package tech.aspi.sims.teacher.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Teacher {

    @Id
    @Column(name = "teacher_id")
    private String teacherId;

    @Column(name = "teacher_name")
    private String teacherName;

    @Column(name = "college_id")
    private int collegeId;

    @Column(name = "gender")
    private String genger;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "phone")
    private String phone;

    @Column(name = "portrait")
    private String portrait;        //教师照片

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
