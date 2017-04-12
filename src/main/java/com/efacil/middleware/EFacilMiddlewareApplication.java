package com.efacil.middleware;

import java.sql.Driver;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.efacil.domain.Person;

/**
 * @author Moatez Ben Abdallah
 **/
@Controller
@SpringBootApplication
@Configuration
@ComponentScan(basePackages = "com.efacil")
@EnableAutoConfiguration
@EntityScan("com.efacil.domain")
@EnableJpaRepositories("com.efacil.repository")
public class EFacilMiddlewareApplication {

	@RequestMapping("/")
	@ResponseBody
	String Health() {
		return "done!";
	}

	public static void main(String[] args) {
		SpringApplication.run(EFacilMiddlewareApplication.class, args);
	}
	
	@Bean
    public DataSource dataSource() throws ClassNotFoundException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
         dataSource.setDriverClass((Class<? extends Driver>)Class.forName("com.mysql.jdbc.Driver"));
        dataSource.setUrl("jdbc:mysql://code.casw5citynpe.us-west-2.rds.amazonaws.com:3306/crm_poc");
        dataSource.setUsername("root");
        dataSource.setPassword("DnDDxbm3FA4jrJZ5");
        return  dataSource;
    }
	
	@Bean
	public SessionFactory createSessionFactory() throws ClassNotFoundException {

	    Properties properties = new Properties();
	    properties.put("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
	    properties.put("hibernate.show_sql", "true");
	    properties.put("hibernate.hbm2ddl.auto","update");

	    StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();
	    standardServiceRegistryBuilder.applySettings(properties);
	    standardServiceRegistryBuilder.applySetting(Environment.DATASOURCE,dataSource());

	    MetadataSources metadataSources = new MetadataSources(standardServiceRegistryBuilder.build());
	    metadataSources.addAnnotatedClass(Person.class);
	    return metadataSources.getMetadataBuilder().build().buildSessionFactory();
	}
}
