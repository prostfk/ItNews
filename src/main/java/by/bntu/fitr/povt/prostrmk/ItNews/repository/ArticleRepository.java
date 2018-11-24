package by.bntu.fitr.povt.prostrmk.ItNews.repository;

import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Long> {

    List<Article> findArticlesByType(String type, Pageable pageable);

    Article findArticleById(Long id);

    List<Article> findAll(Pageable pageable);

    List<Article> findArticlesByTitleLikeIgnoreCase(String title);

    @Query(value = "SELECT * FROM user_posts JOIN article a on user_posts.article_id = a.id WHERE user_id=:userId",nativeQuery = true)
    List<Article> findArticlesByUser(@Param("userId")Long userId);

    @Query(value = "INSERT INTO user_posts(user_id, article_id) VALUES (:userId, :articleId)", nativeQuery = true)
    void saveArticleIntoUsersPosts(@Param("userId")Long userId, @Param("articleId")Long articleId);

    @Query(value = "DELETE FROM user_posts WHERE user_id=:userId AND article_id=:articleId",nativeQuery = true)
    void deleteArticleFromUserPosts(@Param("userId")Long userId, @Param("articleId")Long articleId);

    List<Article> findAll();

}
