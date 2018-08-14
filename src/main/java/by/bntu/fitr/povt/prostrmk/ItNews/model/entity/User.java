package by.bntu.fitr.povt.prostrmk.ItNews.model.entity;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 2435636533523457538L;

    private Long id;

    private String username;

    private String password;

    private String confirmPassword;

    private int isBlocked;

    public User() {
    }


    public User(String username) {
        this.username = username;
    }

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(Long id, String username, String password, int isBlocked) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isBlocked = isBlocked;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int isBlocked() {
        return isBlocked;
    }

    public void setBlocked(int blocked) {
        isBlocked = blocked;
    }

    @Override
    public boolean equals(Object obj) {
        return obj.hashCode() == this.hashCode() && obj instanceof User;
    }

    @Override
    public int hashCode() {
        final int hashConst = 31;
        int result = 1;
//        result = (int) (hashConst * result + id);
        result = hashConst * result + username.hashCode();
        result = hashConst * result + password.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("User{\n id= '%s', username= '%s' , password= '%s', is blocked = '%b' }",id,username,password, isBlocked);
    }
}
