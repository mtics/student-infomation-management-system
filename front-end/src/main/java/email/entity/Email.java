package email.entity;

import email.constant.AdminEmailAccount;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

public class Email {

    // 收件人电子邮箱
    private String emailTo;

    // 发件人电子邮箱
    private String emailFrom;

    // 发送邮件的主机
    private String host;

    // 获取系统属性
    private Properties properties;

    // 获取默认MimeMessage对象
    private MimeMessage message;

    // session对象
    private Session session;

    //邮件传输对象
    private Transport transport;

    // 邮件内容
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = "您的密码是： "+content;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties( ) {
        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        properties = new Properties();

        properties.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        properties.setProperty("mail.smtp.host", AdminEmailAccount.EMAIL_SMTP_HOST);   // 发件人的邮箱的 SMTP 服务器地址
        properties.setProperty("mail.smtp.auth", "true");            // 需要请求认证

        // PS: 某些邮箱服务器要求 SMTP 连接需要使用 SSL 安全认证 (为了提高安全性, 邮箱支持SSL连接, 也可以自己开启),
        //     如果无法连接邮件服务器, 仔细查看控制台打印的 log, 如果有有类似 “连接失败, 要求 SSL 安全连接” 等错误,
        //     打开下面 /* ... */ 之间的注释代码, 开启 SSL 安全连接。
        // SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接,
        //                  需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助,
        //                  QQ邮箱的SMTP(SLL)端口为465或587, 其他邮箱自行去查看)
        properties.setProperty("mail.smtp.port", AdminEmailAccount.EMAIL_SMTP_PORT);
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.socketFactory.port", AdminEmailAccount.EMAIL_SMTP_PORT);

    }

    public MimeMessage getMessage() {
        return message;
    }

    // 创建邮件对象
    public void setMessage( ) throws UnsupportedEncodingException, MessagingException {

        message = new MimeMessage(session);
        // From: 发件人
        //    其中 InternetAddress 的三个参数分别为: 邮箱, 显示的昵称(只用于显示, 没有特别的要求), 昵称的字符集编码
        //    真正要发送时, 邮箱必须是真实有效的邮箱。
        message.setFrom(new InternetAddress(AdminEmailAccount.EMAIL_ACCOUNT, "admin", "UTF-8"));

        // 3. To: 收件人
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(emailTo, "尊敬的用户", "UTF-8"));

        // 4. Subject: 邮件主题
        message.setSubject("密码找回", "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent(content, "text/plain;charset=UTF-8");

        // 6. 设置显示的发件时间
        message.setSentDate(new Date());

        // 7. 保存前面的设置
        message.saveChanges();
    }

    public Session getSession() {
        return session;
    }

    // 根据参数配置，创建会话对象（为了发送邮件准备的)
    public void setSession( ) {
        setProperties();
        session = Session.getInstance(properties);
    }

    public Transport getTransport() {
        return transport;
    }

    public void sendMail( ) throws MessagingException, UnsupportedEncodingException {

        setSession();

        transport = session.getTransport();

        // 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
        //
        //    PS_01: 成败的判断关键在此一句, 如果连接服务器失败, 都会在控制台输出相应失败原因的 log,
        //           仔细查看失败原因, 有些邮箱服务器会返回错误码或查看错误类型的链接, 根据给出的错误
        //           类型到对应邮件服务器的帮助网站上查看具体失败原因。
        //
        //    PS_02: 连接失败的原因通常为以下几点, 仔细检查代码:
        //           (1) 邮箱没有开启 SMTP 服务;
        //           (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
        //           (3) 邮箱服务器要求必须要使用 SSL 安全连接;
        //           (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
        //           (5) 如果以上几点都确定无误, 到邮件服务器网站查找帮助。
        //
        //    PS_03: 仔细看log, 认真看log, 看懂log, 错误原因都在log已说明。
        transport.connect(AdminEmailAccount.EMAIL_ACCOUNT, AdminEmailAccount.EMAIL_PASSWORD);

        // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        setMessage();
        transport.sendMessage(message, message.getAllRecipients());

        // 7. 关闭连接
        transport.close();
    }
}
