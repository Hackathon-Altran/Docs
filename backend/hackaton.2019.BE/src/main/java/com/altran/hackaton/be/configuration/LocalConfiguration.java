package com.altran.hackaton.be.configuration;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

public class LocalConfiguration {

	// Fetch environment parameters
	@Autowired
	private Environment env;

	// Fetch application beans
	@Autowired
	private ApplicationContext context;

	@Bean
	@Profile("default")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Value("${redis.port}") Integer redisPort)
			throws IOException {

		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setDataSource((DataSource) context.getBean("localDataSource"));
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabase(Database.H2);
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setShowSql(true);
		entityManager.setJpaVendorAdapter(vendorAdapter);
		entityManager.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
		entityManager.setPackagesToScan("com.altran.hackaton.be.model");
		entityManager.setJpaProperties(getLocalProperties());
		return entityManager;
	}

	private Properties getLocalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
		properties.setProperty("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
		properties.setProperty("hibernate.format_sql", env.getProperty("spring.jpa.properties.hibernate.format_sql"));
		properties.setProperty("hibernate.show_sql",
				env.getRequiredProperty("spring.jpa.properties.hibernate.show_sql"));
		properties.setProperty("hibernate.generate_statistics",
				env.getRequiredProperty("spring.jpa.properties.hibernate.generate_statistics"));

		return properties;
	}
}
