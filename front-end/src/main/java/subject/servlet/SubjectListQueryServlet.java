package subject.servlet;

import common.util.ExpertUtil;
import common.util.JsonUtil;
import cos.util.CosUtil;
import student.entity.Student;
import subject.entity.Score;
import subject.entity.Subject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubjectListQueryServlet extends HttpServlet {

    private JsonUtil jsonUtil = new JsonUtil();

    private ExpertUtil expertUtil = new ExpertUtil();

    private CosUtil cosUtil = new CosUtil();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String studentId = request.getParameter("student_id");

        // 查询该学生majorId
        String studentUrl = "http://server.aspi.tech:8080/backend/student/findall?studentId="+studentId;

        int majorId = 0;

        try {

            // 获得学生
            String studentJson = jsonUtil.loadJsonFromURL(studentUrl);

            List<Student> studentList = jsonUtil.jsonToStudentList(studentJson);

            majorId = studentList.get(0).getMajorId();

            // 用于找到所有该majorId下的课程
            String subjectUrl = "http://server.aspi.tech:8080/backend/subject/findall?majorId="+majorId;

            String scoreUrl = null;

            // 获得所有课程
            String subjectJson = jsonUtil.loadJsonFromURL(subjectUrl);

            List<Subject> subjectList = jsonUtil.jsonToSubjectList(subjectJson);

            Map<String, Score> map = new HashMap<String, Score>();

            String scoreJson = null;

            Score tempScore = null;

            for(Subject subject : subjectList){
                // 用于找到所有该学号和课程对应的成绩
                scoreUrl = "http://server.aspi.tech:8080/backend/score/findall?studentId="+studentId+"&subjectId=";


                scoreUrl += subject.getSubjectId();
                // 获取对应的成绩JSON
                scoreJson = jsonUtil.loadJsonFromURL(scoreUrl);

                List<Score> scoreList = jsonUtil.jsonToScoreList(scoreJson);

                // 有可能该项科目的成绩尚未存在
                // 此时需要添加一个新的实体
                if(scoreList.size() == 0){
                    tempScore = new Score(-1, -1, studentId, 0);
                }else{
                    tempScore = scoreList.get(0);
                }

                map.put(subject.getSubjectName(), tempScore);
            }

            String allKeyNames = "课程,成绩";

            String allValues = "";

            for (Map.Entry<String, Score> entry: map.entrySet()) {
                allValues += entry.getKey()+","+entry.getValue().getScoreValue()+"\r";
            }

            File file = expertUtil.getCsvFile(allKeyNames, allValues, "scoresFile.csv");

            String scoreFileDownloadUrl = cosUtil.uploadFile(file);



            // 将获得的列表添加到session中
            request.getSession().setAttribute("score_map", map);
            request.getSession().setAttribute("score_url", scoreFileDownloadUrl);

            response.sendRedirect ("/subject/query_scores.jsp") ;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
