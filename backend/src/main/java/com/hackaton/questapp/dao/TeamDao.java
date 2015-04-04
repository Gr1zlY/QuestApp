package com.hackaton.questapp.dao;

import com.google.common.collect.Lists;
import com.hackaton.questapp.entity.TeamEntity;

import java.util.List;

/**
 * Created by Sheremeta on 04.04.2015.
 */
public class TeamDao extends AbstractDao<TeamEntity> {

    public List<TeamEntity> getTeamsByQuestId(Long questId) {
        List<TeamEntity> result = Lists.newArrayList();
        for (TeamEntity teamEntity : getAll()) {
            if(teamEntity.getQuest().getUserId().equals(questId)) result.add(teamEntity);
        }
        return result;
    }

}
