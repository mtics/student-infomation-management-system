package teacher.entity;

import java.util.Date;

public class Teacher {

    private String teacherId;

    private String teacherName;

    private int collegeId;

    private String gender;

    private Date birthday;

    private String email;

    private String portrait;        //教师照片

    public Teacher(String teacherId, String teacherName, String gender, Date birthday, String email, String portrait, int collegeId) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.collegeId = collegeId;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getAllKeyNames(){
        return "teacherId"+",teacherName"+",gender"+",birthday"+",email"+",portrait"+",collegeId";
    }

    public String getAllValues(){
        return teacherId+","+teacherName+","+gender+","+birthday+","+email+","+portrait+","+collegeId;
    }
}
