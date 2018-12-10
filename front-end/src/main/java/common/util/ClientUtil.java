package common.util;

import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;

public class ClientUtil {

    public boolean sendPost(String url) throws IOException {

        boolean isSuccess = false;

        String responseStr = null;

        // 创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost post = null;

        CloseableHttpResponse response = null;

        // 创建Http POST请求
        post = new HttpPost(url);
        // 构造消息头
        post.addHeader("Content-type", "application/json; charset=UTF-8");
        post.setHeader("Accept", "*/*");

        // 伪装浏览器
        post.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");

        try{

            response= httpClient.execute(post);

            responseStr = EntityUtils.toString(response.getEntity());

            // 检验返回码
            int statusCode = response.getStatusLine().getStatusCode();

            if(statusCode != HttpStatus.SC_OK){
                isSuccess = false;
            }else {
                isSuccess = true;
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.close();
            }
            httpClient.close();
        }

        return isSuccess;
    }
}
