package com.fj.redis.util.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author liubin
 * @DESC 类描述信息
 * @createTime 2020-04-07
 * 访问路径：http://localhost:9899/swagger-ui.html
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    /**
     * config.oauth2.swagger.description = LUNZ# Microservice API Description
     * config.oauth2.swagger.package = com.lunz.microservice.esarwebservice.controller
     * config.oauth2.swagger.title = LUNZ# Microservice API
     * config.oauth2.swagger.version = 1.0
     */

    @Value("${config.oauth2.accessTokenUri}")
    private String accessTokenUri;

    @Value("${config.oauth2.swagger.title}")
    private String swaggerTitle;

    @Value("${config.oauth2.swagger.description}")
    private String swaggerDescription;

    @Value("${config.oauth2.swagger.version}")
    private String swaggerVersion;

    @Value("${config.oauth2.swagger.package}")
    private String swaggerPackage;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage(swaggerPackage)).paths(PathSelectors.any())
                .build().securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(Arrays.asList(securitySchema()))
                .apiInfo(apiInfo());

    }

    private OAuth securitySchema() {

        List<AuthorizationScope> authorizationScopeList = new ArrayList();
        List<GrantType> grantTypes = new ArrayList();
        GrantType passwordCredentialsGrant = new ResourceOwnerPasswordCredentialsGrant(accessTokenUri);
        grantTypes.add(passwordCredentialsGrant);

        return new OAuth("oauth2", authorizationScopeList, grantTypes);
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {

        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[0];
        return Collections.singletonList(new SecurityReference("oauth2", authorizationScopes));
    }

    @Bean
    public SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder().build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(swaggerTitle).version(swaggerVersion).description(swaggerDescription)
                .build();
    }
}
