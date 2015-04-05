package com.hackaton.questapp.rest;

import com.hackaton.questapp.dao.OrganizatorDao;
import com.hackaton.questapp.entity.OrganizatorUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Sheremeta on 04.04.2015.
 */
@RestController
public class OrganizatorService {

    private OrganizatorDao organizatorDao;

    @RequestMapping(value = "/login", headers = "Accept=application/json", method = RequestMethod.POST)
    public Long login(@RequestParam String login, @RequestParam String password){
       OrganizatorUser user = organizatorDao.getByLogin(login);
       boolean validLogin =  user != null && password.equals(user.getPassword());
       return validLogin? user.getUserId() : -1L;
    }

    @RequestMapping(value = "/register", headers = "Accept=application/json", method = RequestMethod.POST)
    public Long register(@RequestParam String login, @RequestParam String password){
        OrganizatorUser user = organizatorDao.getByLogin(login);
        if(user != null && user.getLogin().equals(login)){
            return -1L;
        } else {
            OrganizatorUser newUser = new OrganizatorUser(System.currentTimeMillis(),login,password);
            organizatorDao.insert(newUser.getUserId(),newUser);
            return newUser.getUserId();
        }
    }

    public void setOrganizatorDao(OrganizatorDao organizatorDao) {
        this.organizatorDao = organizatorDao;
    }
}
