package com.cab.management.repository;

import com.cab.management.model.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomRepository<T extends Entity> {
    protected final List<T> records = new ArrayList<>();

    public T save(T t){
        List<T> selectedEntities = records.stream().filter(e -> e.getId().equals(t.getId())).collect(Collectors.toList());
        if(selectedEntities!= null && selectedEntities.size() >=1){
            records.remove(selectedEntities.get(0));
            records.add(t);
        }else{
            records.add(t);
        }

        return t;
    }

    public T findOne(String primaryKey){
        List<T> result = records.stream().filter(e -> e.getId().equals(primaryKey)).collect(Collectors.toList());
        if(result == null || result.size() == 0){
            return null;
        }
        return result.get(0);
    }

    public List<T> findAll(){
        return records;
    }

    public int count(){
        return records.size();
    }

    public void delete(T entity){
        records.remove(entity);
    }

    boolean exists(String primaryKey){
        return records.contains(primaryKey);
    }

}
