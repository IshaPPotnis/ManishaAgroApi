package com.activexsolutions.manishaagro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan (basePackages = {"com.activexsolutions.manishaagro"})
@EnableJpaRepositories ("com.activexsolutions.manishaagro.repository")
@EntityScan ("com.activexsolutions.manishaagro.model")
public class ManishaAgroApiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ManishaAgroApiApplication.class, args);
    }

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.activexsolutions.manishaagro")).build();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ManishaAgroApiApplication.class);
    }
}
