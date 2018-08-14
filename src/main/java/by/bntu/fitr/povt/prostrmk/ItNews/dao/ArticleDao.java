package by.bntu.fitr.povt.prostrmk.ItNews.dao;

import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ArticleDao extends Dao{

    @Autowired
    private Connection connection;

    @Autowired
    private CommentDao commentDao;

    public Article findArticleById(Long id){
        //language=SQL

        ResultSet resultSet = executeQueryWithResult(String.format("SELECT * FROM article WHERE id='%d'", id));
        try {
            if (resultSet.next()){
                Article article = new Article(id, resultSet.getString("title"), resultSet.getString("content"), resultSet.getString("type"), resultSet.getString("path"));
                article.setComments(commentDao.findCommentsByArticleId(id));
                return article;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Article> findArticlesWhereTitleLikeIgnoreCase(String title){
        title = "%" + title + "%";
        //language=SQL
        ResultSet resultSet = executeQueryWithResult(String.format("SELECT * FROM article WHERE title LIKE '%s'", title));
        try {
            return articlesFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<Article> findAll(){
        //language=SQL
        ResultSet resultSet = executeQueryWithResult("SELECT * FROM article");
        try{
            return articlesFromResultSet(resultSet);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return Collections.emptyList();

    }

    public void save(Article article, MultipartFile file){
        try{
            String path = saveFile(file);
            //language=SQL
            executeQuery(String.format("INSERT INTO article(title, content, type, path) values ('%s','%s','%s','%s')", article.getTitle(),article.getContent(),article.getType(),path));
        }catch (Exception e){
            System.out.println("EXCEPTION");
        }
    }

    private String saveFile(MultipartFile file) throws Exception {
        String filePath = "src/main/webapp/resources/pics/";
        File javaFile = new File(filePath + file.getOriginalFilename());
        try {
            byte []bytes = file.getBytes();
            javaFile = new File(javaFile.getAbsolutePath());
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(javaFile));
            stream.write(bytes);
            stream.flush();
            stream.close();
            return "/resources/pics/" + file.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("AAAA");
        }
    }

    private List<Article> articlesFromResultSet(ResultSet resultSet) throws SQLException {
        List<Article> articles = new ArrayList<>();
        while (resultSet.next()){
            articles.add(new Article(resultSet.getLong("id"),resultSet.getString("title"),resultSet.getString("content"),resultSet.getString("type"), resultSet.getString("path")));
        }
        return articles;
    }


}
