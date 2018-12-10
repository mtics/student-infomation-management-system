package subject.entity;

public class Score {

    private int scoreId;

    private int subjectId;

    private String studentId;

    private double scoreValue;

    public Score(int scoreId, int subjectId, String studentId, double scoreValue) {
        this.scoreId = scoreId;
        this.subjectId = subjectId;
        this.studentId = studentId;
        this.scoreValue = scoreValue;
    }

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

    public String getAllKeyNames(){
        return "scoreId"+",subjectId"+",studentId"+",scoreValue";
    }

    public String getAllValues(){
        return scoreId+","+subjectId+","+studentId+","+scoreValue;
    }
}
