package servlet;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import util.ClientUtil;
import util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class BulletinServlet extends HttpServlet {

    private ClientUtil clientUtil = new ClientUtil();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * 截取html提交的公告信息，
     * 添加用户信息后再以POST方式提交
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JsonObject bulletinInfo = new JsonObject();

        boolean isSuccess = false;

        // 提交数据URL
        String url = "http://server.aspi.tech:8080/backend/bulletin/save?";

        response.setContentType( "application/json;charset=UTF-8" );
        // 设置跨域请求头
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        // 设置编码为UTF-8
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        // 获取 bulletin's title
        String bulletinTitle = request.getParameter("text_bulletin_title");
        // 获取 bulletin's content
        String bulletinContext = request.getParameter("text_bulletin_context");


        Cookie[] cookies = request.getCookies();

        String userName = null;

        for(int i = 0; i < cookies.length; i ++){

            if(cookies[i].getName().equals("username")){
                userName = cookies[i].getValue();
            }
        }

        System.out.println("userId="+userName+
                "&bulletinTitle="+bulletinTitle+"&bulletinContext="+bulletinContext);

        url += "userId="+userName+
                "&bulletinTitle="+bulletinTitle+"&bulletinContext="+bulletinContext;

        System.out.println(bulletinInfo.toString());

        try {

            isSuccess = clientUtil.sendPost(url);

            if(isSuccess){
                response.sendRedirect("/bulletin/table_bulletin.jsp");
            }else{
                response.sendRedirect("/bulletin/form_bulletin.jsp");
            }

            //response.sendRedirect(url);
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("json解析失败");
        }
    }
}
