package com.hackaton.questapp.rest;

import com.hackaton.questapp.dao.QuestStatusDao;
import com.hackaton.questapp.dao.TeamDao;
import com.hackaton.questapp.dao.TeamMemberDao;
import com.hackaton.questapp.entity.QuestStatusEntity;
import com.hackaton.questapp.entity.TeamEntity;
import com.hackaton.questapp.entity.TeamMemberEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Sheremeta on 04.04.2015.
 */

@RestController
public class QuestStatusService {

    private QuestStatusDao questStatusDao;

    private TeamMemberDao teamMemberDao;

    @RequestMapping(value = "/getQuestStatus", headers = "Accept=application/json")
    public QuestStatusEntity getQuestStatus(@RequestParam String deviceId){
        TeamMemberEntity teamMember = teamMemberDao.getTeamMemberById(deviceId);
        if(teamMember == null) return null;
        TeamEntity team = teamMember.getTeam();
        return questStatusDao.getByTeam(team);
    }

    public void setQuestStatusDao(QuestStatusDao questStatusDao) {
        this.questStatusDao = questStatusDao;
    }

    public void setTeamMemberDao(TeamMemberDao teamMemberDao) {
        this.teamMemberDao = teamMemberDao;
    }
}
