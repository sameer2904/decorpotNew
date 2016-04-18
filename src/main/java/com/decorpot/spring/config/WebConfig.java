package com.decorpot.spring.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(basePackages = "com.decorpot.datasource.repository", entityManagerFactoryRef = "decorpotEntityManager", transactionManagerRef = "decorpotTransactionManager")
@ComponentScan(basePackages = { "com.decorpot.spring.config",
		"com.decorpot.spring.controller", "com.decorpot.*" })
public class WebConfig extends WebMvcConfigurerAdapter {

	private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
	private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
	private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
	private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";

	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";

	@Resource
	private Environment env;

	@Bean(name = "decorpotDataSource")
	@Primary
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(env
				.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
		dataSource.setUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
		dataSource.setUsername(env
				.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
		dataSource.setPassword(env
				.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));

		return dataSource;
	}

	@Bean(name = "decorpotEntityManager")
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(false);
		entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
		entityManagerFactoryBean
				.setPackagesToScan("com.decorpot.datasource.models");

		entityManagerFactoryBean.setJpaProperties(hibProperties());

		return entityManagerFactoryBean;
	}

	private Properties hibProperties() {
		Properties properties = new Properties();
		properties.put(PROPERTY_NAME_HIBERNATE_DIALECT,
				env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
		properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL,
				env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
		return properties;
	}

	@Bean(name = "decorpotTransactionManager")
	@Primary
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory()
				.getObject());
		return transactionManager;
	}

	@Bean
	public InternalResourceViewResolver jspViewResolver() {
		System.out.println("WebConfig:InternalResourceViewResolver");
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setPrefix("/WEB-INF/views/");
		bean.setSuffix(".jsp");
		return bean;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		System.out.println("WebConfig:addResourceHandlers");
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("/resources/")
				.addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/views/**").addResourceLocations(
				"/views/**");
	}

	@Bean
	public JavaMailSender getMailSender() {
		
		JavaMailSenderImpl mailSender =  new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(25);
		mailSender.setUsername("sameersaurav2904@gmail.com");
		mailSender.setPassword("*******");
		Properties mailProperties = new Properties();
		mailProperties.put("mail.smtp.auth", "smtp");
		mailProperties.put("mail.smtp.auth", true);
		mailProperties.put("mail.smtp.starttls.enable", true);
		mailProperties.put("mail.debug",true);
		mailSender.setJavaMailProperties(mailProperties);
		return mailSender;
	}
	
	@Bean
    public SimpleMailMessage simpleMailMessage() {
       SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
       simpleMailMessage.setFrom("sameersaurav2904@gmail.com");
       simpleMailMessage.setSubject("lets do it");
       simpleMailMessage.setTo("sameersaurav2904@gmail.com");
       return simpleMailMessage;
    }
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver commonsMultipartResolver(){
		CommonsMultipartResolver factory = new CommonsMultipartResolver();
        factory.setMaxUploadSize(4000000);
        return factory;
    }

}
