package com.hackaton.questapp.entity;

import javax.persistence.*;

/**
 * Created by Sheremeta on 04.04.2015.
 */
/*
@Entity
@Table(name = "Team")*/
public class TeamEntity {

/*    @Id
    @Column(name = "teamId")
    @GeneratedValue(strategy=GenerationType.IDENTITY)*/
    private Long teamId;

    private String name;

    private QuestEntity quest;

    public TeamEntity(Long teamId, String name, QuestEntity quest) {
        this.teamId = teamId;
        this.name = name;
        this.quest = quest;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public QuestEntity getQuest() {
        return quest;
    }

    public void setQuest(QuestEntity quest) {
        this.quest = quest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamEntity that = (TeamEntity) o;

        if (teamId != null ? !teamId.equals(that.teamId) : that.teamId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return teamId != null ? teamId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TeamEntity{" +
                "teamId=" + teamId +
                ", name='" + name + '\'' +
         //       ", quest=" + quest +
                '}';
    }
}
