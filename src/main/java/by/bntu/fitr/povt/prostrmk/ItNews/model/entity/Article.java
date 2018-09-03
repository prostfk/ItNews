package by.bntu.fitr.povt.prostrmk.ItNews.model.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Article implements IArticles, Serializable {

    private static final long serialVersionUID = 2435636533523457536L;

    private Long id;

//    @Column(name = "title")
    private String title;

//    @Column(name = "content")
    private String content;

//    @Column(name = "type")
    private String type;

//    @Column(name = "pathToFile")
    private String pathToFile;

    private List<Comment> comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPathToFile() {
        return pathToFile;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Article() {
    }

    public Article(Long id, String title, String content, String type, String pathToFile) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.type = type;
        this.pathToFile = pathToFile;
    }

    public Article(String title, String content, String type, String pathToFile) {
        this.title = title;
        this.content = content;
        this.type = type;
        this.pathToFile = pathToFile;
    }

    public Article(Long id, String title, String content, String type, String pathToFile, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.type = type;
        this.pathToFile = pathToFile;
        this.comments = comments;
    }

    @Override
    public int hashCode() {
        final int hashConst = 31;
        int result = 1;
        result = (int) (hashConst * result + id);
        result = hashConst * result + title.hashCode();
        result = hashConst * result + content.hashCode();
        result = hashConst * result + pathToFile.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(id, article.id) &&
                Objects.equals(title, article.title) &&
                Objects.equals(content, article.content) &&
                Objects.equals(type, article.type) &&
                Objects.equals(pathToFile, article.pathToFile) &&
                Objects.equals(comments, article.comments);
    }

    @Override
    public String toString() {
        return String.format("Id - %d,\n Title- %s,\n Content - %s,\nPath to file - %s,\nType - %s", id, title, content, pathToFile, type);
    }
}
