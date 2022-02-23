package org.bulletin_board.config;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@PropertySource("classpath:email.properties")
public class EmailConfig implements EnvironmentAware {
    private Environment environment;

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();

        String port = environment.getProperty("sender.port");
        String host = environment.getProperty("sender.host");
        String username = environment.getProperty("sender.username");
        String password = environment.getProperty("sender.password");

        String transportProtocol = environment.getProperty("transport.protocol");
        String smtpAuth = environment.getProperty("smtp.auth");
        String smtpSSLTrust = environment.getProperty("smtp.ssl.trust");
        String smtpStartTLSEnable = environment.getProperty("smtp.starttls.enable");

        sender.setPort(Integer.parseInt(port));
        sender.setHost(host);
        sender.setUsername(username);
        sender.setPassword(password);

        Properties properties = sender.getJavaMailProperties();
        properties.put("mail.transport.protocol", transportProtocol);
        properties.put("mail.smtp.auth", smtpAuth);
        properties.put("mail.smtp.ssl.trust", smtpSSLTrust);
        properties.put("mail.smtp.starttls.enable", smtpStartTLSEnable);

        return sender;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
