package com.bigScreen.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@EnableScheduling
public class BigScreentApplication {
	
	public static void main(String[] args) {
        SpringApplication.run(BigScreentApplication.class, args);
    }
        
     /** 
      * 跨域过滤器 
      * @return CorsFilter 跨域过滤器对象
      */  
     @Bean  
     public CorsFilter corsFilter() {  
         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();  
         source.registerCorsConfiguration("/**", buildConfig()); // 4  
         return new CorsFilter(source);  
     } 
 
     private CorsConfiguration buildConfig() {  
         CorsConfiguration corsConfiguration = new CorsConfiguration();  
         corsConfiguration.addAllowedOrigin("*");  
         corsConfiguration.addAllowedHeader("*");  
         corsConfiguration.addAllowedMethod("*");  
         return corsConfiguration;  
     }  
}
