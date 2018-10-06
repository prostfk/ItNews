package by.bntu.fitr.povt.prostrmk.ItNews.dao;

import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class UserDao extends AbstractDao<User> {


    private static final Logger LOGGER = Logger.getLogger(UserDao.class);

    public List<User> findAll() {
        //language=SQL
        ResultSet resultSet = executeQueryWithResult("SELECT * FROM user");
        return createList(resultSet);
    }

    public User findUserById(Long id) {
        //language=SQL
        ResultSet resultSet = executeQueryWithResult(String.format("SELECT * FROM user WHERE id='%d'", id));
        try {
            if (resultSet.next()) {
                return new User(resultSet.getLong("id"), resultSet.getString("username"), resultSet.getString("password"),resultSet.getInt("blocked"));
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

    public void update(Long id, User user){
        //language=SQL
        executeQuery(String.format("UPDATE user set username='%s', password='%s',blocked='%d' WHERE id='%d'", user.getUsername(),user.getPassword(),user.isBlocked(), id));
    }

    public boolean validateUser(User user){
        int lengthUsername = user.getUsername().length();
        int lengthPassword = user.getPassword().length();
        return lengthPassword > 2 && lengthPassword < 10 && lengthUsername > 3 && lengthUsername < 15 && findUserByUsername(user.getUsername()) == null;
    }

    public List<User> findUsersByUsernameLike(String username){
        username = "%" + username + "%";
        //language=SQL
        ResultSet resultSet = executeQueryWithResult(String.format("SELECT * FROM user WHERE username LIKE '%s'", username));
        return createList(resultSet);
    }

    @Override
    protected User createEntity(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getLong("id"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getInt("blocked")
        );
    }
}
