package com.me.dcis_4;

import java.util.Properties;

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

import jakarta.persistence.EntityManagerFactory;

/**
 * Конфигурационный класс Spring для настройки приложения.
 */
/**
 * Разрешает репозитории JPA для пакета "com.me.dcis_4".
 * Эта аннотация используется для разрешения репозиториев JPA в приложении.
 * Она сканирует репозитории в указанном пакете и его подпакетах.
 */
@EnableJpaRepositories("com.me.dcis_4")

/**
 * Разрешает управление транзакциями для приложения.
 * Эта аннотация используется для разрешения управления транзакциями в приложении.
 * Она позволяет Spring управлять транзакциями для приложения.
 */
@EnableTransactionManagement

/**
 * Маркирует этот класс как класс конфигурации для приложения.
 * Эта аннотация используется для маркирования, что этот класс является классом конфигурации для приложения.
 * Она используется для определения бинов и конфигурации приложения.
 */
@Configuration

/**
 * Разрешает сканирование компонентов для пакета "com.me.*".
 * Эта аннотация используется для разрешения сканирования компонентов в приложении.
 * Она сканирует компоненты (например, бины, сервисы и репозитории) в указанном пакете и его подпакетах.
 */
@ComponentScan("com.me.*")

/**
 * Указывает расположение файла свойств приложения.
 * Эта аннотация используется для указания расположения файла свойств приложения.
 * В этом случае файл свойств находится в класспате и называется "application.properties".
 */
@PropertySource("classpath:application.properties")
public class SpringConfig {
    /**
     * Класс Environment в Spring Framework является частью инфраструктуры конфигурации и предоставляет доступ к переменным среды, 
     * свойствам приложения и другим настройкам.
     */
    @Autowired
    private Environment env;
    
    /**
     * Создаем бин для источника данных.
     * 
     * @return источник данных
     */
    @Bean
    DataSource dataSource() {
        // Создаем источник данных
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        
        // Устанавливаем свойства источника данных из application.properties
        dataSource.setDriverClassName(env.getProperty("dataSource.driverClassName"));
        dataSource.setUrl(env.getProperty("dataSource.url"));
        dataSource.setUsername(env.getProperty("dataSource.username"));
        dataSource.setPassword(env.getProperty("dataSource.password"));
        
        return dataSource;
    }
    
    /**
     * Создаем бин для фабрики EntityManager.
     * 
     * @return фабрика EntityManager
     */
    @Bean
    EntityManagerFactory entityManagerFactory() {
        // Создаем адаптер для Hibernate
        HibernateJpaVendorAdapter hibernateAdapter = new HibernateJpaVendorAdapter();
        hibernateAdapter.setGenerateDdl(true);
        
        // Создаем фабрику EntityManager
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(hibernateAdapter);
        factory.setPackagesToScan("com.me.dcis_4.entity");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();
        
        return factory.getObject();
    }
    
    /**
     * Создаем бин для менеджера транзакций.
     * 
     * @return менеджер транзакций
     */
    @Bean
    PlatformTransactionManager transactionManager() {
        // Создаем менеджер транзакций для JPA
        JpaTransactionManager tnManager = new JpaTransactionManager();
        tnManager.setEntityManagerFactory(entityManagerFactory());
        
        return tnManager;
    }
}