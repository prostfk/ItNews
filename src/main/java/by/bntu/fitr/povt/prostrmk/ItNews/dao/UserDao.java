package by.bntu.fitr.povt.prostrmk.ItNews.dao;

import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class UserDao extends Dao {

    @Autowired
    private Connection connection;

    public List<User> findAll() {
        //language=SQL
        ResultSet resultSet = executeQueryWithResult("SELECT * FROM user");
        try {
            return usersFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public User findUserById(Long id) {
        //language=SQL
        ResultSet resultSet = executeQueryWithResult(String.format("SELECT * FROM user WHERE id='%d'", id));
        try {
            if (resultSet.next()) {
                return new User(resultSet.getLong("id"), resultSet.getString("username"), resultSet.getString("password"));
            } else {
                System.out.println("No result set");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(User user) {
        //language=SQL
        executeQuery(String.format("INSERT INTO user(username,password,blocked) VALUES ('%s','%s',0)", user.getUsername(), user.getPassword()));
    }

    public User findUserByUsername(String username) {
        //language=SQL
        ResultSet resultSet = executeQueryWithResult(String.format("SELECT * FROM user WHERE username='%s'", username));
        try {
            if (resultSet.next()) {
                return new User(resultSet.getLong("id"), username, resultSet.getString("password"), resultSet.getInt("blocked"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<User> usersFromResultSet(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            users.add(new User(
                    resultSet.getLong("id"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getInt("blocked")
            ));
        }
        return users;
    }


}
