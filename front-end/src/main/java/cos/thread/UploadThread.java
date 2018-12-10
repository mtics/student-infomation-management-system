package cos.thread;

import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.UploadResult;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.Upload;
import cos.constant.CosConstant;
import cos.util.CosUtil;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UploadThread extends Thread {

    private URL url;

    private String fileName;

    private File file;

    // 线程池大小，建议在客户端与COS网络充足(如使用腾讯云的CVM，同园区上传COS)的情况下，设置成16或32即可, 可较充分的利用网络资源
    // 对于使用公网传输且网络带宽质量不高的情况，建议减小该值，避免因网速过慢，造成请求超时。
    ExecutorService threadPool = Executors.newFixedThreadPool(32);
    // 传入一个 threadpool, 若不传入线程池, 默认 TransferManager 中会生成一个单线程的线程池。
    TransferManager transferManager = new TransferManager(CosUtil.getCosClient(), threadPool);

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm ss");

    CosUtil cosUtil = new CosUtil();

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public void run() {
        try{

            File localFile = file;

            fileName = file.getName();

            PutObjectRequest putObjectRequest = new PutObjectRequest(CosConstant.getBucketName(), fileName, localFile);
            // .....(提交上传下载请求, 如下文所属)
            // 本地文件上传
            Upload upload = transferManager.upload(putObjectRequest);
            // 异步,等待传输结束（如果想同步的等待上传结束，则调用 waitForCompletion）
            UploadResult uploadResult = upload.waitForUploadResult();

            // 获取上传成功之后文件的下载地址
            url = cosUtil.getCosClient().generatePresignedUrl(CosConstant.getBucketName(), CosConstant.getSecretKey(),
                    new Date(new Date().getTime() + 5 * 60 * 10000));

        } catch (InterruptedException e) {
            System.out.println("上传失败！");
            e.printStackTrace();
        }finally {
            // 关闭TransferManager
            transferManager.shutdownNow();
        }    }
}
