package com.hackaton.questapp.entity;

/**
 * Created with IntelliJ IDEA.
 * User: noflaxe
 * Date: 05.04.15
 * Time: 2:28
 * To change this template use File | Settings | File Templates.
 */
public class TaskEntityForClientDTO extends TaskEntity {
    public TaskEntityForClientDTO(TaskEntity entity) {
        super(entity.getTaskId(), entity.getQuest(), entity.getTaskName(), entity.getDescription(), entity.getPhoto(),
                entity.getTaskOrdinalNumber(), entity.getTaskType(), entity.getSolution());
    }

    private boolean solved;

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }
}
