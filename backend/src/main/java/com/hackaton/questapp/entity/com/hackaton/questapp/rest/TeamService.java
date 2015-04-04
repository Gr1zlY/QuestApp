package com.hackaton.questapp.entity.com.hackaton.questapp.rest;

import com.hackaton.questapp.entity.TeamEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Sheremeta on 04.04.2015.
 */

@RestController
public class TeamService {

    @RequestMapping(value = "/getTeams", headers = "Accept=application/json")
    public List<TeamEntity> getTeamsByQuest(@RequestParam Long questId){
        System.out.println(questId);
        return Arrays.asList(new TeamEntity(1L,"awesomeTeam",null),new TeamEntity(2L,"lessAwesomeTeam",null),
                             new TeamEntity(3L,"SINEPMEAT",null));
    }

}
