package com.hackaton.questapp.dao;

import com.hackaton.questapp.entity.OrganizatorUser;

/**
 * Created by Sheremeta on 04.04.2015.
 */
public class OrganizatorDao extends AbstractDao<OrganizatorUser> {


    public OrganizatorUser getByLogin(String login) {
        for (OrganizatorUser organizatorUser : getAll()) {
            if(organizatorUser.getLogin().equals(login)) return organizatorUser;
        }
        return null;
    }
}
