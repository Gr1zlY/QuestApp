package com.hackaton.questapp.dao;

import com.google.common.collect.Lists;
import com.hackaton.questapp.entity.TaskEntity;

import java.util.List;

/**
 * Created by Sheremeta on 04.04.2015.
 */
public class TaskDao extends AbstractDao<TaskEntity>{
    public TaskEntity getTaskByNumberAndQuestId(long taskNumber,Long questId){
        for (TaskEntity taskEntity : getAll()) {
            if(taskEntity.getTaskOrdinalNumber() == taskNumber && taskEntity.getQuest().getQuestId().equals(questId))
                return taskEntity;
        }
        return null;
    }


    public List<TaskEntity> getTasksByQuestId(Long questId) {
        List<TaskEntity> list = Lists.newArrayList();
        for (TaskEntity taskEntity : getAll()) {
            if(taskEntity.getTaskId().equals(questId)) list.add(taskEntity);
        }
        return list;
    }

    public void removeByTaskId(Long taskId) {
        removeById(taskId);
    }
}
