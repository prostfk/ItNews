package by.bntu.fitr.povt.prostrmk.ItNews.dto;

import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.Article;
import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.User;

import java.util.List;
import java.util.Objects;

public class UserDto {

    private User user;
    private List<Article> articles;

    public UserDto() {
    }

    public UserDto(User user) {
        this.user = user;
    }

    public UserDto(User user, List<Article> articles) {
        this.user = user;
        this.articles = articles;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(user, userDto.user) &&
                Objects.equals(articles, userDto.articles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, articles);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "user=" + user +
                ", articles=" + articles +
                '}';
    }
}
