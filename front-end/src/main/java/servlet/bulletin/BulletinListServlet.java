package servlet.bulletin;

import entity.Bulletin;
import page.dao.PageDaoImpl;
import page.entity.Page;
import util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BulletinListServlet extends HttpServlet {

    private Page<Bulletin> bulletinPage = null;

    private JsonUtil jsonUtil = new JsonUtil();

    private PageDaoImpl pageDao = new PageDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println(request.getParameter("page_num").toString());

        // 获取当前是页数
        int currentPage = Integer.valueOf(request.getParameter("page_num"));
        // 设置每页数据数
        int pageSize = 10;

        // 后台查询公告的链接
        String url = "http://server.aspi.tech:8080/backend/bulletin/findall";

        try {

            String bulletinJson = jsonUtil.loadJsonFromURL(url);

            List<Bulletin> bulletinList = jsonUtil.jsonToBulletinList(bulletinJson);

            Page<Bulletin> bulletinPage = new Page<Bulletin>(currentPage, pageSize, bulletinList);

            bulletinList = pageDao.getDataListWithPage(bulletinPage.getCurrentPage(), bulletinPage);

            String handledBulletinListJson = jsonUtil.bullutinListToJson(bulletinList);

            // 将获得的列表添加到cookie中
            request.getSession().setAttribute("bulletin_json", handledBulletinListJson);
            request.getSession().setAttribute("bulletin_pages", bulletinPage.getTotalPages());
            request.getSession().setAttribute("bulletin_current_page", bulletinPage.getCurrentPage()-1);

            response.sendRedirect ("/bulletin/table_bulletin.jsp") ;
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
