package org.aviation.control.queue.domain.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(
        basePackages = "org.aviation.control.queue.domain.repository",
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager"
)
@EnableTransactionManagement
public class DomainConfig {

    @Value("${db_hibernate_dialect:org.hibernate.dialect.H2Dialect}")
    private String hibernateDialect;
    
    @Value("${db_hibernate_hbm2ddl_auto:update}")
    private String hibernateHbm2ddlAuto;
    
    @Value("${db_hibernate_showSql:false}")
    private boolean hibernateShowSql;
    
    @Value("${db_hibernate_formatSql:false}")
    private boolean hibernateFormatSql;
    
    @Value("${db_hibernate_non_contextual_creation:true}")
    private boolean hibernateNonContextualCreation;
    
	@Bean
    public EntityManagerFactory entityManagerFactory(@Autowired DataSource dataSource) {

        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(hibernateShowSql);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setPackagesToScan("org.aviation.control.queue.domain.entity");
        factory.setPersistenceUnitName("Persistence.AirCraftControl");
        factory.setDataSource(dataSource);
        factory.setJpaProperties(this.jpaProperties());
        factory.setJpaVendorAdapter(adapter);
        factory.afterPropertiesSet();
        return factory.getObject();
    }
    
    private Properties jpaProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
        properties.put("hibernate.dialect", hibernateDialect);
        properties.put("hibernate.show_sql", hibernateShowSql);
        properties.put("hibernate.format_sql", hibernateFormatSql);
        properties.put("hibernate.jdbc.lob.non_contextual_creation", hibernateNonContextualCreation);
        
        return properties;
    }

	@Bean
    public PlatformTransactionManager transactionManager(@Autowired EntityManagerFactory entityManagerFactory) {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
}
