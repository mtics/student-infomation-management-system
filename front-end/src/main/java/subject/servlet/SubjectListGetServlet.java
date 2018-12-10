package subject.servlet;

import common.util.JsonUtil;
import page.entity.Page;
import student.entity.Student;
import subject.entity.Score;
import subject.entity.Subject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class SubjectListGetServlet extends HttpServlet {

    private JsonUtil jsonUtil = new JsonUtil();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String studentId = request.getParameter("studentId");

        String majorId = request.getParameter("majorId");

        // 用于找到所有该majorId下的课程
        String subjectUrl = "http://server.aspi.tech:8080/backend/subject/findall?majorId="+majorId;

        // 用于找到所有该学号和课程对应的成绩
        String scoreUrl = "http://server.aspi.tech:8080/backend/score/findall?studentId="+studentId+"&subjectId=";

        try {

            // 获得所有课程
            String subjectJson = jsonUtil.loadJsonFromURL(subjectUrl);

            List<Subject> subjectList = jsonUtil.jsonToSubjectList(subjectJson);

            Map<String, Score> map = new HashMap<String, Score>();

            String scoreJson = null;

            Score tempScore = null;

            for(Subject subject : subjectList){
                scoreUrl += subject.getSubjectId();
                // 获取对应的成绩JSON
                scoreJson = jsonUtil.loadJsonFromURL(scoreUrl);

                List<Score> scoreList = jsonUtil.jsonToScoreList(scoreJson);

                // 有可能该项科目的成绩尚未存在
                // 此时需要添加一个新的实体
                if(scoreList.size() == 0){
                    tempScore = new Score(0, subject.getSubjectId(), studentId, 0);
                }else{
                    tempScore = scoreList.get(0);
                }

                map.put(subject.getSubjectName(), tempScore);
            }

            // 将获得的列表添加到session中
            request.getSession().setAttribute("student_id", studentId);
            request.getSession().setAttribute("score_map", map);
            request.getSession().setAttribute("major_id", majorId);

            response.sendRedirect ("/subject/set_scores.jsp") ;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
