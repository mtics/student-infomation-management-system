package servlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.User;
import util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {

    private JsonUtil jsonUtil = new JsonUtil();

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

        String url = "http://server.aspi.tech:8080/backend/user/findbyid?user_id="+userName;
        try {
            String jsonStr = jsonUtil.loadJsonFromURL(url);

            if(jsonStr.equals("null")){
                System.out.println("没有该用户");
                out.print("<script>alert('没有该用户!')</script>");
                out.print("<script>window.location.href='/login.jsp'</script>");
            }

            JsonObject json = (JsonObject)new JsonParser().parse(jsonStr);

            //解析出来的密码会带\",所以需要将其消掉
            if(passwd.equals(json.get("passwd").toString().replace("\"",""))){
                System.out.println("密码正确");
                request.getSession().setAttribute ("username", userName);
                request.getSession().setAttribute("password", passwd);
                response.sendRedirect ("/index.jsp") ;
            }
            else {
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
