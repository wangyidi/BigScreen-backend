package com.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Hello world!
 *
 */
@EnableScheduling
@SpringBootApplication
public class WeatherApplication 
{
    public static void main( String[] args ) {
    	SpringApplication.run(WeatherApplication.class);
    }
}
