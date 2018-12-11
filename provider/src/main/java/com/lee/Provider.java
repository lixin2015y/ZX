package com.lee;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

/**
 * @author lee
 */
@MapperScan(basePackages = "com.lee.dao")
@ImportResource("classpath:dubbo/provider.xml")
@SpringBootApplication
public class Provider extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Provider.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Provider.class, args);
    }
}
