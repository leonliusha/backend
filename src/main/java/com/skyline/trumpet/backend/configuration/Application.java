package com.skyline.trumpet.backend.configuration;

import java.util.Date;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.skyline.trumpet.backend.model.Broadcast;
import com.skyline.trumpet.backend.persistence.BroadcastMapper;
import com.skyline.trumpet.backend.service.BroadcastService;

@SpringBootApplication
@ComponentScan(basePackages = "com.skyline.trumpet.backend")
public class Application{

    public static void main(String[] args) {
           SpringApplication.run(Application.class, args);
    }
}
