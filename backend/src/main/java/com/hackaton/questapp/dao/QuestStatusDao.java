package com.hackaton.questapp.dao;

import com.hackaton.questapp.entity.QuestStatusEntity;
import com.hackaton.questapp.entity.TeamEntity;

/**
 * Created by Sheremeta on 04.04.2015.
 */
public class QuestStatusDao extends AbstractDao<QuestStatusEntity> {

    @Override
    public QuestStatusEntity getById(Long id){
        return null;
    }

    public QuestStatusEntity getByTeam(TeamEntity team){
        for (QuestStatusEntity questStatusEntity : getAll()) {
            if(questStatusEntity.getTeam().equals(team)) return questStatusEntity;
        }
        return null;
    }
}
