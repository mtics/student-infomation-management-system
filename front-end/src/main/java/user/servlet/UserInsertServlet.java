package user.servlet;

import bulletin.entity.Bulletin;
import com.google.gson.JsonObject;
import com.qcloud.cos.transfer.MultipleFileUpload;
import common.util.ClientUtil;
import common.util.JsonUtil;
import cos.util.CosUtil;
import page.dao.PageDaoImpl;
import page.entity.Page;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserInsertServlet extends HttpServlet {


    private ClientUtil clientUtil = new ClientUtil();

    private JsonUtil jsonUtil = new JsonUtil();

    private PageDaoImpl pageDao = new PageDaoImpl();

    private CosUtil cosUtil = new CosUtil();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean isSuccess = false;

        // 提交数据URL
        StringBuffer url = null;

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

        // 从网页获取用户填入的信息
        String userId = request.getParameter("text_user_id");
        String userName = request.getParameter("text_user_name");
        String gender = request.getParameter("text_user_gender");
        String birthday = request.getParameter("text_user_birthday");
        String phone = request.getParameter("text_user_phone");
        String portraitPath = request.getParameter("text_user_portrait");
        System.out.println("+++++++++++++++"+request.getParameter("text_user_level"));

        int userLevel = Integer.parseInt(request.getParameter("text_user_level"));

        System.out.println("============"+portraitPath);

        if(userLevel == 1){
            url = new StringBuffer("http://server.aspi.tech:8080/backend/student/save?");
            url.append("studentId="+userId);
            url.append("&studentName="+userName);
        }else if(userLevel == 2){
            url = new StringBuffer("http://server.aspi.tech:8080/backend/teacher/save?");
            url.append("teacherId="+userId);
            url.append("&teacherName="+userName);
        }


        if(!gender.equals("")){
            url.append("&gender="+gender);
        }
        if(!birthday.equals("")){
            url.append("&birthday="+birthday);
        }
        if(!phone.equals("")){
            url.append("&phone="+phone);
        }



        try {

            // 要先将照片上传到COS中，再将外链存入数据库
            if(!portraitPath.equals("")){
                String portraitUrl = cosUtil.uploadFile(portraitPath);

                url.append("&portrait="+portraitUrl);
            }

            // 以post方式提交，返回是否成功
            isSuccess = clientUtil.sendPost(url.toString());

            if(isSuccess){
                response.sendRedirect("/index.jsp");
            }else{
                response.sendRedirect("/bulletin/form_user.jsp");
            }

            //response.sendRedirect(url);
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("用户信息新建失败");
        }
    }
}
