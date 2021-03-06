package bulletin.servlet;

import bulletin.entity.Bulletin;
import common.util.ExpertUtil;
import cos.util.CosUtil;
import page.dao.PageDaoImpl;
import page.entity.Page;
import common.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class BulletinListServlet extends HttpServlet {

    private JsonUtil jsonUtil = new JsonUtil();

    private PageDaoImpl pageDao = new PageDaoImpl();

    private ExpertUtil expertUtil = new ExpertUtil();

    private CosUtil cosUtil = new CosUtil();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 获取当前是页数
        int currentPage = Integer.valueOf(request.getParameter("page_num") == null ? "1" : request.getParameter("page_num"));
        // 设置每页数据数
        int pageSize = 10;

        // 后台查询公告的链接
        String url = "http://server.aspi.tech:8080/backend/bulletin/findall";

        String searchBulletinTitle = request.getParameter("search_bulletin_title");
        String searchBulletinContext = request.getParameter("search_bulletin_context");
        String searchUserId = request.getParameter("search_user_id");

        boolean isExistSearchBulletinTitle = (searchBulletinTitle != null && !searchBulletinTitle.equals(""));
        boolean isExistSearchBulletinContext = (searchBulletinContext != null && !searchBulletinContext.equals(""));
        boolean isExistSearchUserId = (searchUserId != null && !searchUserId.equals(""));

        String tempParams = "";

        // 检查条件，存在就拼接上去
        if (isExistSearchBulletinTitle) {
            tempParams += "bulletinTitle=" + searchBulletinTitle + "&";
        }
        if (isExistSearchBulletinContext) {
            tempParams += "bulletinContext=" + searchBulletinContext + "&";
        }
        if (isExistSearchUserId) {
            tempParams += "userId=" + searchUserId + "&";
        }

        // 如果三个条件存在一个，那么就跳转到/findallbyparams里进行条件查询
        if (isExistSearchBulletinTitle ||
                isExistSearchBulletinContext || isExistSearchUserId) {


            // 因为不确定哪个条件是存在的，所以之前每个都会加"&"
            // 因此最后就需要添加一个恒成立条件"1=1"
            // 在没有条件的情况下，与"/findall"等价
            url += "?"+tempParams + "1=1";

        }

        try {

            String bulletinJson = jsonUtil.loadJsonFromURL(url);

            List<Bulletin> bulletinList = jsonUtil.jsonToBulletinList(bulletinJson);

            Page<Bulletin> bulletinPage = new Page<Bulletin>(currentPage, pageSize, bulletinList);

            bulletinList = pageDao.getDataListWithPage(bulletinPage.getCurrentPage(), bulletinPage);

            String handledBulletinListJson = jsonUtil.bullutinListToJson(bulletinList);

            String allKeyNames = bulletinList.get(0).getAllKeyNames();

            String allValues = "";

            for(int i = 0; i < bulletinList.size(); i++){
                allValues += bulletinList.get(i).getAllValues()+"\r";
            }

            File file = expertUtil.getCsvFile(allKeyNames, allValues, "bulletinFile.csv");

            String currentPageListUrl = cosUtil.uploadFile(file);

            // 将获得的列表添加到session中
            request.getSession().setAttribute("bulletins_json", handledBulletinListJson);
            request.getSession().setAttribute("bulletin_pages", bulletinPage.getTotalPages());
            request.getSession().setAttribute("bulletin_current_page", bulletinPage.getCurrentPage()-1);
            request.getSession().setAttribute("bulletins_current", currentPageListUrl);

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
