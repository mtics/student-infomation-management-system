package cos.constant;

public class CosConstant {

    private static final String SECRET_ID = "AKIDdpuAGKyrrFfEpMJ57tUEj4wH3eAqAbGt";

    private static final String SECRET_KEY = "2UXQBuTvEPgMqZ4ywmGdvGOhqqfjzRaT";

    private static final String APPID = "1253813002";

    // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
    private static final String BUCKET_NAME = "incentancy-1253813002";

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
