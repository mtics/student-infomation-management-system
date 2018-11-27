package util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Bulletin;

import java.util.ArrayList;

public class BulletinUtil {

    private JsonUtil jsonUtil = new JsonUtil();

    public void getBulletinsFromWeb(String url) throws Exception {

        // 获取字符串类型的JSON格式公告数据
        String bulletinJson = jsonUtil.loadJsonFromURL("http://server.aspi.tech:8080/backend/bulletin/findall");


    }
}
