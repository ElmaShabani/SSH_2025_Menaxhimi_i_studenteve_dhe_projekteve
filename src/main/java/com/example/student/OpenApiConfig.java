package com.example.student;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Student API", version = "1.0", description = "Dokumentimi i API për menaxhimin e studentëve")
)
public class OpenApiConfig {
}
