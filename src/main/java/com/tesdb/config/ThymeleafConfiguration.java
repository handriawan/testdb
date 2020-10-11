package com.tesdb.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

//@Configuration
//@EnableWebMvc

public class ThymeleafConfiguration {
    @Bean
    public SpringTemplateEngine templateEngine() {
    	org.thymeleaf.spring5.SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    	templateEngine.setTemplateResolver(thymeleafTemplateResolver());
    	return templateEngine;
    }
	
	
	@Bean
	public SpringResourceTemplateResolver thymeleafTemplateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTMLS");
		templateResolver.setCacheable(false);
		return templateResolver;
		
	}
    
    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {
    	ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
    	viewResolver.setCharacterEncoding("UTF-8");
    	viewResolver.setTemplateEngine(templateEngine());
    	return viewResolver;
    }
}

