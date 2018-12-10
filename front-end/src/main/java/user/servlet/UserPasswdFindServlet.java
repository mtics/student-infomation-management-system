package user.servlet;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import common.util.JsonUtil;
import email.entity.Email;
import net.sf.json.JSONArray;
import student.entity.Student;
import teacher.entity.Teacher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UserPasswdFindServlet extends HttpServlet {

    private JsonUtil jsonUtil = new JsonUtil();

    private Email email = new Email();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String jspUserId = request.getParameter("text_username");

        String findUserUrl = "http://server.aspi.tech:8080/backend/user/findbyid?userId="+jspUserId;

        PrintWriter printWriter = response.getWriter();

        try {

            String jsonStr = jsonUtil.loadJsonFromURL(findUserUrl);

            // 若找不到该用户，则报错并跳回密码找回页
            if(jsonStr.equals("null")){
                printWriter.print("<script>alert('没有该用户!')</script>");
                printWriter.print("<script>window.location.href='/login.jsp'</script>");
            }

            JsonObject json = (JsonObject)new JsonParser().parse(jsonStr);

            // 获得用户密码
            String passwd = json.get("passwd").toString().replace("\"", "");

            // 接下来找到用户的邮箱地址
            if(jspUserId.length() == 11){
                findUserUrl = "http://server.aspi.tech:8080/backend/student/findall?studentId="+jspUserId;
            }else{
                findUserUrl = "http://server.aspi.tech:8080/backend/teacher/findall?teacherId="+jspUserId;
            }

            jsonStr = jsonUtil.loadJsonFromURL(findUserUrl);

            String emailAddress;


            if(jspUserId.length() == 11){
                List<Student> studentList = jsonUtil.jsonToStudentList(jsonStr);
                emailAddress = studentList.get(0).getEmail();
            }else{
                List<Teacher> teacherList = jsonUtil.jsonToTeacherList(jsonStr);
                emailAddress = teacherList.get(0).getEmail();
            }

            // 模拟邮箱发送
            // 设置收件地址
            email.setEmailTo(emailAddress);
            // 设置正文为密码
            email.setContent(passwd);
            // 发送邮件
            email.sendMail();

            // 跳转至登录页
            printWriter.println("<script>alert('The password had already been sent to you e-mail!')</script>");
            printWriter.print("<script>window.location.href='/login.jsp'</script>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
