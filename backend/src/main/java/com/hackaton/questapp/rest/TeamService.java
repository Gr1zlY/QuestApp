package com.hackaton.questapp.rest;

import com.hackaton.questapp.dao.TeamDao;
import com.hackaton.questapp.entity.TeamEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Sheremeta on 04.04.2015.
 */

@RestController
public class TeamService {

    private TeamDao teamDao;

    @RequestMapping(value = "/getTeams", headers = "Accept=application/json")
    public List<TeamEntity> getTeamsByQuest(@RequestParam Long questId){
       return teamDao.getTeamsByQuestId(questId);
    }

    @RequestMapping(value = "/getTeams", headers = "Accept=application/json")
    public String createTeam(@RequestParam String name,@RequestParam Long questId){
        
    }

    public void setTeamDao(TeamDao teamDao) {
        this.teamDao = teamDao;
    }
}
