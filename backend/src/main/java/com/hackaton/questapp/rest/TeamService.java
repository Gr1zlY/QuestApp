package com.hackaton.questapp.rest;

import com.hackaton.questapp.dao.QuestDao;
import com.hackaton.questapp.dao.QuestStatusDao;
import com.hackaton.questapp.dao.TeamDao;
import com.hackaton.questapp.dao.TeamMemberDao;
import com.hackaton.questapp.entity.*;
import org.json.JSONException;
import org.json.JSONObject;
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

    private QuestDao questDao;

    private QuestStatusDao questStatusDao;

    private TeamMemberDao teamMemberDao;

    @RequestMapping(value = "/getTeams", headers = "Accept=application/json")
    public List<TeamEntity> getTeamsByQuest(@RequestParam Long questId){
       return teamDao.getTeamsByQuestId(questId);
    }

    @RequestMapping(value = "/createTeam", headers = "Accept=application/json")
    public Status createTeam(@RequestParam String name,@RequestParam Long questId,
                             @RequestParam String deviceId) throws JSONException {
        if(teamDao.teamWithSuchNameExist(name)) return new Status("ERROR");
        QuestEntity currentQuest = questDao.getById(questId);
        TeamEntity teamEntity = new TeamEntity(System.currentTimeMillis(),name,currentQuest);
        teamDao.insert(teamEntity.getTeamId(),teamEntity);
        TeamMemberEntity teamMemberEntity = new TeamMemberEntity(System.currentTimeMillis(),teamEntity,deviceId);
        teamMemberDao.insert(teamMemberEntity.getMemberId(),teamMemberEntity);
        questStatusDao.insert(System.currentTimeMillis(),new QuestStatusEntity(teamEntity,0L));
        return new Status("OK");
    }

    @RequestMapping(value = "/joinTeam",  headers = "Accept=application/json")
    public Status joinTeam(@RequestParam String deviceId, @RequestParam Long teamId){
        TeamEntity team = teamDao.getById(teamId);
        TeamMemberEntity teamMemberEntity = new TeamMemberEntity(System.currentTimeMillis(),team,deviceId);
        teamMemberDao.insert(teamMemberEntity.getMemberId(),teamMemberEntity);
        System.out.println(teamMemberEntity);
        return new Status("OK");
    }

    public void setTeamDao(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    public void setQuestDao(QuestDao questDao) {
        this.questDao = questDao;
    }

    public void setQuestStatusDao(QuestStatusDao questStatusDao) {
        this.questStatusDao = questStatusDao;
    }

    public void setTeamMemberDao(TeamMemberDao teamMemberDao) {
        this.teamMemberDao = teamMemberDao;
    }
}
