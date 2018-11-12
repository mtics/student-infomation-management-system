package tech.aspi.sims.backend.user.servlet;

import tech.aspi.sims.backend.user.model.User;
import tech.aspi.sims.backend.user.controller.UserController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "loginServlet",urlPatterns = "/servlet/loginServlet", description = "登录校验")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private Optional<User> op_user;

    private User user;

    private UserController userController = new UserController();

    public LoginServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        super.doPost(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType( "text/html" );

        // 设置编码为UTF-8
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        // get username
        String userName = (String)request.getParameter("text_username");
        // get password
        String passwd = (String)request.getParameter("text_passwd");

        //get password from sql for checking
        op_user = userController.findById(userName);
        user = op_user.get();

        //test
        System.out.println("username:"+userName+"\r\n"+"password:"+passwd);

        // compare password from web with passowd from sql
        if(passwd.equals(user.getPasswd()))
        {
            request.getSession().setAttribute ("username", userName);
            request.getSession().setAttribute("password", passwd);
            response.sendRedirect ("http://server.aspi.tech:8080/frontend") ;
        }
        else {
            response.sendRedirect ("http://server.aspi.tech:8080/frontend/login.jsp") ;
        }
    }

}
