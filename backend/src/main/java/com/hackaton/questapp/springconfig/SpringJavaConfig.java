package com.hackaton.questapp.springconfig;

import com.hackaton.questapp.rest.QuestService;
import com.hackaton.questapp.rest.TeamService;
import com.hackaton.questapp.storage.DBStubStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Properties;

/**
 * Created by Sheremeta on 04.04.2015.
 */
@Configuration
public class SpringJavaConfig {

    @Bean
    public QuestService questRestService(){
        QuestService service = new QuestService();
        return service;
    }

    @Bean
    public TeamService teamRestService(){
        TeamService teamService = new TeamService();
        return teamService;
    }

    @Bean
    @Scope(value = "prototype")
    public DBStubStorage dbStubStorage(){
        return new DBStubStorage();
    }



    /*@Bean(destroyMethod = "close")
    public BasicDataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
       // dataSource.set
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/QuestDB");
        dataSource.setUsername("quest");
        dataSource.setPassword("quest");
        return dataSource;
    }

    @Bean
    public AnnotationSessionFactoryBean hibernateFactoryBean(){
        AnnotationSessionFactoryBean bean = new AnnotationSessionFactoryBean();
        bean.setDataSource(dataSource());
        bean.setAnnotatedPackages("com.hackaton.questapp.entity");
        Properties hibernateProperties = bean.getHibernateProperties();
        hibernateProperties.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
        hibernateProperties.setProperty("hibernate.current_session_context_class","thread");
        return bean;
    }*/
}
