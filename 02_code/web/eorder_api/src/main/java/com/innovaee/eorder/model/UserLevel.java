package com.innovaee.eorder.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "T_USER_LEVEL")
public class UserLevel extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String levelName;

    private Float discount;

    private Integer levelScore;

    private boolean levelStatus;

    public UserLevel() {
    }

    public UserLevel(String levelName, Float discount, Integer levelScore,
            boolean levelStatus) {
        super();
        this.levelName = levelName;
        this.discount = discount;
        this.levelScore = levelScore;
        this.levelStatus = levelStatus;
    }

    @Basic
    @Column(name = "LEVEL_NAME")
    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    @Basic
    @Column(name = "DISCOUNT")
    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    @Basic
    @Column(name = "LEVEL_SCORE")
    public Integer getLevelScore() {
        return levelScore;
    }

    public void setLevelScore(Integer levelScore) {
        this.levelScore = levelScore;
    }

    @Basic
    @Column(name = "LEVEL_STATUS")
    public boolean isLevelStatus() {
        return levelStatus;
    }

    public void setLevelStatus(boolean levelStatus) {
        this.levelStatus = levelStatus;
    }

    @Override
    public String toString() {
        return "UserLevel [levelName=" + levelName + ", discount=" + discount
                + ", levelScore=" + levelScore + ", levelStatus=" + levelStatus
                + "]";
    }

}