package util;

import com.google.gson.*;
import entity.Bulletin;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {

    private Gson gson = new Gson();

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");

    /**
     * JSON读取工具，返回String类型的JSON
     */
    public String loadJsonFromURL(String url) throws Exception{
        //读取URL， 返回JSON串
        String json = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);

        if (response != null){
            HttpEntity entity =  response.getEntity();  //获取网页内容
            json = EntityUtils.toString(entity, "UTF-8");
        }

        if (response != null){
            response.close();
        }
        if (httpClient != null) {
            httpClient.close();
        }
        return json;
    }

    /**
     * 将JSON解析成List<Bulletin>
     * @param jsonStr
     * @return
     */
    public List<Bulletin> jsonToBulletinList(String jsonStr) throws ParseException {

        System.out.println(jsonStr);

        JSONArray jsonArray = JSONArray.fromObject(jsonStr);

        List<Bulletin> list = new ArrayList<Bulletin>();

        Bulletin bulletin = null;

        for (int i = 0; i < jsonArray.size(); i ++){

            JSONObject jsonObject = jsonArray.getJSONObject(i);

            bulletin = new Bulletin(jsonObject.getInt("bulletinId"), jsonObject.getString("userId"),
                    jsonObject.getString("bulletinTitle"), sdf.parse(jsonObject.getString("publishedDate")),
                    jsonObject.getString("bulletinContext"));

            list.add(bulletin);
        }

        return list;
    }

    /**
    public static void main(String[] args){
        String url = "http://server.aspi.tech:8080/backend/user/findbyid?user_id=20181151105";
        try {
            System.out.println(loadJsonFromURL(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     */
}
