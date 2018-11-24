package by.bntu.fitr.povt.prostrmk.ItNews;

import by.bntu.fitr.povt.prostrmk.ItNews.config.ItNewsApplication;
import by.bntu.fitr.povt.prostrmk.ItNews.dao.ArticleDao;
import by.bntu.fitr.povt.prostrmk.ItNews.dao.MessageDao;
import by.bntu.fitr.povt.prostrmk.ItNews.dao.UserDao;
import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.Article;
import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.Message;
import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ItNewsApplication.class)
//@Transactional
@WebAppConfiguration
public class TddTesting {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private MessageDao messageDao;

    @Test
    public void testFindTopTenLatestNewsShouldBeNotEmpty() {
        List<Article> tenLatestNews = articleDao.findTenLatestNews();
        assertNotNull(tenLatestNews);
    }

    //2

    @Test
    public void testSearchByTitleShouldPass() {
        List<Article> articles = articleDao.searchByTitle("Oracle");
        assertFalse(articles.isEmpty());
    }

    @Test
    public void testSearchByTitleShouldFailed() {
        List<Article> articles = articleDao.searchByTitle("Статья");
        assertTrue(articles.isEmpty());
    }

    //3

    @Test
    public void testAuthenticateUserShouldPass() {
        assertTrue(userDao.authenticateUser("roman","$2a$10$JNxcYbLAUvlBgmojPhzRxOtCPTCu.5qXnz4ewdfZsceDElJTskTFu"));
    }

    @Test
    public void testAuthenticateUserShouldFailed() {
        assertFalse(userDao.authenticateUser("Роман","$2sdhjgsjdkgje.5qXnz4ewdfZsceDElJTskTFu"));
    }

    //4

    @Test
    public void testFindMessagesByUserShouldFindAllMessages() {
        List<Message> messagesBySenderId = messageDao.findMessagesBySenderId(1L);
        assertNotNull(messagesBySenderId);
    }

    @Test
    public void testFindMessagesByUserShouldFindNoMessages() {
        List<Message> messagesBySenderId = messageDao.findMessagesBySenderId(100L);
        assertEquals(Collections.emptyList(),messagesBySenderId);
    }

    //5

    @Test
    public void testValidateUserShouldBeOk() {
        assertTrue(userDao.validateUser(new User("Roman1998", "zxccxz")));
    }

    @Test
    public void testValidateUserShouldBeFailed() {
        assertFalse(userDao.validateUser(new User("R", "z")));
    }

}
