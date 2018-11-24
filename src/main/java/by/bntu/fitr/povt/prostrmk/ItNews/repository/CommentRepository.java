package by.bntu.fitr.povt.prostrmk.ItNews.repository;

import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findCommentsByArticleId(Long articleId);

}
