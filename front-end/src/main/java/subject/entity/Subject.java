package subject.entity;

public class Subject {

    private int subjectId;

    private int majorId;

    private String subjectName;

    public Subject(int subjectId, int majorId, String subjectName) {
        this.subjectId = subjectId;
        this.majorId = majorId;
        this.subjectName = subjectName;
    }

    public Subject() {
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getAllKeyNames(){
        return "subjectId"+",majorId"+",subjectName";
    }

    public String getAllValues(){
        return subjectId+","+majorId+","+subjectName;
    }
}
