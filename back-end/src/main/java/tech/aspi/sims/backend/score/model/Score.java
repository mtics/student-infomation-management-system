package tech.aspi.sims.backend.score.model;

import tech.aspi.sims.backend.student.model.Student;
import tech.aspi.sims.backend.subject.model.Subject;

import javax.persistence.*;

@Entity
@Table(name = "score")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "score_id")
    private int scoreId;

    @Column(name = "subject_id")
    private int subjectId;

    @Column(name = "student_id")
    private String studentId;

    @Column(name = "score_value")
    private double scoreValue;

    public int getScoreId() {
        return scoreId;
    }

    public void setScoreId(int scoreId) {
        this.scoreId = scoreId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public double getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(double scoreValue) {
        this.scoreValue = scoreValue;
    }
}
