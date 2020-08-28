package org.abinbev.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Anheuser-Busch InBev: Code challenge",
                description = "This API aims to fulfill the requirements of the Anheuser-Busch InBev Code challenge.",
                contact = @Contact(
                        name = "Gabriel Berberian",
                        url = "https://github.com/GabrielBerberian",
                        email = "gaberberi@gmail.com"
                )),
        servers = @Server(url = "http://localhost:8080")
)
public class SwaggerConfig {
}
