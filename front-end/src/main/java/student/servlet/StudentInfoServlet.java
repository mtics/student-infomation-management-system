package student.servlet;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import common.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StudentInfoServlet extends HttpServlet {

    private JsonUtil jsonUtil = new JsonUtil();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String jspStudentId = request.getParameter("studentId");

        String url = "http://server.aspi.tech:8080/backend/student/findbyid?studentId="+jspStudentId;

        try {

            String studentJson = jsonUtil.loadJsonFromURL(url);

            JsonObject json = (JsonObject)new JsonParser().parse(studentJson);

            System.out.println(studentJson);

            String userId = json.get("studentId").toString().replace("\"", "");
            String userName = json.get("studentName").toString().replace("\"", "");
            String userLevel = "1";     // 学生等级为1
            String birthday = json.get("birthday").toString().replace("\"", "");
            String gender = json.get("gender").toString().replace("\"", "");
            String email = json.get("email").toString().replace("\"", "");
            String majorId = json.get("majorId").toString();
            String portrait = json.get("portrait").toString().replace("\"", "");

            System.out.println(majorId);

            // 将获得的公告添加到session中
            request.getSession().setAttribute("user_id", userId);
            request.getSession().setAttribute("user_name", userName);
            request.getSession().setAttribute("user_level", userLevel);
            request.getSession().setAttribute("birthday", birthday);
            request.getSession().setAttribute("gender", gender);
            request.getSession().setAttribute("email", email);
            request.getSession().setAttribute("major_id", majorId);
            request.getSession().setAttribute("portrait", portrait);

            response.sendRedirect ("/user/form_user_update.jsp") ;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
