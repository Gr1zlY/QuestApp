package com.hackaton.questapp.entity.com.hackaton.questapp.runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Sheremeta on 04.04.2015.
 */
@Configuration
@ComponentScan(basePackages = "com.hackaton.questapp.entity.com.hackaton.questapp.springconfig")
@EnableAutoConfiguration
public class Main {


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }
}
