/*
 * SPDX-FileCopyrightText: 2025 Swiss Confederation
 *
 * SPDX-License-Identifier: MIT
 */

package ch.admin.bit.eid.trust_registry.authoring_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import lombok.AllArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@AllArgsConstructor
@Configuration
public class OpenApiConfig {
    private final BuildProperties buildProperties;

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI().info(new io.swagger.v3.oas.models.info.Info()
                        .title("Trust Registry Authoring API")
                        .description("APIs for writing into SWIYU Trust Registry")
                        .version(buildProperties.getVersion())
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .name("EID - Team Tergum")
                                .email("eid@bit.admin.ch")
                                .url("https://confluence.bit.admin.ch/display/EIDTEAM")
                        )
                ).externalDocs(new io.swagger.v3.oas.models.ExternalDocumentation()
                        .url("https://confluence.bit.admin.ch/display/EIDTGM/E-ID+-+Team+Tergum+-+Startseite")
                )
                .addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement()
                        .addList("bearerAuth")
                )
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("bearerAuth", new io.swagger.v3.oas.models.security.SecurityScheme()
                                .name("bearerAuth")
                                .description("JWT authentication")
                                .scheme("bearer")
                                .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
                                .bearerFormat("JWT")
                                .in(io.swagger.v3.oas.models.security.SecurityScheme.In.HEADER)
                        )
                );

    }

    @Bean
    GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("API")
                .pathsToMatch("/**")
                .build();
    }
}
