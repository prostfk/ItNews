package by.bntu.fitr.povt.prostrmk.ItNews.dao;

import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class Dao {

    private static final Logger LOGGER = Logger.getLogger(Dao.class);

    @Autowired
    private Connection connection;

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

}
