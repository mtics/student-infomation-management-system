package student.servlet;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import common.util.JsonUtil;
import student.entity.Student;
import teacher.entity.Teacher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class StudentInfoServlet extends HttpServlet {

    private JsonUtil jsonUtil = new JsonUtil();

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String jspStudentId = request.getParameter("studentId");

        String url = "http://server.aspi.tech:8080/backend/student/findall?studentId="+jspStudentId;

        try {

            String studentJson = jsonUtil.loadJsonFromURL(url);

            Student student = jsonUtil.jsonToStudent(studentJson);

            System.out.println(studentJson);

            String userId = student.getStudentId();
            String userName = student.getStudentName();
            String userLevel = "2";     // 教师等级为2
            String birthday = simpleDateFormat.format(student.getBirthday());
            String gender = student.getGender();
            String email = student.getEmail();
            int majorId = student.getMajorId();
            String portrait = student.getPortrait();
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
