package teacher.servlet;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import common.util.ClientUtil;
import common.util.JsonUtil;
import page.dao.PageDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TeacherInfoServlet extends HttpServlet {

    private JsonUtil jsonUtil = new JsonUtil();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String jspTeacherId = request.getParameter("teacherId");

        String url = "http://server.aspi.tech:8080/backend/teacher/findbyid?teacherId="+jspTeacherId;

        try {

            String teacherJson = jsonUtil.loadJsonFromURL(url);

            JsonObject json = (JsonObject)new JsonParser().parse(teacherJson);

            System.out.println(teacherJson);

            String userId = json.get("teacherId").toString().replace("\"", "");
            String userName = json.get("teacherName").toString().replace("\"", "");
            String userLevel = "2";     // 教师等级为2
            String birthday = json.get("birthday").toString().replace("\"", "");
            String gender = json.get("gender").toString().replace("\"", "");
            String email = json.get("email").toString().replace("\"", "");
            String collegeId = json.get("collegeId").toString();
            String portrait = json.get("portrait").toString().replace("\"", "");

            System.out.println(collegeId);

            // 将获得的公告添加到session中
            request.getSession().setAttribute("user_id", userId);
            request.getSession().setAttribute("user_name", userName);
            request.getSession().setAttribute("user_level", userLevel);
            request.getSession().setAttribute("birthday", birthday);
            request.getSession().setAttribute("gender", gender);
            request.getSession().setAttribute("email", email);
            request.getSession().setAttribute("major_id", collegeId);
            request.getSession().setAttribute("portrait", portrait);

            response.sendRedirect ("/user/form_user_update.jsp") ;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
