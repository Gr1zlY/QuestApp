package com.hackaton.questapp.rest;

import com.hackaton.questapp.dao.QuestDao;
import com.hackaton.questapp.entity.OrganizatorUser;
import com.hackaton.questapp.entity.QuestEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Sheremeta on 04.04.2015.
 */
@RestController
public class QuestService {

    private QuestDao questDao;

    @RequestMapping(value = "/getQuests", headers = "Accept=application/json")
    public List<QuestEntity> getAllQuests(){
        return questDao.getAll();
    }

    @RequestMapping(value = "/getQuestsByUserId", headers = "Accept=application/json")
    public List<QuestEntity> getQuestsByUserId(@RequestParam Long userId){
       return questDao.getQuestsByUserId(userId);
    }

    public void setQuestDao(QuestDao questDao) {
        this.questDao = questDao;
    }
}
