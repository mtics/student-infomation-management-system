package common.servlet;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import bulletin.entity.Bulletin;
import student.Student;
import teacher.entity.Teacher;
import page.dao.PageDaoImpl;
import page.entity.Page;
import common.util.CookieUtil;
import common.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class LoginServlet extends HttpServlet {

    private JsonUtil jsonUtil = new JsonUtil();

    private CookieUtil cookieUtil = new CookieUtil();

    private PageDaoImpl pageDao = new PageDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType( "text/html" );

        // 设置编码为UTF-8
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();

        // get username
        String userName = (String)request.getParameter("text_username");
        // get password
        String passwd = (String)request.getParameter("text_password");

        // 从服务器后台获取该用户对应的信息
        String url = "http://server.aspi.tech:8080/backend/user/findbyid?user_id="+userName;

        try {
            String jsonStr = jsonUtil.loadJsonFromURL(url);

            // 若找不到该用户，则报错并跳回登录页
            if(jsonStr.equals("null")){
                System.out.println("没有该用户");
                out.print("<script>alert('没有该用户!')</script>");
                out.print("<script>window.location.href='/login.jsp'</script>");
            }

            // 若找到该用户的信息，则将其重新转换成JSON格式
            JsonObject json = (JsonObject)new JsonParser().parse(jsonStr);

            // 解析出来的密码会带\",所以需要将其消掉
            String passwdFromDataBase = json.get("passwd").toString().replace("\"","");

            // 获取用户等级以作权限区分
            String userLevel = json.get("userLevel").toString();

            // 检查密码
            if(passwd.equals(passwdFromDataBase)){
                System.out.println("密码正确");

                // 若密码正确，则将信息添加入Cookie
                Cookie userNameCookie = cookieUtil.setCookie("username", userName);
                Cookie userLevelCookie = cookieUtil.setCookie("userlevle", userLevel);
                response.addCookie(userNameCookie);
                response.addCookie(userLevelCookie);

                Cookie userPortraitCookie = null;


                // 从数据库中找出用户对应的学生/教师信息
                // 获得头像地址
                if(userName.length() == 11){
                    String studentUrl = "http://server.aspi.tech:8080/backend/student/findbyid?studentId="+userName;
                    String studentJson = jsonUtil.loadJsonFromURL(studentUrl);
                    Student student = jsonUtil.jsonToStudent(studentJson);
                    userPortraitCookie = cookieUtil.setCookie("portrait", student.getPortrait());
                    response.addCookie(userPortraitCookie);
                }else{
                    String teacherUrl = "http://server.aspi.tech:8080/backend/teacher/findbyid?teacherId="+userName;
                    String teacherJson = jsonUtil.loadJsonFromURL(teacherUrl);
                    Teacher teacher = jsonUtil.jsonToTeacher(teacherJson);
                    userPortraitCookie = cookieUtil.setCookie("portrait", teacher.getPortrait());
                    response.addCookie(userPortraitCookie);
                }

                System.out.println(userNameCookie.getValue()+"-----"+userPortraitCookie.getValue());

                // 添加一个公告信息
                String bulletinJson = jsonUtil.loadJsonFromURL("http://server.aspi.tech:8080/backend/bulletin/findall");

                List<Bulletin> bulletinList = jsonUtil.jsonToBulletinList(bulletinJson);

                Page<Bulletin> bulletinPage = new Page<Bulletin>(1, 10, bulletinList);

                bulletinList = pageDao.getDataListWithPage(bulletinPage.getCurrentPage(), bulletinPage);

                String handledBulletinListJson = jsonUtil.bullutinListToJson(bulletinList);

                // 将获得的列表添加到cookie中
                request.getSession().setAttribute("bulletins_json", handledBulletinListJson);
                request.getSession().setAttribute("bulletin_pages", bulletinPage.getTotalPages());
                request.getSession().setAttribute("bulletin_current_page", bulletinPage.getCurrentPage()-1);

                response.sendRedirect ("/index.jsp") ;
            }else {
                out.print("<script>alert('用户名或密码错误!')</script>");
                out.print("<script>window.location.href='/login.jsp'</script>");
                //response.sendRedirect ("/login.jsp") ;
            }
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("json解析失败");
        }

    }
}
