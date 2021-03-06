package by.bntu.fitr.povt.prostrmk.ItNews;

import by.bntu.fitr.povt.prostrmk.ItNews.config.ItNewsApplication;
import by.bntu.fitr.povt.prostrmk.ItNews.dao.UserDao;
import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ItNewsApplication.class)
//@Transactional
@WebAppConfiguration
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void findUserByUsernameTestCorrect() {
        User roman = userDao.findUserByUsername("roman");
        assertEquals("roman",roman.getUsername());
    }

    @Test
    public void findUserByUsernameTestIncorrect() {
        User roman = userDao.findUserByUsername("vova333");
        assertNull(roman);
    }


    @Test
    public void checkUserAuthenticationCorrect(){
        User user = new User("roman", "zxccxz");
        User base = userDao.findUserByUsername(user.getUsername());
        Assert.assertTrue(passwordEncoder.matches(user.getPassword(),base.getPassword()));
    }

    @Test
    public void checkUserAuthenticationIncorrect(){
        User user = new User("roman", "123");
        User base = userDao.findUserByUsername(user.getUsername());
        Assert.assertFalse(passwordEncoder.matches(user.getPassword(),base.getPassword()));
    }


}
