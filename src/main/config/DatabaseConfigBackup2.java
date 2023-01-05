package main.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan("main")
@PropertySource("/WEB-INF/resources/database.properties")
@EnableTransactionManagement
@EnableJpaRepositories("main.repository")
public class DatabaseConfigBackup2 {

	@Autowired
	private Environment environment;

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getProperty("jdbc.driver"));
		dataSource.setUrl(environment.getProperty("jdbc.url"));
		dataSource.setUsername(environment.getProperty("jdbc.username"));
		dataSource.setPassword(environment.getProperty("jdbc.password"));
		return dataSource;
	}


	
	  @Bean
	  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

	    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	    vendorAdapter.setGenerateDdl(true);
	    vendorAdapter.setShowSql(true);

	    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
	    factory.setJpaVendorAdapter(vendorAdapter);
	    factory.setPackagesToScan(new String[] { "main" });
	    factory.setDataSource(getDataSource());
	    factory.setJpaProperties(jpaProperties());
	    return factory;
	  }
	  
	  @Bean
	  public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

	    JpaTransactionManager txManager = new JpaTransactionManager();
	    txManager.setEntityManagerFactory(entityManagerFactory);
	    return txManager;
	  }
	  
//		@Bean(name = "entityManagerFactory")
//		public LocalSessionFactoryBean sessionFactoryBean() {
//			LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
//			localSessionFactoryBean.setDataSource(getDataSource());
//			localSessionFactoryBean.setPackagesToScan(new String[] { "main" });
//			localSessionFactoryBean.setHibernateProperties(hibernateProperties());
//			return localSessionFactoryBean;
//		}

	  @Bean
	  public Properties jpaProperties() {

	  	Properties properties = new Properties();
	  	properties.setProperty("spring.jpa.properties.hibernate.show_sql", "true");
	  	return properties;
	  }
	  
//	  private final Properties hibernateProperties() {
//		Properties properties = new Properties();
//		properties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
//		properties.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
//		properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
//		return properties;
//	}

//	@Bean(name = "transactionManager")
//	public HibernateTransactionManager getTransactionManager() {
//		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//		transactionManager.setSessionFactory(sessionFactoryBean().getObject());
//		return transactionManager;
//	}
}
