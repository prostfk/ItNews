package by.bntu.fitr.povt.prostrmk.ItNews.repository;

import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {

    List<Message> findMessagesBySenderId(Long id);
    List<Message> findMessagesBySenderIdAndReceiverId(Long senderId, Long receiverId);
    @Query(value = "SELECT DISTINCT u.username, u.id FROM user_message JOIN user u on user_message.receiver_id = u.id JOIN user u2 on user_message.sender_id = u2.id WHERE u.id=:userId OR u2.id=:userId",nativeQuery = true)
    List<String> findConversations(@Param("userId")Long userId);

}
