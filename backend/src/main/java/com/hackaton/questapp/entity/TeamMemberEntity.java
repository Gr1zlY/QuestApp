package com.hackaton.questapp.entity;

/**
 * Created by Sheremeta on 04.04.2015.
 */
public class TeamMemberEntity {

    private Long memberId;

    private TeamEntity team;

    private String identifier; // open problem, androidId? IphoneId?

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public TeamEntity getTeam() {
        return team;
    }

    public void setTeam(TeamEntity team) {
        this.team = team;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamMemberEntity that = (TeamMemberEntity) o;

        if (identifier != null ? !identifier.equals(that.identifier) : that.identifier != null) return false;
        if (!memberId.equals(that.memberId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = memberId.hashCode();
        result = 31 * result + (identifier != null ? identifier.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TeamMemberEntity{" +
                "memberId=" + memberId +
                ", team=" + team +
                ", identifier='" + identifier + '\'' +
                '}';
    }
}
