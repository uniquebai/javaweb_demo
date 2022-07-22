package com.xyq.pojo;

/**
 * @author cwtt
 */
public class User {
    private Integer id;
    private String nickname;
    private String username;
    private String password;
    private String hobby;
    private String location;
    private Integer status;

    public User(){}

    public User(Integer id, String nickname, String username, String password, String hobby, String location, Integer status) {
        this.id = id;
        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.hobby = hobby;
        this.location = location;
        this.status = status;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHobby() {
        return hobby;
    }

    public String getLocation() {
        return location;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", hobby='" + hobby + '\'' +
                ", location='" + location + '\'' +
                ", status=" + status +
                '}';
    }
}
