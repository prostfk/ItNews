package by.bntu.fitr.povt.prostrmk.ItNews.dao;

import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

public abstract class AbstractDao<T> {//abstr, gen

    private static final Logger LOGGER = Logger.getLogger(AbstractDao.class);
    protected Connection connection;

    public AbstractDao() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/db.properties"));
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url,user,password);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
            e.printStackTrace();
        }
    }

    //    helpers
    void executeQuery(String sql) {
        try {
            connection.createStatement().execute(sql);
            LOGGER.debug("Sql query: " + sql);
            System.out.println("Sql query: " + sql);
        } catch (SQLException e) {
            LOGGER.warn("An error occurred for sql query: " + sql);
            LOGGER.error(e);
        }
    }

    ResultSet executeQueryWithResult(String sql) {
        ResultSet resultSet = null;
        try {
            resultSet = connection.createStatement().executeQuery(sql);
            LOGGER.debug("Sql query: " + sql);
            System.out.println("Sql query: " + sql);
        } catch (SQLException e) {
            LOGGER.warn("An error occurred for sql query: " + sql);
            LOGGER.error(e);
        }
        return resultSet;
    }

    List<T> createList(ResultSet resultSet){//gen
        List<T> list = new LinkedList<>();
        try {
            while (resultSet.next()){
                list.add(createEntity(resultSet));
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    protected abstract T createEntity(ResultSet resultSet) throws SQLException;


}
