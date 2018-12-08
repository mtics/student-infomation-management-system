package bulletin.servlet;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import page.dao.PageDaoImpl;
import common.util.ClientUtil;
import common.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BulletinInfoServlet extends HttpServlet {


    private ClientUtil clientUtil = new ClientUtil();

    private JsonUtil jsonUtil = new JsonUtil();

    private PageDaoImpl pageDao = new PageDaoImpl();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String jspBulletinId = request.getParameter("bulletin_id");

        String url = "http://server.aspi.tech:8080/backend/bulletin/findbyid?bull_id="+jspBulletinId;

        try {

            String bulletinJson = jsonUtil.loadJsonFromURL(url);

            JsonObject json = (JsonObject)new JsonParser().parse(bulletinJson);

            System.out.println(bulletinJson);

            String bulletinId = json.get("bulletinId").toString();
            String bulletinTitle = json.get("bulletinTitle").toString().replace("\"", "");
            String bulletinContext = json.get("bulletinContext").toString().replace("\"", "");


            // 将获得的公告添加到session中
            request.getSession().setAttribute("bulletin_id", bulletinId);
            request.getSession().setAttribute("bulletin_title", bulletinTitle);
            request.getSession().setAttribute("bulletin_context", bulletinContext);

            System.out.println("bulletinId: "+bulletinId);
            System.out.println("bulletinTitle: "+bulletinTitle);
            System.out.println("bulletinContext: "+bulletinContext);

            response.sendRedirect ("/bulletin/form_bulletin_update.jsp") ;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
