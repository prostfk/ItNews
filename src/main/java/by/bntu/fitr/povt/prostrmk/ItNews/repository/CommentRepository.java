package by.bntu.fitr.povt.prostrmk.ItNews.repository;

import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    @Query(value = "SELECT * FROM comment WHERE article_id=:articleId",nativeQuery = true)
    List<Comment> findCommentsByArticleId(@Param("articleId") Long articleId);

}
