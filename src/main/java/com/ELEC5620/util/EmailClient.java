//package com.ELEC5620.util;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Component;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.context.Context;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//import java.util.Map;
//
//@Component
//public class EmailClient {
//
//    private static final Logger logger = LoggerFactory.getLogger(EmailClient.class);
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    @Autowired
//    private TemplateEngine templateEngine;
//
//    @Value("${spring.mail.username}")
//    private String from;
//
//    public void sendMail(String to, String title, String content) {
//        try {
//            MimeMessage message = mailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message);
//            helper.setFrom(from);
//            helper.setTo(to);
//            helper.setSubject(title);
//            helper.setText(content, true);
//            mailSender.send(helper.getMimeMessage());
//        } catch (MessagingException e) {
//            logger.error("Send email fail：" + e.getMessage());
//        }
//    }
//
//    public void sendMail(String to, String title, Map<String, String> veriables, String path) {
//        Context context = new Context();
//        for (String name : veriables.keySet()) {
//            context.setVariable(name, veriables.get(name));
//        }
//        String content = templateEngine.process(path, context);
//        sendMail(to, title, content);
//    }
//}
