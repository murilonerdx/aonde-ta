package com.murilonerdx.aondeta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

@Configuration
public class SwaggerConfig {
    private static final String BASE_PACKAGE = "com.murilonerdx.aondeta.resources";
    private static final String API_TITLE = "Aonde Tá";
    private static final String API_DESCRIPTION = "Projeto tem como principal funcionalidade, ajudar a saber quais lugares seguros tem em São Paulo, e aonde" +
            " está tendo maior ocorrencias de furtos, violencia ou mortes por conta de assaltos ou coisas relacionadas, dando um indicativo de prioridade como baixo, alto, medio e normal";
    private static final String API_VERSION = "1.0.0";
    private static final String CONTACT_NAME = "Desenvolvido por Murilo P.S";
    private static final String CONTACT_GITHUB = "https://github.com/murilonerdx";
    private static final String CONTACT_EMAIL = "mu-silva@outlook.com";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(basePackage(BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(buildApiInfo());
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title(API_TITLE)
                .description(API_DESCRIPTION)
                .version(API_VERSION)
                .contact(new Contact(CONTACT_NAME, CONTACT_GITHUB, CONTACT_EMAIL))
                .build();
    }
}
