package com.hackaton.questapp.dao;

import com.hackaton.questapp.storage.DBStubStorage;

import java.util.List;

/**
 * Created by Sheremeta on 04.04.2015.
 */
public abstract class AbstractDao<Type> {

    private DBStubStorage<Type> storage;

    public List<Type> getAll(){
        return storage.getAll();
    }

    public Type getById(Long id){
        return storage.getById(id);
    }

    public void insert(Long key, Type data){
        storage.insert(key,data);
    }


    public void setStorage(DBStubStorage<Type> storage) {
        this.storage = storage;
    }
}
