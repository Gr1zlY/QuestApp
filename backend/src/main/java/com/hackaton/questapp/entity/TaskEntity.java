package com.hackaton.questapp.entity;

import java.util.Arrays;

/**
 * Created by Sheremeta on 04.04.2015.
 */

//@Entity
//@Table(name = "Task")
public class TaskEntity {

  //  @Id
 //   @Column(name = "taskId")
 //   @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long taskId;

    private QuestEntity quest;

    private String taskName;

    private String description;

    private byte[] photo;

    private int taskOrdinalNumber;

    private TaskType taskType;

    private String solution; // can have ANYTHING

    public TaskEntity(Long taskId, QuestEntity quest,String taskName, String textDescription, byte[] photo, int taskOrdinalNumber, TaskType taskType, String solution) {
        this.taskId = taskId;
        this.quest = quest;
        this.taskName = taskName;
        this.description = textDescription;
        this.photo = photo;
        this.taskOrdinalNumber = taskOrdinalNumber;
        this.taskType = taskType;
        this.solution = solution;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public QuestEntity getQuest() {
        return quest;
    }

    public void setQuest(QuestEntity quest) {
        this.quest = quest;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

        if (taskId != null ? !taskId.equals(that.taskId) : that.taskId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return taskId != null ? taskId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TaskEntity{" +
                "taskId=" + taskId +
                ", quest=" + quest +
                ", description='" + description + '\'' +
                ", photo=" + Arrays.toString(photo) +
                ", taskOrdinalNumber=" + taskOrdinalNumber +
                '}';
    }
}
