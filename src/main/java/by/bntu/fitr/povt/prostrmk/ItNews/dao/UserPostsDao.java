package by.bntu.fitr.povt.prostrmk.ItNews.dao;

import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.Article;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class UserPostsDao extends AbstractDao {

    private static final Logger LOGGER = Logger.getLogger(UserPostsDao.class);

    @Autowired private UserDao userDao;
    @Autowired private ArticleDao articleDao;

    public List<Article> findArticlesByUser(Long id) {
        //language=SQL
        ResultSet resultSet = executeQueryWithResult("SELECT * FROM user_posts JOIN article a on user_posts.article_id = a.id WHERE user_id=" + id);
        List<Article> articles = new ArrayList<>();
        try{
            while(resultSet.next()){
                articles.add(new Article(
                        resultSet.getLong("article_id"),resultSet.getString("title"),
                        resultSet.getString("content"),resultSet.getString("type"),
                        resultSet.getString("path"), Collections.emptyList()
                ));
            }
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return articles;
    }

    public void save(long userId, long articleId){
        //language=SQL
        executeQuery(String.format("INSERT INTO user_posts(user_id, article_id) VALUES('%d','%d')", userId,articleId));
    }

    public void delete(long userId, long articleId){
        //language=SQL
        executeQuery(String.format("DELETE FROM user_posts WHERE user_id='%d' AND article_id='%d'", userId, articleId));
    }

    @Override
    protected Object createEntity(ResultSet resultSet) throws SQLException {
        return null;
    }
}
