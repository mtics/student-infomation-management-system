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

public class BulletinSearchServlet extends HttpServlet {

    private JsonUtil jsonUtil = new JsonUtil();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
            url = "http://server.aspi.tech:8080/backend/bulletin/findallbyparams?";

            // 因为不确定哪个条件是存在的，所以之前每个都会加"&"
            // 因此最后就需要添加一个恒成立条件"1=1"
            // 在没有条件的情况下，与"/findall"等价
            url += tempParams + "1=1";

            System.out.println(url);

            try {

                String bulletinJson = jsonUtil.loadJsonFromURL(url);

                // 将获得的列表添加到cookie中
                request.getSession().setAttribute("bulletin_json", bulletinJson);

                response.sendRedirect("/bulletin/table_bulletin.jsp");
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
}
