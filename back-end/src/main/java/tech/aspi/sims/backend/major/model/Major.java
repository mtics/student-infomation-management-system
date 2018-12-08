package tech.aspi.sims.backend.major.model;

import javax.persistence.*;

@Entity
@Table(name = "major")
public class Major {

    @Id
    @GeneratedValue
    @Column(name = "major_id")
    private String majorId;

    @Column(name = "major_name")
    private String majorName;

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }
}
