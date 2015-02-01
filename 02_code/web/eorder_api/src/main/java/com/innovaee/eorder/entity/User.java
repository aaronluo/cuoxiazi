package com.innovaee.eorder.entity;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_USER")
public class User extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private String cellphone;

    private Integer userScore;

    private boolean userStatus;

    private UserLevel userLevel;

    private List<Order> orders;

    public User() {
    }

    public User(String username, String password, String cellphone,
            Integer userScore, boolean userStatus) {
        super();
        this.username = username;
        this.password = password;
        this.cellphone = cellphone;
        this.userScore = userScore;
        this.userStatus = userStatus;
    }

    @Basic
    @Column(name = "USERNAME")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "CELLPHONE")
    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    @Basic
    @Column(name = "USER_SCORE")
    public Integer getUserScore() {
        return userScore;
    }

    public void setUserScore(Integer userScore) {
        this.userScore = userScore;
    }

    @Basic
    @Column(name = "USER_STATUS")
    public boolean isUserStatus() {
        return userStatus;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }

    @OneToOne(targetEntity = UserLevel.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_LEVEL_ID")
    public UserLevel getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(UserLevel userLevel) {
        this.userLevel = userLevel;
    }

    @OneToMany(targetEntity = Order.class, fetch = FetchType.EAGER, mappedBy = "member")
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "User [username=" + username + ", password=" + password
                + ", cellphone=" + cellphone + ", userScore=" + userScore
                + ", userStatus=" + userStatus + ", userLevel=" + userLevel
                + ", orders=" + orders + "]";
    }

}