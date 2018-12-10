package bulletin.servlet;

import bulletin.entity.Bulletin;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.sf.json.JSONArray;
import page.dao.PageDaoImpl;
import common.util.ClientUtil;
import common.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BulletinInfoServlet extends HttpServlet {


    private ClientUtil clientUtil = new ClientUtil();

    private JsonUtil jsonUtil = new JsonUtil();

    private PageDaoImpl pageDao = new PageDaoImpl();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String jspBulletinId = request.getParameter("bulletin_id");

        String url = "http://server.aspi.tech:8080/backend/bulletin/findall?bulletinId="+jspBulletinId;

        try {

            String bulletinJson = jsonUtil.loadJsonFromURL(url);

            List<Bulletin> bulletins = jsonUtil.jsonToBulletinList(bulletinJson);

            Bulletin bulletin = bulletins.get(0);

            System.out.println(bulletinJson);

            int bulletinId = bulletin.getBulletinId();
            String bulletinTitle = bulletin.getBulletinTitle();
            String bulletinContext = bulletin.getBulletinContext();

            // 将获得的公告添加到session中
            request.getSession().setAttribute("bulletin_id", bulletinId);
            request.getSession().setAttribute("bulletin_title", bulletinTitle);
            request.getSession().setAttribute("bulletin_context", bulletinContext);

            response.sendRedirect ("/bulletin/form_bulletin_update.jsp") ;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
