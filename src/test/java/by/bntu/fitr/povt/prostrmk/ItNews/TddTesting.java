package by.bntu.fitr.povt.prostrmk.ItNews;

import by.bntu.fitr.povt.prostrmk.ItNews.config.ItNewsApplication;
import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.Message;
import by.bntu.fitr.povt.prostrmk.ItNews.repository.ArticleRepository;
import by.bntu.fitr.povt.prostrmk.ItNews.repository.MessageRepository;
import by.bntu.fitr.povt.prostrmk.ItNews.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ItNewsApplication.class)
//@Transactional
@WebAppConfiguration
public class TddTesting {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

//    @Test
//    public void testFindTopTenLatestNewsShouldBeNotEmpty() {
//        List<Article> tenLatestNews = articleRepository.findTenLatestNews();
//        assertNotNull(tenLatestNews);
//    }

    //2

//    @Test
//    public void testSearchByTitleShouldPass() {
//        List<Article> articles = articleRepository.searchByTitle("Oracle");
//        assertFalse(articles.isEmpty());
//    }
//
//    @Test
//    public void testSearchByTitleShouldFailed() {
//        List<Article> articles = articleRepository.searchByTitle("Статья");
//        assertTrue(articles.isEmpty());
//    }

    //3

//    @Test
//    public void testAuthenticateUserShouldPass() {
//        assertTrue(userRepository.authenticateUser("roman","$2a$10$JNxcYbLAUvlBgmojPhzRxOtCPTCu.5qXnz4ewdfZsceDElJTskTFu"));
//    }
//
//    @Test
//    public void testAuthenticateUserShouldFailed() {
//        assertFalse(userRepository.authenticateUser("Роман","$2sdhjgsjdkgje.5qXnz4ewdfZsceDElJTskTFu"));
//    }

    //4

    @Test
    public void testFindMessagesByUserShouldFindAllMessages() {
        List<Message> messagesBySenderId = messageRepository.findMessagesBySenderId(1L);
        assertNotNull(messagesBySenderId);
    }

    @Test
    public void testFindMessagesByUserShouldFindNoMessages() {
        List<Message> messagesBySenderId = messageRepository.findMessagesBySenderId(100L);
        assertEquals(Collections.emptyList(),messagesBySenderId);
    }

    //5

//    @Test
//    public void testValidateUserShouldBeOk() {
//        assertTrue(userRepository.validateUser(new User("Roman1998", "zxccxz")));
//    }
//
//    @Test
//    public void testValidateUserShouldBeFailed() {
//        assertFalse(userRepository.validateUser(new User("R", "z")));
//    }

}
