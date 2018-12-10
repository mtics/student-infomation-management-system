package subject.servlet;

import common.util.ClientUtil;
import common.util.JsonUtil;
import subject.entity.Subject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ScoreSubmitServlet extends HttpServlet {

    private JsonUtil jsonUtil = new JsonUtil();

    private ClientUtil clientUtil = new ClientUtil();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String studentId = request.getParameter("text_user_id");
        String majorId = request.getParameter("text_major_id");

        // 用于找到所有该majorId下的课程
        String subjectUrl = "http://server.aspi.tech:8080/backend/subject/findall?majorId="+majorId;

        // 获得所有课程
        String subjectJson = null;
        try {

            subjectJson = jsonUtil.loadJsonFromURL(subjectUrl);

            List<Subject> subjectList = jsonUtil.jsonToSubjectList(subjectJson);

            // 用于找到所有该学号和课程对应的成绩
            String scoreUrl = null;

            // 前端传过来的有课程的id和成绩
            for(Subject subject : subjectList){

                scoreUrl = "http://server.aspi.tech:8080/backend/score/save?studentId="+studentId+"&subjectId=";

                int subjectId = subject.getSubjectId();

                // 获取成绩
                double scoreValue = Double.parseDouble(request.getParameter("text_score_value_"+subjectId));
                // 获取成绩ID
                int scoreId = Integer.parseInt(request.getParameter("text_score_id_"+subjectId));


                // 如果成绩出错，则设为0
                if(scoreValue < 0 || scoreValue > 100){
                    scoreValue = 0;
                }

                scoreUrl += subjectId + "&scoreValue="+scoreValue;

                if(scoreId != 0){
                    scoreUrl += "&scoreId="+scoreId;
                }

                boolean isSuccess = clientUtil.sendPost(scoreUrl);

                if(!isSuccess){
                    System.out.println(subject.getSubjectName() + "成绩上传出错！");
                }
            }

            response.sendRedirect("/index.html");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
