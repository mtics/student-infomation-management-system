package servlet;

import entity.Bulletin;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取当前是页数
        int currentPage = Integer.valueOf(req.getParameter("current_page"));
        // 设置每页数据数
        int pageSize = 10;

        // 后台查询公告的链接
        String url = "http://server.aspi.tech:8080/backend/bulletin/findall";

        try {
            String jsonStr = jsonUtil.loadJsonFromURL(url);

            List<Bulletin> bulletinList = jsonUtil.jsonToBulletinList(jsonStr);

            bulletinPage = new Page<Bulletin>(currentPage, pageSize, bulletinList);

            // 将获得的列表添加到cookie中
            req.setAttribute("bulletin_list", bulletinPage.getList());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
