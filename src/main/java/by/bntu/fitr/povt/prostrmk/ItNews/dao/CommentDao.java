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
public class CommentDao extends Dao {

    @Autowired
    private Connection connection;

    public List<Comment> findCommentsByArticleId(Long id){
        //language=SQL
        ResultSet resultSet = executeQueryWithResult("SELECT * FROM comment WHERE article_id=" + id);
        try {
            return commentsFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public void save(Comment comment){
        //language=SQL
        executeQuery(String.format("INSERT INTO comment(username, content, article_id, date) VALUES ('%s','%s','%d','%s')",comment.getUsername(),comment.getComment(),comment.getArticleId(),comment.getDate().toString()));
    }

    private List<Comment> commentsFromResultSet(ResultSet resultSet) throws SQLException {
        List<Comment> comments = new ArrayList<>();
        while(resultSet.next()){
            comments.add(new Comment(resultSet.getLong("id"),resultSet.getString("username"),resultSet.getString("content"),resultSet.getString("date"),resultSet.getLong("article_id")));
        }
        return comments;
    }

}
