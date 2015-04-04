package com.hackaton.questapp.entity;

import javax.persistence.*;
import java.util.Arrays;

/**
 * Created by Sheremeta on 04.04.2015.
 */
/*@Entity
@Table(name = "Quest")*/
public class QuestEntity {


/*    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy=GenerationType.IDENTITY)*/
    private Long userId;

    private String name;

    private String description;

    private byte[] photo;

    private OrganizatorUser creator;

    private Long time;

    public QuestEntity(Long userId, String name, String description, byte[] photo, OrganizatorUser creator, Long time) {
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.creator = creator;
        this.time = time;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public OrganizatorUser getCreator() {
        return creator;
    }

    public void setCreator(OrganizatorUser creator) {
        this.creator = creator;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestEntity that = (QuestEntity) o;

        if (!userId.equals(that.userId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return userId.hashCode();
    }


    @Override
    public String toString() {
        return "QuestEntity{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", photo=" + Arrays.toString(photo) +
                ", creator=" + creator +
                ", time=" + time +
                '}';
    }
}
