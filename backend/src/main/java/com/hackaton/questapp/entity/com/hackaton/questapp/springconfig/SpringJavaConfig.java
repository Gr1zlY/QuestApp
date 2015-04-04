package com.hackaton.questapp.entity.com.hackaton.questapp.springconfig;

import com.hackaton.questapp.entity.com.hackaton.questapp.rest.QuestService;
import com.hackaton.questapp.entity.com.hackaton.questapp.rest.TeamService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
