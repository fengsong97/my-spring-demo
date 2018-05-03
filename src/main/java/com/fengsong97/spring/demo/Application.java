package com.fengsong97.spring.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

/**
 * 创建人 fengsong
 * 创建时间 2018/04/25 15:17
 **/
@SpringBootApplication
@EnableJpaAuditing
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory() {

        return new HibernateJpaSessionFactoryBean();
    }


}
