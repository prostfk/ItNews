package by.bntu.fitr.povt.prostrmk.ItNews.dao;

import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class CommentDao extends AbstractDao<Comment> {


    protected List<Comment> findCommentsByArticleId(Long id) {
        try (ResultSet resultSet = executeQueryWithResult(String.format("SELECT * FROM comment WHERE article_id = %d", id))) {
            return createList(resultSet);
        }catch (Exception e){
            return null;
        }
    }

    public void save(Comment comment){
        //language=SQL
        executeQuery(String.format("INSERT INTO comment(username, content, article_id, date) VALUES ('%s','%s','%d','%s')",comment.getUsername(),comment.getComment(),comment.getArticleId(),comment.getDate().toString()));
    }

    private List<Comment> commentsFromResultSet(ResultSet resultSet) throws SQLException {
        List<Comment> comments = new ArrayList<>();
        while(resultSet.next()){
            comments.add(createEntity(resultSet));
        }
        return comments;
    }

    @Override
    protected Comment createEntity(ResultSet resultSet) throws SQLException {
        return new Comment(
                resultSet.getLong("id"),resultSet.getString("username"),resultSet.getString("content"),resultSet.getString("date"),resultSet.getLong("article_id")
        );
    }


}
