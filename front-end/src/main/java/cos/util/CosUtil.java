package cos.util;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.UploadResult;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.Download;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.Upload;
import cos.constant.CosConstant;
import cos.thread.UploadThread;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class CosUtil {

    // 线程池大小，建议在客户端与COS网络充足(如使用腾讯云的CVM，同园区上传COS)的情况下，设置成16或32即可, 可较充分的利用网络资源
    // 对于使用公网传输且网络带宽质量不高的情况，建议减小该值，避免因网速过慢，造成请求超时。
    ExecutorService threadPool = Executors.newFixedThreadPool(32);
    // 传入一个 threadpool, 若不传入线程池, 默认 TransferManager 中会生成一个单线程的线程池。
    TransferManager transferManager = new TransferManager(CosUtil.getCosClient(), threadPool);

    /**
     * @Description: 生成客户端COSClient对象
     * @return
     */
    public static COSClient getCosClient(){
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(CosConstant.getAPPID(),CosConstant.getSecretId(), CosConstant.getSecretKey());
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig中包含了设置region, https(默认http), 超时, 代理等set方法, 使用可参见源码或者接口文档FAQ中说明
        ClientConfig clientConfig = new ClientConfig(new Region("ap-guangzhou"));
        // 3 生成cos客户端
        COSClient cosClient = new COSClient(cred, clientConfig);

        return cosClient;
    }

    /**
     * 上传路径中的文件到COS
     * 返回的是外链。
     * 如果上传的文件名重复，会覆盖原文件
     * @param filePath
     */
    public String uploadFile(final String filePath) throws InterruptedException {

        // 创建新线程
        UploadThread uploadThread = new UploadThread();
        // 设置要上传的文件路径
        uploadThread.setFilePath(filePath);
        // 新线程上传文件
        uploadThread.start();
        //将异步执行变成同步执行
        uploadThread.join();

        // 外链=前缀+文件名
        String url = CosConstant.getPreUrl()+ uploadThread.getFileName();

        System.out.println(url);

        // 返回接收上传文件后获得的外链
        return url;
    }

    public void download(final String filePath) {
        try {
            //下载到本地指定路径
            File localDownFile = new File(filePath);
            GetObjectRequest getObjectRequest = new GetObjectRequest(CosConstant.getBucketName(), CosConstant.getSecretKey());
            // 下载文件
            Download download = transferManager.download(getObjectRequest, localDownFile);
            // 等待传输结束（如果想同步的等待上传结束，则调用 waitForCompletion）
            download.waitForCompletion();
            System.out.println("下载成功");
        } catch (Throwable tb) {
            System.out.println("下载失败");
            tb.printStackTrace();
        } finally {
            // 关闭 TransferManger
            transferManager.shutdownNow();
        }
    }
}
