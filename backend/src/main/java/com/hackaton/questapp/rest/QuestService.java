package com.hackaton.questapp.rest;

import com.hackaton.questapp.dao.OrganizatorDao;
import com.hackaton.questapp.dao.QuestDao;
import com.hackaton.questapp.dao.TeamMemberDao;
import com.hackaton.questapp.entity.QuestEntity;
import com.hackaton.questapp.entity.Status;
import com.hackaton.questapp.entity.TeamEntity;
import com.hackaton.questapp.entity.TeamMemberEntity;
import org.apache.xmlbeans.impl.util.Base64;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Sheremeta on 04.04.2015.
 */
@RestController
public class QuestService {

    private QuestDao questDao;

    private OrganizatorDao organizatorDao;

    private TeamMemberDao teamMemberDao;

    @RequestMapping(value = "/getQuests", headers = "Accept=application/json")
    public List<QuestEntity> getAllQuests(){
        return questDao.getAll();
    }

    @RequestMapping(value = "/getQuestsByUserId", headers = "Accept=application/json")
    public List<QuestEntity> getQuestsByUserId(@RequestParam Long userId){
       return questDao.getQuestsByUserId(userId);
    }

    /* AWFULL METHOD */
    @RequestMapping(value = "/getQuestByDeviceId", headers = "Accept=application/json")
    public QuestEntity getQuestsByDeviceId(@RequestParam String deviceId){
        TeamMemberEntity teamMember = teamMemberDao.getTeamMemberById(deviceId);
        if(teamMember == null) return null;
        TeamEntity team = teamMember.getTeam();
        return team.getQuest();
    }

    @RequestMapping(value = "/addNewQuest", headers = "Accept=application/json", method = RequestMethod.POST)
    public void insertNewQuest(@RequestParam String name, @RequestParam String description,@RequestParam String photo,
                               @RequestParam Long userId, @RequestParam Long time){
        QuestEntity questEntity = new QuestEntity(System.currentTimeMillis(),name,description,
                Base64.decode(photo.getBytes()), organizatorDao.getById(userId), time);
        questDao.insert(questEntity.getQuestId(),questEntity);

    }

     @RequestMapping(value="/removeQuestById", headers = "Accept=application/json", method = RequestMethod.POST)
     public Status removeQuestByQuestId(@RequestParam Long questId){
        questDao.removeById(questId);
        return new Status("OK");
     }

    @RequestMapping(value="/updateQuest", headers = "Accept=application/json", method = RequestMethod.POST)
    public Status updateQuest(@RequestParam Long questId,@RequestParam String name,
                              @RequestParam String description,@RequestParam String photo,
                              @RequestParam Long userId, @RequestParam Long time){
        QuestEntity quest = questDao.getById(questId);
        quest.setName(name);
        quest.setDescription(description);
        quest.setPhoto(Base64.decode(photo.getBytes()));
        quest.setTime(time);
        return new Status("OK");
    }


    public void setQuestDao(QuestDao questDao) {
        this.questDao = questDao;
    }

    public void setOrganizatorDao(OrganizatorDao organizatorDao) {
        this.organizatorDao = organizatorDao;
    }

    public void setTeamMemberDao(TeamMemberDao teamMemberDao) {
        this.teamMemberDao = teamMemberDao;
    }
}
