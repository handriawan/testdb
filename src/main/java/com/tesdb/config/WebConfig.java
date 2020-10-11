package com.tesdb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addResourceHandler("/dist/css/**").addResourceLocations("classpath:/static/dist/css/");
		registry.addResourceHandler("/dist/js/**").addResourceLocations("classpath:/static/dist/js/");
		registry.addResourceHandler("/dist/img/**").addResourceLocations("classpath:/static/dist/img/");
		registry.addResourceHandler("/plugins/**").addResourceLocations("classpath:/static/plugins/");
		
	}

   @Bean
   public BCryptPasswordEncoder passwordEncoder() {
	   BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();
	   return bCryptPasswordEncoder;
   }
	
}

