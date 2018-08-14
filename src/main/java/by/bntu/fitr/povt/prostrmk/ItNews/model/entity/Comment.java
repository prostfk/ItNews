package by.bntu.fitr.povt.prostrmk.ItNews.model.entity;

import java.io.Serializable;

public class Comment implements Serializable {

    private static final long serialVersionUID = 2435636533523457537L;

    private Long id;

    private String username;

    private String comment;

    private String date;

    private Long articleId;

    public Comment() {
    }

    public Comment(String username, String comment, String date) {
        this.username = username;
        this.comment = comment;
        this.date = date;
    }

    public Comment(String username, String comment, String date, Long articleId) {
        this.username = username;
        this.comment = comment;
        this.date = date;
        this.articleId = articleId;
    }

    public Comment(Long id, String username, String comment, String date, Long articleId) {
        this.id = id;
        this.username = username;
        this.comment = comment;
        this.date = date;
        this.articleId = articleId;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }
}
