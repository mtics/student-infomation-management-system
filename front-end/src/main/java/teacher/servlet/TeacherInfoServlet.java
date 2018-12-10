package teacher.servlet;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import common.util.ClientUtil;
import common.util.JsonUtil;
import page.dao.PageDaoImpl;
import teacher.entity.Teacher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class TeacherInfoServlet extends HttpServlet {

    private JsonUtil jsonUtil = new JsonUtil();

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String jspTeacherId = request.getParameter("teacherId");

        String url = "http://server.aspi.tech:8080/backend/teacher/findall?teacherId="+jspTeacherId;

        try {

            String teacherJson = jsonUtil.loadJsonFromURL(url);

            Teacher teacher = jsonUtil.jsonToTeacher(teacherJson);

            System.out.println(teacherJson);

            String userId = teacher.getTeacherId();
            String userName = teacher.getTeacherName();
            String userLevel = "2";     // 教师等级为2
            String birthday = simpleDateFormat.format(teacher.getBirthday());
            String gender = teacher.getGender();
            String email = teacher.getEmail();
            int collegeId = teacher.getCollegeId();
            String portrait = teacher.getPortrait();

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
