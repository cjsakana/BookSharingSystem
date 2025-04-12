package kg.us.sakanatang.utils;

import org.springframework.context.annotation.Configuration;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Configuration
public class EmailUtil {
    // 从系统环境中读取变量
    public static String sendEmailAccount = EnvReader.getEnv("EmailAccount");
    // 发件人的邮箱的授权码(自己在邮箱服务器中开启并设置)
    public static String sendEmailSecret = EnvReader.getEnv("EmailSecret");
    // 发件人邮箱的SMTP服务器地址，如：smtp.163.com,qq邮箱如下：
    public static String sendEmailSMTPHost = "smtp.qq.com";
    // QQ邮箱SSL端口
    public static String port = "465";


    // 把发送邮件封装为函数，参数为收件人的邮箱账号和要发送的内容
    public static void sendMail(String receiveMailAccount, String subject, String mailContent) {

        // 创建用于连接邮件服务器的参数配置
        Properties props = new Properties();
        // 设置使用SMTP协议
        props.setProperty("mail.transport.protocol", "smtp");
        // 设置发件人的SMTP服务器地址
        props.setProperty("mail.smtp.host", sendEmailSMTPHost);
        // 设置需要验证
        props.setProperty("mail.smtp.auth", "true");

        props.put("mail.smtp.port", port);
        props.put("mail.smtp.ssl.enable", "true"); // 启用SSL

        // 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getInstance(props);
        // 设置debug模式，便于查看发送过程所产生的日志
//        session.setDebug(true);

        try {
            // 创建一封邮件
            MimeMessage message = createMimeMessage(session, sendEmailAccount, receiveMailAccount, subject, mailContent);

            // 根据 Session 获取邮件传输对象
            Transport transport = session.getTransport();

            transport.connect(sendEmailAccount, sendEmailSecret);

            // 发送邮件, 发到所有的收件地址, 通过message.getAllRecipients() 可以获取到在创建邮件对象时添加的所有收件人
            transport.sendMessage(message, message.getAllRecipients());

            // 关闭连接
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param session     和服务器交互的会话
     * @param sendMail    发件人邮箱
     * @param receiveMail 收件人邮箱
     * @return
     * @throws Exception
     */
    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail, String subject,
                                                String mailContent) throws Exception {
        // 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 设置发件人姓名和编码格式
        message.setFrom(new InternetAddress(sendMail, "广软图书分享系统", "UTF-8"));

        // 收件人
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "尊敬的用户", "UTF-8"));

        // 设置邮件主题
        message.setSubject(subject, "UTF-8");

        // 设置邮件正文
        message.setContent(mailContent, "text/html;charset=UTF-8");

        // 设置发件时间
        message.setSentDate(new Date());

        // 保存设置
        message.saveChanges();

        return message;
    }
}
