package com.hackaton.questapp.entity;

import java.util.Arrays;

/**
 * Created by Sheremeta on 04.04.2015.
 */
public class TaskEntity {

    private Long id;

    private QuestEntity quest;

    private String textDescription;

    private byte[] photo;

    private int taskOrdinalNumber;

    private TaskType taskType;

    private String solution; // can have ANYTHING

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestEntity getQuest() {
        return quest;
    }

    public void setQuest(QuestEntity quest) {
        this.quest = quest;
    }

    public String getTextDescription() {
        return textDescription;
    }

    public void setTextDescription(String textDescription) {
        this.textDescription = textDescription;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public int getTaskOrdinalNumber() {
        return taskOrdinalNumber;
    }

    public void setTaskOrdinalNumber(int taskOrdinalNumber) {
        this.taskOrdinalNumber = taskOrdinalNumber;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskEntity that = (TaskEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TaskEntity{" +
                "id=" + id +
                ", quest=" + quest +
                ", textDescription='" + textDescription + '\'' +
                ", photo=" + Arrays.toString(photo) +
                ", taskOrdinalNumber=" + taskOrdinalNumber +
                '}';
    }
}
