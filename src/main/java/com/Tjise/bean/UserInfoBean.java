package com.Tjise.bean;

/**
 * 要求：bean的属性名和数据库的字段名保持一致；可以忽略大小写；
 */
public class UserInfoBean {
    // 属性
    private Integer userId;
    private String userName;
    private String userPsw;
    private String userTel;

    // 行为

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPsw() {
        return userPsw;
    }

    public void setUserPsw(String userPsw) {
        this.userPsw = userPsw;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPsw='" + userPsw + '\'' +
                ", userTel='" + userTel + '\'' +
                '}';
    }
}
