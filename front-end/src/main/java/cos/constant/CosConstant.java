package cos.constant;

public class CosConstant {

    private static final String SECRET_ID = "SECRET_ID";

    private static final String SECRET_KEY = "SECRET_KEY";

    private static final String APPID = "APPID";

    // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
    private static final String BUCKET_NAME = "BUCKET_NAME-APPID";

    private static final String PRE_URL = "PRE_URL";

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
