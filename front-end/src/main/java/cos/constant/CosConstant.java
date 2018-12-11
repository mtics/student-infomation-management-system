package cos.constant;

public class CosConstant {

    private static final String SECRET_ID = "AKIDY1LUQDYBQ070nQT2b0EICFCSm6FBwXvV";

    private static final String SECRET_KEY = "b18GG6j9QZXI1JSTHtNo5EJF56ye7NTO";

    private static final String APPID = "1253813002";

    // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
    private static final String BUCKET_NAME = "incentancy-1253813002";

    private static final String PRE_URL = "https://incentancy-1253813002.cos.ap-guangzhou.myqcloud.com";

    public static String getPreUrl() {
        return PRE_URL;
    }

    public static String getBucketName() {
        return BUCKET_NAME;
    }

    public static String getAPPID() {
        return APPID;
    }

    public static String getSecretId() {
        return SECRET_ID;
    }

    public static String getSecretKey() {
        return SECRET_KEY;
    }
}
