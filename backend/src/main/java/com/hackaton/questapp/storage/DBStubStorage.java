package com.hackaton.questapp.storage;

import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Sheremeta on 04.04.2015.
 */
public class DBStubStorage<Type> {

    private Map<Long,Type> storage = Maps.newHashMap();

    public List<Type> getAll(){
        return new ArrayList<>(storage.values());
    }

    public Type getById(Long id){
        return storage.get(id);
    }

    public void insert(Long key,Type data){
        storage.put(key,data);
    }
}
