package com.hackaton.questapp.entity.com.hackaton.questapp.rest;

import com.hackaton.questapp.entity.OrganizatorUser;
import com.hackaton.questapp.entity.QuestEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Sheremeta on 04.04.2015.
 */
@RestController
public class QuestService {

    @RequestMapping(value = "/getQuests", headers = "Accept=application/json")
    public List<QuestEntity> getAllQuests(){
        OrganizatorUser stubOrganizator = new OrganizatorUser(1L,"AwesomeDude","pleasehideme");
        QuestEntity firstStubEntity = new QuestEntity(1L,"ugly quest","The most ugliest quest you will ever see",
                null,stubOrganizator,System.currentTimeMillis());
        QuestEntity secondStubEntity = new QuestEntity(2L,"pretty quest","Not the best, but better than the others",
                null, stubOrganizator, System.currentTimeMillis());
        return Arrays.asList(firstStubEntity,secondStubEntity);
    }


}
