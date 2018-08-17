package by.bntu.fitr.povt.prostrmk.ItNews.dao;

import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.Message;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class MessageDao extends Dao {

    @Autowired private UserDao userDao;

    private static final Logger LOGGER = Logger.getLogger(MessageDao.class);

    public List<Message> findMessagesBySenderId(long id){
        //language=SQL
        ResultSet resultSet = executeQueryWithResult(String.format("SELECT * FROM user_message WHERE sender_id='%d'", id));
        List<Message> messages = new ArrayList<>();
        try{
            while (resultSet.next()){
                messages.add(new Message(
                        resultSet.getLong("id"),resultSet.getLong("sender_id"),
                        resultSet.getLong("receiver_id"),resultSet.getString("content")
                ));
            }
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return messages;
    }

    public List<Message> findMessagesBySenderAndReceiver(long senderId, long receiverId){
        //language=SQL
        ResultSet resultSet = executeQueryWithResult(String.format("SELECT * FROM user_message WHERE sender_id='%d' AND receiver_id='%d'", senderId, receiverId));
        List<Message> messages = new ArrayList<>();
        try {
            while (resultSet.next()){
                messages.add(new Message(
                        resultSet.getLong("id"),resultSet.getLong("sender_id"),
                        resultSet.getLong("receiver_id"),resultSet.getString("content")
                ));
            }
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return messages;
    }

    public void save(Message message){
        //language=SQL
        executeQuery(String.format("INSERT INTO user_message(sender_id, receiver_id, content) values('%d','%d','%s')", message.getSenderId(),message.getReceiverId(),message.getText()));
    }

}
