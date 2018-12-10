package user.servlet;

import common.util.ClientUtil;
import org.apache.commons.fileupload.FileUploadException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

public class UserPasswdUpdateServlet extends HttpServlet {

    private ClientUtil clientUtil = new ClientUtil();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userId = request.getParameter("text_user_id");

        String userLevel = request.getParameter("text_user_level");

        String newPasswd = request.getParameter("text_user_new_passwd");

        String url = "http://server.aspi.tech:8080/backend/user/save?userId="
                    +userId+"&userLevel="+userLevel+"&passwd="+newPasswd;

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

        boolean isSuccess = false;

        try{
            // 以post方式提交，返回是否成功
            isSuccess = clientUtil.sendPost(url.toString());

            if(isSuccess){
                out.print("<script>alert('密码修改成功！')</script>");
                out.print("<script>window.location.href='/user/form_user_passwd.jsp'</script>");
                //response.sendRedirect ("/login.jsp") ;
            }else{

                out.print("<script>alert('密码修改失败！')</script>");
                out.print("<script>window.location.href='/user/form_user_passwd.jsp'</script>");
            }

        } catch (Exception e){
            e.printStackTrace();
        }


    }
}
