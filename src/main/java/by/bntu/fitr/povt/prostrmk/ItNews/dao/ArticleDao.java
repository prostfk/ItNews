package by.bntu.fitr.povt.prostrmk.ItNews.dao;

import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.Article;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@by.bntu.fitr.povt.prostrmk.ItNews.annotation.Dao
public class ArticleDao extends AbstractDao<Article> {

    @Autowired
    private CommentDao commentDao;

    public List<Article> findArticlesWithLimit(Long begin, Long count) {
        //language=SQL
        begin -= 1;
        ResultSet resultSet = executeQueryWithResult(String.format("SELECT * FROM article LIMIT %d,%d", begin * count, count));
        try {
            return articlesFromResultSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Article findArticleById(Long id) {
        //language=SQL
        ResultSet resultSet = executeQueryWithResult(String.format("SELECT * FROM article WHERE id='%d'", id));
        try {
            if (resultSet.next()) {
                Article article = new Article(
                        resultSet.getLong("id"), resultSet.getString("title"), resultSet.getString("content"), resultSet.getString("type"), resultSet.getString("path")
                );
                article.setComments(commentDao.findCommentsByArticleId(id));
                return article;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Article> findArticlesWhereTitleLikeIgnoreCase(String title) {
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

    public List<Article> findArticlesByType(String type) {
        //language=SQL
        List<Article> array = new LinkedList<>();
        ResultSet resultSet = executeQueryWithResult(String.format("SELECT a.id,a.title,a.content, a.type, a.path, COUNT(c.id) FROM article a LEFT JOIN comment c on a.id = c.article_id WHERE a.type='%s' GROUP BY id ORDER BY id DESC", type));
        try {
            if (resultSet.next()) {
                array.add(new Article(
                        resultSet.getLong("id"), resultSet.getString("title"), resultSet.getString("content"), resultSet.getString("type"),
                        resultSet.getString("path"), resultSet.getInt("COUNT(c.id)")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return array;
    }

    public List<Article> findAll() {
        //language=SQL
        ResultSet resultSet = executeQueryWithResult("SELECT * FROM article");
        try {
            return articlesFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

    }

    public List<Article> findIndexPage() {
        List<Article> array = new LinkedList<>();
        ResultSet resultSet = executeQueryWithResult("SELECT a.id,a.title,a.content, a.type, a.path, COUNT(c.id) FROM article a LEFT JOIN comment c on a.id = c.article_id GROUP BY id ORDER BY id DESC");
        try {
            while (resultSet.next()) {
                array.add(new Article(
                        resultSet.getLong("id"), resultSet.getString("title"), resultSet.getString("content"), resultSet.getString("type"),
                        resultSet.getString("path"), resultSet.getInt("COUNT(c.id)")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    public List<Article> findAllReversed() {
        //language=SQL
        ResultSet resultSet = executeQueryWithResult("SELECT * FROM article ORDER BY id DESC");
        try {
            return articlesFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();

    }

    public void save(Article article, MultipartFile file) {
        try {
            String path = saveFile(file);
            //language=SQL
            executeQuery(String.format("INSERT INTO article(title, content, type, path) values ('%s','%s','%s','%s')", article.getTitle(), article.getContent(), article.getType(), path));
        } catch (Exception e) {
            System.out.println("EXCEPTION");
        }
    }

    public void update(Long id, Article article) {
        //language=SQL
        executeQuery(String.format("UPDATE article SET title='%s', content='%s', type='%s', path='%s' WHERE id='%d'", article.getTitle(), article.getContent(), article.getType(), article.getPathToFile(), id));
    }

    public String saveFile(MultipartFile file) throws Exception {
        String filePath = "src/main/webapp/resources/pics/";
        File javaFile = new File(filePath + file.getOriginalFilename());
        try {
            byte[] bytes = file.getBytes();
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

    public List<Article> findAllOffered() {
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM temp_article");
            List<Article> objects = new LinkedList<>();
            while (resultSet.next()) {
                Article article = new Article(resultSet.getLong("id"), resultSet.getString("title"), resultSet.getString("content") + " offered by" + resultSet.getString("username"), resultSet.getString("type"), resultSet.getString("path"));
                objects.add(article);
            }
            return objects;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Article> articlesFromResultSet(ResultSet resultSet) throws SQLException {
        List<Article> articles = new LinkedList<>();
        while (resultSet.next()) {
            Article article = new Article(resultSet.getLong("id"), resultSet.getString("title"), resultSet.getString("content"), resultSet.getString("type"), resultSet.getString("path"));
//            article.setComments(commentDao.findCommentsByArticleId(article.getId()));
            articles.add(article);
        }
        return articles;
    }

    @Override
    protected Article createEntity(ResultSet resultSet) throws SQLException {
        return new Article(
                resultSet.getLong("id"), resultSet.getString("title"), resultSet.getString("content"), resultSet.getString("type"), resultSet.getString("path")
        );
    }

    public List<Article> findTenLatestNews() {
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM article ORDER BY id DESC LIMIT 10");
            return createList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Article> searchByTitle(String title) {
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(String.format("SELECT * FROM article WHERE title LIKE '%%%s%%'", title));
            return createList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void saveOffered(Article article, String username, MultipartFile file) {
        try {
            String path = saveFile(file);
            //language=SQL
            executeQuery(String.format("INSERT INTO temp_article(title, content, type, path,username) values ('%s','%s','%s','%s','%s')", article.getTitle(), article.getContent(), article.getType(), path, username));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteOfferedArticleById(Long id) {
        try {
            connection.createStatement().execute(String.format("DELETE FROM temp_article WHERE id='%d'", id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Article findOfferedArticleById(Long id) {
        try (ResultSet resultSet = connection.createStatement().executeQuery(String.format("SELECT * FROM temp_article WHERE id='%d'", id))) {
            if (resultSet.next()){
                return new Article(
                        resultSet.getLong("id"), resultSet.getString("title"),
                        resultSet.getString("content") + " made by " + resultSet.getString("username"), resultSet.getString("type"),
                        resultSet.getString("path")
                );

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void putFromSaveToArticles(Article article, Long tempArticleId){

        executeQuery(String.format("INSERT INTO article(title, content, type, path) values ('%s','%s','%s','%s')", article.getTitle(), article.getContent(), article.getType(), article.getPathToFile()));
        deleteOfferedArticleById(tempArticleId);
    }

}
