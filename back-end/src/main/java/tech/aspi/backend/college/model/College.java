package tech.aspi.sims.college.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class College {

    @Id
    @GeneratedValue
    @Column(name = "college_id")
    private String collegeId;

    @Column(name = "college_name")
    private String collegeName;

    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }
}
