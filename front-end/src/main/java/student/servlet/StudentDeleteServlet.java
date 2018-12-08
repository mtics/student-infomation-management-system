package student.servlet;

import common.util.ClientUtil;
import common.util.JsonUtil;
import page.dao.PageDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StudentDeleteServlet extends HttpServlet {

    private ClientUtil clientUtil = new ClientUtil();

    private JsonUtil jsonUtil = new JsonUtil();

    private PageDaoImpl pageDao = new PageDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isSuccess = false;

        // 提交数据URL
        String url = "http://server.aspi.tech:8080/backend/student/deletebyid?studentId=";

        response.setContentType( "text/plain;charset=UTF-8" );
        // 设置跨域请求头
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        // 设置编码为UTF-8
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String studentId = request.getParameter("studentId");

        try {

            isSuccess = clientUtil.sendPost(url+studentId);

            System.out.println(isSuccess);

            if(isSuccess){
                response.sendRedirect("/servlet/student/list");
            }else{
                // 如果删除失败，直接返回即可
                // session中的值不需要更新
                response.sendRedirect("/user/table_student.jsp");
            }

            //response.sendRedirect(url);
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("json解析失败");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
