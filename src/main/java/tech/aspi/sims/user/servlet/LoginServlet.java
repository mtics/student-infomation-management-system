package tech.aspi.sims.user.servlet;

import tech.aspi.sims.user.controller.UserController;
import tech.aspi.sims.user.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "loginServlet",urlPatterns = "/servlet/loginServlet", description = "登录校验")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private User user;

    private UserController userController = new UserController();

    public LoginServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response. setContentType ( "text/html" ) ;
        // get username
        String userName = request.getParameter("text_username") ;
        // get password
        String passwd = request.getParameter("text_passwd") ;

        //get password from sql for checking
        user = userController.findOne(userName);

        //test
        System.out.println("username:"+userName+"\r\n"+"password:"+passwd);
        request . getSession( ).setAttribute ( "UserName" , userName ) ;
        request.getSession().setAttribute("password", passwd);

        // compare password from web with passowd from sql
        if(passwd.equals(user.getPasswd()))
        {
            response. sendRedirect ("index.jsp") ;
        }
        else {
            response. sendRedirect ("login.jsp") ;
        }
    }

}
