package common.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class VerificationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session =request.getSession();
        String verificationCode = (String)session.getAttribute("verificationCode");
        String checkcode = request.getParameter("op");
        PrintWriter out = response.getWriter();
        if(checkcode.toLowerCase().equals(verificationCode.toLowerCase())){
            out.println(1);
        }else{
            out.println(0);
        }
        out.flush();
        out.close();
    }
}
