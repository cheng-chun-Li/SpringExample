package lee.Spring;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Contains database configurations.
 */
@Configuration
@Configurable
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "lee.*" })
public class DatabaseConfig {

	// ------------------------
	  // PRIVATE FIELDS
	  // ------------------------
	  
	  @Autowired
	  private Environment env;
	  
	  @Autowired
      JpaVendorAdapter jpaVendorAdapter;

  // ------------------------
  // PUBLIC METHODS
  // ------------------------

  /**
   * DataSource definition for database connection. Settings are read from
   * the application.properties file (using the env object).
   */
  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(env.getProperty("db.driver"));
    dataSource.setUrl(env.getProperty("db.url"));
    dataSource.setUsername(env.getProperty("db.username"));
    dataSource.setPassword(env.getProperty("db.password"));
    System.out.println("DataSource complete : " +dataSource.getUsername() + " " + dataSource.getPassword());
    return dataSource;
  }

  @Bean
  public JpaVendorAdapter jpaVendorAdapter() {
      HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
      jpaVendorAdapter.setGenerateDdl(true);
      jpaVendorAdapter.setShowSql(true);
      jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
      System.out.println("JpaVendorAdapter complete");
      return jpaVendorAdapter;
  }  

  @Bean (name = "myEntityManager")
  public EntityManager entityManager() {
	  System.out.println("entityManager complete");
      return entityManagerFactory().createEntityManager();
  }

  @Bean (name = "myEntityManagerFactory")
  public EntityManagerFactory entityManagerFactory() {
      LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
      lef.setDataSource(dataSource());
      lef.setJpaVendorAdapter(jpaVendorAdapter);
      lef.setPackagesToScan("lee.*");
      lef.afterPropertiesSet();
      System.out.println("EntityManagerFactory complete");
      return lef.getObject();
  }

  @Bean
  public PlatformTransactionManager transactionManager() {
      return new JpaTransactionManager(entityManagerFactory());
  }
}