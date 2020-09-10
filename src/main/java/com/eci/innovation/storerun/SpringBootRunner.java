package com.eci.innovation.storerun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;

import io.swagger.models.Contact;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;

import springfox.documentation.spring.web.plugins.Docket;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
public class SpringBootRunner {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootRunner.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                                                      .apis(RequestHandlerSelectors.basePackage( "com.eci.innovation.storerun.controller" ))
                                                      .paths(PathSelectors.any())
                                                      .build()
                                                      .apiInfo(this.apiInfo());
    }
    
    
    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Store Runners API")
            .description("Store Runners Demo API for ECI Innovation Day")
            .license("Apache 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
            .termsOfServiceUrl("")
            .version("1.0.0")
            .contact(new springfox.documentation.service.Contact("Store Runners", "", "storerunners@ecisolutions.com"))
            .build();
    }
}
