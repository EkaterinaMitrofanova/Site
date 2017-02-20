package model;

import java.io.Serializable;

public class User implements Serializable{

    private int id;
    private String nickname;
    private String email;
    private String password;
    private String sex;

    public User (int id, String nickname, String email, String password, String sex){
        super();
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.sex = sex;
    }

    public User(){
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User [Id=" + id + ", Name="
                + nickname + ", Email=" + email + ", Sex=" + sex + "]";
    }
}
