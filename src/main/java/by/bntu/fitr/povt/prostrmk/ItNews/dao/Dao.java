package by.bntu.fitr.povt.prostrmk.ItNews.dao;

import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dao {

    @Autowired
    private Connection connection;

    //    helpers
    void executeQuery(String sql) {
        try {
            connection.createStatement().execute(sql);
            System.out.println("Sql query: " + sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    ResultSet executeQueryWithResult(String sql) {
        ResultSet resultSet = null;
        try {
            resultSet = connection.createStatement().executeQuery(sql);
            System.out.println("Sql query: " + sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

}
