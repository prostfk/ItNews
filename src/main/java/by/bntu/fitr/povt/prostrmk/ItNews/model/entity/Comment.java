package by.bntu.fitr.povt.prostrmk.ItNews.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 2435636533523457537L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String content;

    private String date;

    public Comment() {
    }

    public Comment(String username, String content, String date) {
        this.username = username;
        this.content = content;
        this.date = date;
    }


    public Comment(Long id, String username, String content, String date, Long articleId) {
        this.id = id;
        this.username = username;
        this.content = content;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
