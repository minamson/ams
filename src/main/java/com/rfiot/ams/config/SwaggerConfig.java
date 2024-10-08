package com.rfiot.ams.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "RFIoT Co,.Ltd",
                        email = "contact@rfiot.co.kr",
                        url = "http://rfiot.co.kr"
                ),
                description = "OpenApi documentation for RF-AMS",
                title = "OpenApi specification - RF-AMS",
                version = "1.0",
                license = @License(
                        name = "RF-AMS License",
                        url = "http://www.rfiot.co.kr/ams/license"
                ),
                termsOfService = "\"http://www.rfiot.co.kr/ams/termsOfService"
        ),
        servers = {
                @Server(description = "Local ENV",url = "http://localhost:8080" ),
                @Server( description = "PROD ENV",url = "https://rfiot.co.kr/ams")
        },
        security = {
                @SecurityRequirement(name = "bearerAuth" )
        }
)


@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)


@Configuration
@Profile({"!prod && swagger"})
public class SwaggerConfig {


}



