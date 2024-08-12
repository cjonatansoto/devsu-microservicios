package com.devsu.movimientos.infrastructure.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Ejercicio Devsu",
                version = "1.0",
                description = "Desarrollo de una aplicación que exponga una API RESTfull de movimientos de ctas",
                contact = @Contact(
                        name = "Jonatan Soto Imio",
                        email = "cjonatansoto@gmail.com",
                        url = "https://github.com/cjonatansoto"
                ),
                license = @License(
                        name = "Licencia MIT",
                        url = "https://opensource.org/licenses/MIT"
                )
        )
)
public class OpenAPIConfig {
}
