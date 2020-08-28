package org.abinbev.event;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

/**
 * Launches browser on application ready event
 */
@Component
public class ApplicationReadyEventListener {

    private static final String PREFIX = "http://localhost";
    private static final String SWAGGER3 = "swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config";
    private static final String OAUTH2 = "oauth2";
    private static final String HEALTH = "actuator/health";
    private static final String GIT = "https://github.com/GabrielBerberian/ABInbevCodeChalange";

    private final Environment environment;

    public ApplicationReadyEventListener(Environment environment) {
        this.environment = environment;
    }

    @EventListener({ApplicationReadyEvent.class})
    void applicationReadyEvent() {

        System.out.println("Application started ... launching browser now");
        List<String> suffixes = Arrays.asList(OAUTH2, HEALTH, SWAGGER3);
        suffixes.forEach(suffix -> {
            try {
                browse(getBaseUrl(suffix));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        try {
            browse(GIT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getBaseUrl(String suffix) {
        String port = environment.getProperty("server.port");
        return PREFIX + ":" + port + "/" + suffix;
    }

    private void browse(String url) {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
