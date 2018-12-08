package common.servlet;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import common.util.ExpertUtil;
import cos.util.CosUtil;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class ExcelExpertServlet extends HttpServlet {

    private CosUtil cosUtil = new CosUtil();

    private ExpertUtil expertUtil = new ExpertUtil();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String jsonStr = request.getParameter("json_info");

        System.out.println(jsonStr);
        System.out.println(request.getRequestURI());
        System.out.println(request.getRequestURL());

        JsonObject json = (JsonObject)new JsonParser().parse(jsonStr);

        JSONArray jsonArray = JSONArray.fromObject(json);

        File file = expertUtil.exportExcel("temp.xls", jsonArray, "entity");

        String downloadUrl = null;

        try {
            downloadUrl = cosUtil.uploadFile(file);

            System.out.println(downloadUrl);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
