package com.hackaton.questapp.entity;

/**
 * Created with IntelliJ IDEA.
 * User: noflaxe
 * Date: 05.04.15
 * Time: 9:33
 * To change this template use File | Settings | File Templates.
 */
public class TaskStatusEntity  extends Status{

    private Long taskId;

    public TaskStatusEntity(String status, Long id) {
        super(status);
        this.taskId = id;
    }


    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}
