package com.hmvss.auth.config.properties;


import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Configuration
@Component
@ConfigurationProperties(prefix = "server")
public class ServerProperties {

    String serverUri;
    String port;
    String tokenUri;
    private Servlet servlet;


    @Getter
    @Setter
    public static class Servlet {
        private String contextPath;
    }

    public String getOauthRq() {
        return this.serverUri + ":" + this.port + this.servlet.getContextPath() + this.tokenUri + "?";
    }

}
