package com.hackaton.questapp.dao;

import com.hackaton.questapp.entity.TeamMemberEntity;

/**
 * Created by Sheremeta on 04.04.2015.
 */
public class TeamMemberDao extends AbstractDao<TeamMemberEntity> {

    public TeamMemberEntity getTeamMemberById(String id){
        for (TeamMemberEntity teamMemberEntity : getAll()) {
            if(teamMemberEntity.getIdentifier().equals(id)) return teamMemberEntity;
        }
        return null;
    }
}
