package com.hackaton.questapp.dao;

import com.google.common.collect.Lists;
import com.hackaton.questapp.entity.QuestEntity;

import java.util.List;

/**
 * Created by Sheremeta on 04.04.2015.
 */
public class QuestDao extends AbstractDao<QuestEntity> {

    public List<QuestEntity> getQuestsByUserId(Long userId){
        List<QuestEntity> result = Lists.newArrayList();
        for (QuestEntity questEntity : getAll()) {
            if(questEntity.getQuestId().equals(userId)) result.add(questEntity);
        }
        return result;
    }

}
