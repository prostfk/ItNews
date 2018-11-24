package by.bntu.fitr.povt.prostrmk.ItNews.model.entity;

import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 2435636533523457538L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 4, max=60)
    private String username;

    @Size(min = 1,max = 100)
    private String password;

    @Transient
    private transient String confirmPassword;

    @Column(name = "blocked")
    private boolean isBlocked;

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
        this.isBlocked = isBlocked != 0;
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
        return isBlocked ? 1 : 0;
    }

    public void setBlocked(int blocked) {
        isBlocked = blocked != 0;
    }

    public boolean getStatus(){
        return isBlocked;
    }

    public void setStatus(boolean status){
        isBlocked = status;
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
        return String.format("User{\n id= '%s', username= '%s' , password= '%s', is blocked = '%b' }", id, username, password, isBlocked);
    }
}
