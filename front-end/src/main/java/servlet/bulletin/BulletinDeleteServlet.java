package servlet.bulletin;

import entity.Bulletin;
import page.dao.PageDaoImpl;
import page.entity.Page;
import util.ClientUtil;
import util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BulletinDeleteServlet extends HttpServlet {

    private ClientUtil clientUtil = new ClientUtil();

    private JsonUtil jsonUtil = new JsonUtil();

    private PageDaoImpl pageDao = new PageDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean isSuccess = false;

        // 提交数据URL
        String url = "http://server.aspi.tech:8080/backend/bulletin/delete?";

        response.setContentType( "application/json;charset=UTF-8" );
        // 设置跨域请求头
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        // 设置编码为UTF-8
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String bulletinId = request.getParameter("bulletin_id");

        try {

            isSuccess = clientUtil.sendPost(url);

            if(isSuccess){

                // 更新公告信息
                String bulletinJson = jsonUtil.loadJsonFromURL("http://server.aspi.tech:8080/backend/bulletin/findall");

                List<Bulletin> bulletinList = jsonUtil.jsonToBulletinList(bulletinJson);

                Page<Bulletin> bulletinPage = new Page<Bulletin>(1, 10, bulletinList);

                bulletinList = pageDao.getDataListWithPage(bulletinPage.getCurrentPage(), bulletinPage);

                String handledBulletinListJson = jsonUtil.bullutinListToJson(bulletinList);

                // 将获得的列表添加到cookie中
                request.getSession().setAttribute("bulletin_json", handledBulletinListJson);
                request.getSession().setAttribute("bulletin_pages", bulletinPage.getTotalPages());
                request.getSession().setAttribute("bulletin_current_page", bulletinPage.getCurrentPage()-1);


                response.sendRedirect("/bulletin/table_bulletin.jsp");
            }else{
                // 如果删除失败，直接返回即可
                // session中的值不需要更新
                response.sendRedirect("/bulletin/table_bulletin.jsp");
            }

            //response.sendRedirect(url);
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("json解析失败");
        }
    }
}
