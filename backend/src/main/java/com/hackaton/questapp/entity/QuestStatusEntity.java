package com.hackaton.questapp.entity;



/**
 * Created by Sheremeta on 04.04.2015.
 */

/*@Entity
@Table(name = "QuestStatus")*/
public class QuestStatusEntity {

    private TeamEntity team;

    private Long tasksCompleted;

    public QuestStatusEntity(TeamEntity team, Long tasksCompleted) {
        this.team = team;
        this.tasksCompleted = tasksCompleted;
    }

    public TeamEntity getTeam() {
        return team;
    }

    public void setTeam(TeamEntity team) {
        this.team = team;
    }

    public Long getTasksCompleted() {
        return tasksCompleted;
    }

    public void setTasksCompleted(Long tasksCompleted) {
        this.tasksCompleted = tasksCompleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestStatusEntity that = (QuestStatusEntity) o;

        if (tasksCompleted != null ? !tasksCompleted.equals(that.tasksCompleted) : that.tasksCompleted != null)
            return false;
        if (team != null ? !team.equals(that.team) : that.team != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = team != null ? team.hashCode() : 0;
        result = 31 * result + (tasksCompleted != null ? tasksCompleted.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "QuestStatusEntity{" +
                "team=" + team +
                ", tasksCompleted=" + tasksCompleted +
                '}';
    }
}
