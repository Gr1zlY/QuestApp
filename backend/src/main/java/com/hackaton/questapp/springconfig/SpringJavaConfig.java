package com.hackaton.questapp.springconfig;

import com.hackaton.questapp.dao.*;
import com.hackaton.questapp.entity.*;
import com.hackaton.questapp.httpfilter.SimpleCORSFilter;
import com.hackaton.questapp.rest.QuestService;
import com.hackaton.questapp.rest.QuestStatusService;
import com.hackaton.questapp.rest.TaskService;
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

    private static final QuestEntity QUEST_EXAMPLE_ENTITY = new QuestEntity(1L,"ugly quest","The most ugliest quest you will ever see",
                                                                                   null,null,System.currentTimeMillis());

    private static final QuestEntity QUEST_EXAMPLE_SECOND = new QuestEntity(2L,"pretty quest","Not the best, but better than the others",
                                                                                   null, null, System.currentTimeMillis());

    private static final TaskEntity QUEST_TASK_STUB = new TaskEntity(1L,QUEST_EXAMPLE_ENTITY,"even more first","First riddle! SINEP MAET",
                                                                    null, 1, TaskType.INPUT,"TEAM PENIS");

    private static final TaskEntity QUEST_TASK_STUB2 = new TaskEntity(2L,QUEST_EXAMPLE_SECOND,"first","blahblah riddle! FIND ME",
            null, 1, TaskType.GPS,"");



    @Bean
    public QuestService questRestService(){
        QuestService service = new QuestService();
        service.setQuestDao(questDao());
        service.setTeamMemberDao(teamMemberDao());
        //service.setOrganizatorDao(ograni);
        return service;
    }

    @Bean
    public TeamService teamRestService(){
        TeamService teamService = new TeamService();
        teamService.setQuestDao(questDao());
        teamService.setQuestStatusDao(questStatusDao());
        teamService.setTeamDao(teamDao());
        teamService.setTeamMemberDao(teamMemberDao());
        return teamService;
    }

    @Bean
    public TaskService taskRestSerivce(){
        TaskService taskService = new TaskService();
        taskService.setTeamMemberDao(teamMemberDao());
        taskService.setQuestStatusDao(questStatusDao());
        taskService.setTaskDao(taskDao());
        return taskService;
    }

    @Bean
    public QuestStatusService questStatusService(){
        QuestStatusService questStatusService = new QuestStatusService();
        questStatusService.setTeamMemberDao(teamMemberDao());
        questStatusService.setQuestStatusDao(questStatusDao());
        return questStatusService;
    }

    @Bean
    public QuestDao questDao(){
        QuestDao dao = new QuestDao();
        DBStubStorage storage = dbStubStorage();
        storage.insert(QUEST_EXAMPLE_ENTITY.getQuestId(),QUEST_EXAMPLE_ENTITY);
        storage.insert(QUEST_EXAMPLE_SECOND.getQuestId(),QUEST_EXAMPLE_SECOND);
        dao.setStorage(storage);
        return dao;
    }

    @Bean
    public QuestStatusDao questStatusDao(){
        QuestStatusDao questStatusDao = new QuestStatusDao();
        questStatusDao.setStorage(dbStubStorage());
        return questStatusDao;
    }

    @Bean
    public TaskDao taskDao(){
        TaskDao taskDao = new TaskDao();
        DBStubStorage taskStorage = dbStubStorage();
        taskStorage.insert(QUEST_TASK_STUB.getTaskId(),QUEST_TASK_STUB);
        taskStorage.insert(QUEST_TASK_STUB2.getTaskId(),QUEST_TASK_STUB2);
        taskDao.setStorage(taskStorage);
        return taskDao;
    }

    @Bean
    public TeamDao teamDao(){
        TeamDao teamDao = new TeamDao();
        teamDao.setStorage(dbStubStorage());
        return teamDao;
    }

    @Bean
    public TeamMemberDao teamMemberDao(){
        TeamMemberDao dao = new TeamMemberDao();
        dao.setStorage(dbStubStorage());
        return dao;
    }

    @Bean
    @Scope(value = "prototype")
    public DBStubStorage dbStubStorage(){
        return new DBStubStorage();
    }

    @Bean
    public SimpleCORSFilter corsFilter(){
        SimpleCORSFilter filter = new SimpleCORSFilter();
        return filter;
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
